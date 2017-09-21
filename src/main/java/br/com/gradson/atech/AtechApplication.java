package br.com.gradson.atech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("br.com.gradson.atech")
@EntityScan("br.com.gradson.atech")
@EnableJpaRepositories("br.com.gradson.atech")
@EnableSwagger2
@Configuration
public class AtechApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtechApplication.class, args);
	}
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(
           RequestHandlerSelectors.any()).paths(PathSelectors.ant("/api/**")).build();
    }

}
