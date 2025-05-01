package com.think.rest_demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;


@OpenAPIDefinition(
	info = @Info(
        title = "Vendor & Book API",
        description = "API documentation for both Vendor and Book services",
        version = "1.0",
        termsOfService = "Standard Terms",
        contact = @Contact(
            name = "Sayak Pal",
            url = "http://vendorbookservice.com",
            email = "contact@vendorbook.com"
        ),
        license = @License(
            name = "Vendor Book App License",
            url = "http://vendorbookservice.com"
        )
    )
)


@SpringBootApplication
@EntityScan(basePackages = "com.think.rest_demo.model")
@EnableJpaRepositories(basePackages = "com.think.rest_demo.repository")
public class RestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}
}
 