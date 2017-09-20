package br.com.gradson.atech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("br.com.gradson.atech")
@EntityScan("br.com.gradson.atech")
@EnableJpaRepositories("br.com.gradson.atech")
public class AtechApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtechApplication.class, args);
	}
}
