package com.example.assessment;

import com.example.assessment.entity.User;
import com.example.assessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class AssessmentApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}

	@Bean
	public void init() {
		User user1 = new User("user1@gmail.com", "User-1");
		User user2 = new User("user2@gmail.com", "User-2");
		User user3 = new User("user3@gmail.com", "User-3");
		User user4 = new User("user4@gmail.com", "User-4");
		User user5 = new User("user5@gmail.com", "User-5");

		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
	}
}


