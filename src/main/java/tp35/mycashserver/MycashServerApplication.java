package tp35.mycashserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MycashServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycashServerApplication.class, args);
	}
}
