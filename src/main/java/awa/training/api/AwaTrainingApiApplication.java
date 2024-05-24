package awa.training.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(scanBasePackages = {"awa.training.api"}
		,exclude= {UserDetailsServiceAutoConfiguration.class})
@EnableJpaRepositories(basePackages = {"awa.training.api"})
@EntityScan(basePackages = {"awa.training.api"})
public class AwaTrainingApiApplication {
	
	public static void main(String[] args) {
	 SpringApplication.run(AwaTrainingApiApplication.class, args);
	
	}
}
