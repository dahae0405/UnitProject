package com.security.login.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.h2.tools.Server;

import javax.activation.DataSource;
import java.sql.SQLException;

/**
 *  1. ( IntelliJ_Database ) H2DB 생성
 *      ㄴ ( 커넥션 정보 ) 인텔 DB에서 커넥션 정보는 자동생성해준다. 기본포트는 8090성
 *
 *
 *  2. ( Intellij_springBoot ) H2DB 생성.
 *  ㄴ ( 생성 이유 ) TCP를 사용하면 "외부 클라이언트 (위 1번)에서 통신"가능하다.
 *  ㄴ config에서 h2TcpServer 관리
 *      ㄴ ( connect 정보 ) 커넥션정보는 yml파일로 가져와 매핑
 *      ㄴ ( spring Data ) 스프링이 관리할 수 있도록 @Configuration
 *      ㄴ ( 사용할 Object )
 *          ㄴ ( import h2 Server ) 객체Server ->  다양한 라이브러리 중 H2라이브러리 Server로
 *              ㄴ ( build.gradle ) bundle에서 h2내부객체를 사용 할 수 있도록 scope는 implement로 변경.
 *          ㄴ ( import hikari datasource )
 *
 *
 *  3. 클라이언트 인텔콘솔은 위1번에 붙기
 *      ㄴ ( 중 요 application.yaml ) "커넥션 정보를 같게"하여 같은 곳을 바라보도록 한다.
 *          ㄴ ( 추가 샛팅 - 접근 ) mem이 아닌 remote
 *          ㄴ ( 추가 샛팅 - jdbc-url ) 이건 mem
 *
 *  etc. connection 정보
 *      ㄴ yaml 순서도 중요 ㅠㅠ - database(lv1) -> jpa(lv1) -> h2(lv1)
 *          ㄴ ( 접근1 - h2 ) TCP로 외부 클라이언트접근 허용
 *          ㄴ ( 접근2 - 시큐리티 ) hikari CP로 우회
 *          ㄴ ( 포트 ) 기본포트 9092
 *          ㄴ ( jdbc-url:메모리 ) :mem
 *
 *
* */

@Configuration
public class H2TcpSeverConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari") // yml의 설정값을 Set한다.
    public HikariDataSource h2TcpServer() throws SQLException {
        Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",
                "-ifNotExists",
                "-tcpPort", "9092").start();
        return new HikariDataSource();
    }

}
