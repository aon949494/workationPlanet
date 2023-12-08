package org.zerock.p01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class P01Application {

	public static void main(String[] args) {
		SpringApplication.run(P01Application.class, args);
	}

}
