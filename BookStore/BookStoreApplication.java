package com.BookStore;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.BookStore.entity.UserModel;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper ()
	{
		return new ModelMapper();
	}
	
	@Bean
	public UserModel getUser() {
		return new UserModel();
	}
}
