package com.example.studentApi;

import com.example.studentApi.entity.Users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApiApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(StudentApiApplication.class, args);

	}

}
