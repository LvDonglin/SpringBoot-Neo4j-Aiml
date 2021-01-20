package com.example.java.controller;

import com.example.java.service.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: KnowledgeGraph
 * @Package: com.example.java.controller
 * @ClassName: ServiceController
 * @Author: 吕东霖
 * @Description: ${description}
 * @Date: 2021/1/2 20:09
 * @Version: 1.0
 */
@RestController
public class ServiceController {

    @Autowired
    private AllService allService;

    /*
    * 处理aiml请求
    * */
    @GetMapping("/analyze")
    public String aimlReply(HttpServletRequest request){
        String question = request.getParameter("question");

        return allService.analyzeQuest(question);
    }

    /*
    * 添加节点
    * */
    @PostMapping("/addNode")
    public boolean addPredisposition(HttpServletRequest request){
         int flag = Integer.parseInt(request.getParameter("flag"));
         String node = request.getParameter("node");
         if (flag == 0){
             allService.addPredisposition(node);
         }else if (flag == 1){
             allService.addSymptom(node);
         }else if (flag == 2){
             allService.addPreventNode(node);
         }else {
             allService.addTestNode(node);
         }
         return true;
    }

    /*
    * 查询节点
    * */
    @GetMapping("/queryNodes")
    public List<String> queryNodes(HttpServletRequest request){
        List<String> list = new ArrayList<>();
        int flag = Integer.parseInt(request.getParameter("flag"));
        if (flag == 0){
            list = allService.queryPredisposition();
        }else if ( flag == 1){
            list = allService.querySymptom();
        }else if ( flag == 2){
            list = allService.queryPreventNode();
        }else {
            list = allService.queryTestNode();
        }
        return list;
    }

    /*
    * 查询节点
    * */
    @PostMapping("/updateNode")
    public boolean updateNode(HttpServletRequest request){
        String name = request.getParameter("content");
        String newName = request.getParameter("new_content");
         allService.updateNode(name,newName);
         return true;
    }

    @GetMapping("/deleteNode")
    public boolean deleteNode(HttpServletRequest request){
        String name = request.getParameter("name");
        allService.deleteNode(name);
        return true;
    }
}
