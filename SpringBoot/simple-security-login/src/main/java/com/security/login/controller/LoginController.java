//package com.security.login.controller;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//public class LoginController {
//    @PostMapping(value={"/login"})
//    public @ResponseBody
//    String loginProc( HttpServletRequest resData ){
//        System.out.println("/post - 컨트롤러 ");
//        System.out.println("resData = "+resData.getParameter("id") );
//        return "<h1>loginProc<h1>";
//    }
//
//}
