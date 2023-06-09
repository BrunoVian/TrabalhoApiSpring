package br.com.unipar.TrabalhoWebSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TrabalhoWebSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoWebSpringApplication.class, args);
	}


	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(geraInfoSwsagger())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.unipar.TrabalhoWebSpring"))
				.paths(PathSelectors.any())
				.build();
	}

	public ApiInfo geraInfoSwsagger() {

		return new ApiInfo("Trabalho Spring Boot - Unipar",
				"Criado no Terceiro Ano de ADS 2023 - Unipar",
				"1.0", null, "Bruno Vian e Mateus Pessini Scherer", null, null);
	}

}
