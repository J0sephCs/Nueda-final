let balance= 0;
let investment= 0;

let totalPrice= 0;
let totalQuantity= 0;
let uniqueCompanies= new Set();


function openModal() {
    document.getElementById('modal-overlay').style.display = 'flex';
}
 // Function to close the modal
 function closeModal() {
    document.getElementById('modal-overlay').style.display = 'none';
}

function openStockForm(){
    const stockForm = document.getElementById('submitStockForm');
    
    // Clear the form fields
    stockForm.reset();
    document.getElementById('add-stock').style.display = 'flex';
}
function closeStockForm(){
    document.getElementById('add-stock').style.display = 'none';
}





document.addEventListener('DOMContentLoaded', function() {
    const button = document.getElementById('fetchAllStocks');
    
    if (button) {
        button.addEventListener('click', async function() { // async function declaration

            const response = await fetch('http://localhost:8080/stock/getStocks'); // await the fetch operation
            if (!response.ok) {
                throw new Error('Failed to fetch stocks');
            }
            
            const data = await response.json(); // await parsing of JSON response
            
            const popUp = window.open('', 'Portfolio pop up', 'width=600, height=400, scrollbar=yes, resizable=yes');
            if (popUp) {
                popUp.document.write(`
                    <html>
                    <head>
                        <title>Investment Portfolio Overview</title>
                        <link rel="stylesheet" type="text/css" href="popup.css">
                    </head>
                    <body>
                        <h2>Investment Portfolio Overview</h2>
                        <ul>
                `);

                // Populate the list with stock data
                data.forEach(stock => {
                    popUp.document.write(`
                        <li>
                            <span>${stock.stockTicker}</span> - 
                            <span>${stock.companyName}</span> - 
                            <span>$${stock.stockPrice.toFixed(2)}</span> - 
                            <span>${stock.quantity} shares</span> - 
                            <span>${stock.stockType}</span>
                        </li>
                    `);
                });

                // Close the list and body tags
                popUp.document.write(`
                        </ul>
                    </body>
                    </html>
                `);

                // Optionally, close the document to finish rendering
                popUp.document.close();
            } else {
                alert('ERROR: Popup window could not be opened.');
            }
    });
    }
});






async function submitForm(event){
    event.preventDefault();
    const form= document.getElementById('createAccountForm');
    const formData= new FormData(form);

    const data= {
        username: formData.get('user'),
        accountBalance: formData.get('bal')
    };

    balance= data.accountBalance;

    const response= await fetch('http://localhost:8080/account/saveAccount', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        // Close the modal
        closeModal();

        // Show success alert after closing the modal
        alert('Account created successfully!');
        setBalance(balance);
        updateUsername(data.username);
        updateBalance(data.accountBalance);
   
    
        // Reset the form after successful submission
        form.reset();

    } else {
        // Show failure alert
        alert('Failed to create account.');
    }
}






async function submitStock(event){
    event.preventDefault();
    const stockForm= document.getElementById('submitStockForm');
    const formData= new FormData(stockForm);

    const data= {
        stockTicker: formData.get('ticker'),
        companyName: formData.get('company'),
        stockPrice: formData.get('price'),
        quantity: formData.get('quant'),
        stockType: formData.get('type')
    };

    const totalCost= data.stockPrice*data.quantity;
    if(totalCost > balance){
        alert("Insufficient funds");
        return;
    }

    const response= await fetch('http://localhost:8080/stock/addStock', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        closeStockForm();


        balance-= totalCost;
        investment+= totalCost;

        updateBalance(balance);
        updateInvestment(investment);
        // Show success alert after closing the modal
        alert('Stock saved successfully!');
        addStockToTable(data);

        
    
        // Reset the form after successful submission
        stockForm.reset();

    } else {
        // Show failure alert
        alert('Failed to submit stock.');
    }
}

