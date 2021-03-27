package com.security.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *  A. 시큐리티가 custum설정파일을 사용하도록 변경
 *      ㄴ B. 시큐리티가 H2콘솔을 클라이언트 웹브라우저에서 보도록 허용.
 *
 * */
@Configuration      // A1. Spring에서 관리할 수 있게 Annotation걸기 ( Spring Data로 선언 )
@EnableWebSecurity  // A2. 시큐리티 사용하겠다.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    // A3. 시큐리티 커스텀 - WebSecurityConfigurerAdapter를 받아 사용했더니, 시큐리티가 가로채지않는다.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                // B1. 도메인접근 허용 - h2 콘솔을 클라이언트(웹브라우저)에 접근허용.
                .antMatchers("/h2-console/**").permitAll()
                .and()
                // B2. h2콘솔 - 시큐리티의 기본csrf검증 해제
                .csrf().disable()
                // B3. h2콘솔 - H2Console은 iframe을 사용하기 때문에 Header의 X-Frame-Options를 비활성화
                .headers().frameOptions().disable();







    }

}
