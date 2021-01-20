package com.example.java.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: KnowledgeGraph
 * @Package: com.example.java.controller
 * @ClassName: LoginController
 * @Author: 吕东霖
 * @Description: ${description}
 * @Date: 2021/1/5 13:39
 * @Version: 1.0
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public boolean login(HttpServletRequest request){
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if ( name.equals("admin")&&password.equals("123456")){
            return true;
        }else {
            return false;
        }
    }
}