async function fetchLastPrice(event){
    event.preventDefault();
    
    const stockSymbol= document.getElementById('stockSymbol').value;

    console.log("Sending data:", { stockSymbol });



    fetch(`http://localhost:8080/twelve/getPastPrice?stockSymbol=${stockSymbol}`)
        .then(response=> response.json())
        .then(data=>{
            if(data){
                addToHistoryTable(stockSymbol, data);
            }else{
                alert('No data: '+stockSymbol);
            }
        })
        .catch(error=>{
            console.error('Error: ' +error);
        });
}

function addToHistoryTable(stockSymbol, lastPrice){
    const body= document.getElementById('historyTable-body');
    const row= document.createElement('tr');

    row.innerHTML= `
        <td>${stockSymbol}</td>
        <td>${lastPrice}</td>
    `;

    body.appendChild(row);
}




function addStockToTable(stock){
    const tableBody= document.querySelector('.stockTable tbody');

    const newRow= document.createElement('tr');

    newRow.innerHTML= `
        <td>${stock.stockTicker}</td>
        <td>${stock.companyName}</td>
        <td>$${parseFloat(stock.stockPrice).toFixed(2)}</td>
        <td>${parseInt(stock.quantity)}</td>
        <td>${stock.stockType}</td>
        <td><button onclick="deleteRow(this, '${stock.companyName}', ${parseFloat(stock.stockPrice)}, ${parseInt(stock.quantity)})" style="background-color: red; color: white;">Delete</button></td> <!-- Delete button -->

    `;

    tableBody.appendChild(newRow);

    updateTableTotals(stock);
}


function updateTableTotals(stock){
    totalPrice+= stock.stockPrice * stock.quantity;
    totalQuantity+= stock.quantity;
    uniqueCompanies.add(stock.companyName);

    document.getElementById('total-companies').textContent= uniqueCompanies.size;
    document.getElementById('total-price').textContent=`$${totalPrice}`;
    document.getElementById('total-quantity').innerText= `${totalQuantity}`;
}

function deleteRow(button, companyName, stockPrice, quantity){
    const row= button.parentNode.parentNode;

    row.parentNode.removeChild(row);

    const tableBody= document.querySelector('.stockTable tbody');
    const remainingRows= Array.from(tableBody.querySelectorAll('tr'));
    const isCompanyStillPresent= remainingRows.some(row=> row.cells[1].textContent === companyName);

    if(!isCompanyStillPresent){
        uniqueCompanies.delete(companyName);
    }
    totalPrice-= stockPrice*quantity;
    totalQuantity-= quantity;

    document.getElementById('total-companies').textContent= uniqueCompanies.size;
    document.getElementById('total-price').textContent=`$${totalPrice}`;
}

document.addEventListener("DOMContentLoaded", function(){

    var xhr= new XMLHttpRequest();
    xhr.open("GET" , "index.php", true);
    xhr.onload= function (){
        if(xhr.status === 200){
            var data= JSON.parse(xhr.responseText);
            var tableBody= document.getElementById("historyTable-body");

            tableBody.innerText= '';

            data.forEach(function (row){
                var tr= document.createElement("tr");
                tr.innerHTML= "<td>" + row.stock_symbol + "</td>" +
                               "<td>" + row.closing_price + "</td>" +
                               "<td>" + row.date + "</td>";
                tableBody.appendChild(tr);
            });
        }else{
            console.error("Request failed:" + xhr.status);
        }
    };
    xhr.send();

});




// async function deleteAllStock(stock){
//     const response= await fetch('', {
//         method: 'DELETE',
//         headers:{
//             'Content-Type' : 'application/json'
//         }
//     })

// }

function updateUsername(username){
    const usernameElement= document.getElementById('header-username');
    usernameElement.innerText= username;
}

function updateBalance(balance){
    const balanceElement= document.getElementById('account-balance');
    balanceElement.innerHTML=`
        <h1>$${balance}</h1>
    `;
}

function setBalance(balance){
    const initialBalance= document.getElementById('initial-balance');
    initialBalance.innerHTML= `
        <h1>$${balance}</h1>
    `;
}


function updateInvestment(totalInvestment){
    const balanceElement= document.getElementById('account-investment');
    balanceElement.innerHTML=`
        <h1>$${totalInvestment}</h1>
    `;
}


