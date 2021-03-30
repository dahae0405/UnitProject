package com.security.login.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestApiController2 {

    @GetMapping(value={"","/"})
    public @ResponseBody String goMain(){
        System.out.println("도메인 홈 입니다.");
        return "<h1>최초페이지<h1>";
    }

    @PostMapping(value={"/post"})
    public @ResponseBody String goPost( HttpServletResponse bodyData ){
        System.out.println("/post - 컨트롤러 ");
        System.out.println("bodyData = "+bodyData);
        return "<h1>Post<h1>";
    }




}
