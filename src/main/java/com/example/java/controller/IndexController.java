package com.example.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @ProjectName: KnowledgeGraph
 * @Package: com.example.java.controller
 * @ClassName: IndexController
 * @Author: 吕东霖
 * @Description: ${description}
 * @Date: 2021/1/2 20:20
 * @Version: 1.0
 */
@Controller
public class IndexController {


    @GetMapping("/")
    public String sayHello(){
        return "chat";
    }
}
