package com.security.login.Filter;



import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class MyFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("필터1");

        //getURL 하기위해 Http객체로 만든다
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if( req.getMethod().equals("POST") ){
            // test > postman > (Post + /api)
            System.out.println("POST요청됨");
            /** Test header.Authorization
             * Input : postman > (method : post , url : /api/post , header.Authorization : hello )
             * out : IntelliJ console > sout(headerAuth) : hello
             * */
            String headerAuth = req.getHeader("Authorization"); // 해더 기본속성 Authorization
            System.out.println("headerAuth = "+headerAuth);
            /** Test header.Authorization
             * Input : postman > (method : post , url : /api/post , header.Authorization : cos )
             * Input : cos ? 체인 타도록 : "인증안됨";
             * out : postman > header.Authorization : cos ? O (탐) : X(인증안됨 출력)
             * */
            if( headerAuth.equals("cos") ){
                chain.doFilter( request, response);
            }else{
                PrintWriter out = res.getWriter();
                out.print("인증안됨");
            }

        }


    }
}
