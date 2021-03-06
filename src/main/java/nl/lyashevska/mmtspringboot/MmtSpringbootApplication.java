package nl.lyashevska.mmtspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

// @SpringBootApplication (exclude = {SecurityAutoConfiguration.class})

// instruct Spring on where to find the classes I marked as @Component
@ComponentScan(basePackages = "nl.lyashevska")
@SpringBootApplication ()

public class MmtSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MmtSpringbootApplication.class, args);
	}

}
