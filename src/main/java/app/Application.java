
package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = { "app" })
@EnableJpaRepositories(basePackages = { "dao" })
@ComponentScan(basePackages = {"app"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
		     }

}