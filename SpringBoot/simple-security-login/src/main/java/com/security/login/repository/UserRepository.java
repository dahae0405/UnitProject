package com.security.login.repository;


import com.security.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);

	//User save(User user);
}
