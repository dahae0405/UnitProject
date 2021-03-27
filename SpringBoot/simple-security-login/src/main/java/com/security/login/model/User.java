package com.security.login.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dNbr;

    private String id;
    private String password;
    private String roles;       // rst = Admin,Manager

    public List<String> getRoleList(){
        if( this.roles.length() > 0 ){
            return Arrays.asList( this.roles.split(",") );
        }
        return new ArrayList<>(); //list는 인터페이스 , ArrayList는 구현
    }


}
