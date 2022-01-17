package br.com.totvs.eficienciacombustivel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EficienciacombustivelApplication {

	public static void main(String[] args) {
		SpringApplication.run(EficienciacombustivelApplication.class, args);
	}

}
