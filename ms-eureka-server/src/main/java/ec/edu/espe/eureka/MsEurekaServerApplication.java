package ec.edu.espe.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaServer
public class MsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEurekaServerApplication.class, args);
	}

}
