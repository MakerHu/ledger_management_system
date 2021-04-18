package com.bjtu.ledger_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LedgerManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LedgerManagementSystemApplication.class, args);
	}

	@Bean
    public BCryptPasswordEncoder encoding(){
        return new BCryptPasswordEncoder();
    }
}
