package com.security.login;

import com.security.login.model.User;
import com.security.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApplication implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("jane","$2a$10$iCUytkJ0XgvNVo9ppTwONudkw3kV3jL5BdTClZHg0QyIrSmpbL8/i","ROLE_ADMIN"));

        userRepository.save(new User("user","$2a$10$iCUytkJ0XgvNVo9ppTwONudkw3kV3jL5BdTClZHg0QyIrSmpbL8/i","ROLE_USER"));


        System.out.println(" 인서트 확인 "+userRepository.findByUsername("jane"));
    }

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

}
