package net.walecode.fashionblogrestapi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Fashion Blog App REST APIs",
                description = "Spring Boot Fashion Blog App REST APIs Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Olawale",
                        email = "hellowaleagboola@gmail.com",
                        url = "https://github.com/Realwale"
                ),
                license = @License(
                        name = "Apache 2.8",
                        url = "https://github.com/Realwale/license"

                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Fashion Blog App REST APIs Documentation"
        )
)
public class FashionBlogRestApiApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(FashionBlogRestApiApplication.class, args);
    }

}
