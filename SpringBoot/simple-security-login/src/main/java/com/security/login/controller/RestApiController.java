package com.security.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping(value={"","/"})
    public @ResponseBody String goMain(){
        System.out.println("도메인 홈 입니다.");
        return "<h1>최초페이지<h1>";
    }


}
