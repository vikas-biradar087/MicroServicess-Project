package com.api.user;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient

public class UserServiceeApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceeApplication.class, args);
	}

//	@Bean
//	public ModelMapper modelMapper(){
//		return new ModelMapper();
//	}
//
//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}

}
