package com.dbc.prova;

import com.dbc.prova.domain.User;
import com.dbc.prova.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User(null, "admin@gmail.com", "$2a$10$Ip7h.NKlnffrxADTWV1noeviQNGNbR4B1JbU5SAKP1pM/ihyTGiuO", null);

		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			return;
		}

		userRepository.save(user);
	}

}
