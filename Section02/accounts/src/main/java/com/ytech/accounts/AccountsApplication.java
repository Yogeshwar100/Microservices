package com.ytech.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Description;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.core.Version;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
/*
@ComponentScans({
    @ComponentScan(basePackages = "com.ytech.package"),
    @ComponentScan(basePackages = "com.ytech.package")
})
@EnableJpaRepositories("com.ytech.accounts.repository")
@EntityScan("com.ytech.accounts.model")
*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "EazyBank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Yogeshwar Jamdade",
						email = "yogeshwar473@gmail.com",
						url = "https://www.eazybytes.com"
						),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.eazybytes.com"
						)
				),
		 externalDocs = @ExternalDocumentation(
			        description = "EazyBank Accounts microservice REST API Documentation",
			        url = "https://www.eazybytes.com/swagger-ui.html"
			    )
			)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
