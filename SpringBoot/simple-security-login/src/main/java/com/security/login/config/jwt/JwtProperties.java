package com.security.login.config.jwt;

public interface JwtProperties {
	String SECRET = "cos"; 		 	 // 보이스봇서버를 식별할 암호키값
	int EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
