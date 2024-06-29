package com.stock_api.stocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocksApplication.class, args);
	}


	
	// @Bean
	// public WebMvcConfigurer corsConfigurer(){
	// 	return new WebMvcConfigurer() {
	// 		@Override
	// 		public void addCorsMappings(CorsRegistry registry){
	// 			registry.addMapping("/api/**")
	// 			.allowedOrigins("http://localhost:8000")
	// 			.allowedMethods("GET", "POST", "PUT", "DELETE")
	// 			.allowedHeaders("*");
	// 		}
	// 	};
	// }

}
