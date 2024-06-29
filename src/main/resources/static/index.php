<?php

$user= 'root';
$password= 'Basketball2013$';
$database= 'Stocks_db';

$servername= 'localhost:3306';
$mysqli = new mysqli($servername. $user, $password, $database);

if ($mysqli->connect_error) {
    die('Connect Error (' . $mysqli->connect_errno . ') ' . $mysqli->connect_error);
}

$sql = "SELECT * FROM stock_price_history";
$res= $mysqli->query($sql);


if( $res && $res->num_rows > 0){
    $data = array();

    while($row = $res->fetch_assoc()){
        $data[] = $row;
    }
    echo json_encode($data);
} else {
    echo json_encode(array());
}

$res->free();

$mysqli->close();



