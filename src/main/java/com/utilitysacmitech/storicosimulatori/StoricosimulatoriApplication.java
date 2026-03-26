package com.utilitysacmitech.storicosimulatori;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class StoricosimulatoriApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoricosimulatoriApplication.class, args);
	}

}
