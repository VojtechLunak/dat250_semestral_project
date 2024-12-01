package hvl.dat250;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class Dat250Application {

	public static void main(String[] args) {
		SpringApplication.run(Dat250Application.class, args);
	}

}
