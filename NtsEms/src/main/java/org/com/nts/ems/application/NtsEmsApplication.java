package org.com.nts.ems.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.com.nts.ems")
@EnableJpaRepositories(basePackages = "org.com.nts.ems.repository")
@EntityScan(basePackages = "org.com.nts.ems.entity")
public class NtsEmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NtsEmsApplication.class, args);
	}

}
