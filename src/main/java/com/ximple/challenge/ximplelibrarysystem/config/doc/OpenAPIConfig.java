package com.ximple.challenge.ximplelibrarysystem.config.doc;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI myOpenAPI() {
		Contact contact = new Contact();
		contact.setEmail("tsglinglani@gmail.com");
		contact.setName("Thiago S. Glinglani");
		contact.setUrl("https://www.linkedin.com/in/thiago-glinglani");

		Info info = new Info()
				.title("XIMPLE ONLINE LIBRARY SYSTEM API")
				.version("0.0.1")
				.contact(contact)
				.description("This API exposes endpoints to manage ONLINE LIBRARY SYSTEM.");

		SecurityScheme securityScheme = new SecurityScheme()
				.name("BearerAuth")
				.type(SecurityScheme.Type.HTTP)
				.scheme("bearer")
				.bearerFormat("JWT");


		return new OpenAPI().info(info).addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
				.schemaRequirement("BearerAuth", securityScheme);
	}
}
