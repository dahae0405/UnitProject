package com.security.login.config;

import com.security.login.Filter.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;

/**
 *  A. 시큐리티가 custum설정파일을 사용하도록 변경
 *      ㄴ B. 시큐리티가 H2콘솔을 클라이언트 웹브라우저에서 보도록 허용.
 *      ㄴ C. 토큰사용을 위한 설정
 *
 * */
@Configuration      // A1. Spring에서 관리할 수 있게 Annotation걸기 ( Spring Data로 선언 )
@EnableWebSecurity  // A2. 시큐리티 사용하겠다.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    // A3. 시큐리티 커스텀 - WebSecurityConfigurerAdapter를 받아 사용했더니, 시큐리티가 가로채지않는다.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
                // D. 코스값 필터제어
                .addFilterBefore( new MyFilter(), SecurityContextPersistenceFilter.class)
                // C1. 세션방식 비활성, JWT 방식 사용.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // C4. 도메인 접근,권한
                .antMatchers("/api/v1/user/**") // 이쪽으로 들어오면
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 권한있는애만 접근
                .antMatchers("/api/v1/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**").access("hasRole('ROLE_USER') ")
                .anyRequest().permitAll() // 그외 요청은 모두 허용
                // B1. 도메인접근 허용 - h2 콘솔을 클라이언트(웹브라우저)에 접근허용.
                //.antMatchers("/h2-console/**").permitAll()
                .and()
                // B2,C2. h2콘솔 - 시큐리티의 기본csrf검증 해제
                .csrf().disable()
                // B3. h2콘솔 - H2Console은 iframe을 사용하기 때문에 Header의 X-Frame-Options를 비활성화
                .headers().frameOptions().disable()
                .and()
                // C3. response가 view -> data
                .formLogin().disable()
                .httpBasic().disable()
                // C4. cors 필터 적용
                .addFilter( this.corsFilter() );   // @CrossOrigin (인증X) , 시큐리티 필터에 인증 (O)

    }


    // 시큐리티 필터입니다. ㅎㅎ
    public CorsFilter corsFilter(){
        System.out.println("cors필터야");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("api/**",config);
        return new CorsFilter( source );
    }


}
