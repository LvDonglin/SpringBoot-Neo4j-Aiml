package com.example.java.service;

import com.example.java.aiml.AIMLParser;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * @ProjectName: KnowledgeGraph
 * @Package: com.example.java.service
 * @ClassName: PredispositionService
 * @Author: 吕东霖
 * @Description: ${description}
 * @Date: 2021/1/3 20:00
 * @Version: 1.0
 */
@Service
public class AllService {

    @Autowired
    private AIMLParser ai;

    private Driver driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "123456" ) );
    private Session session = driver.session();
    /*
    * 添加诱因节点
    *
    * */
    public void addPredisposition(String ps){
        session.run( "CREATE (a:Predisposition {name: {name}})",
                parameters( "name", ps) );
        session.run("MATCH (a:Predisposition),(b:illness) WHERE a.name = {name} AND b.name = '脑卒中' CREATE (a)-[r:LeadTo] -> (b) RETURN r",
                parameters( "name", ps));

    }

    /*
    * 添加症状节点
    * */
    public void addSymptom(String Symptom){
        session.run( "CREATE (a:Symptom {name: {name}})",
                parameters( "name", Symptom) );
        session.run("MATCH (a:Symptom),(b:illness) WHERE a.name = {name} AND b.name = '脑卒中' CREATE (b)-[r:Assume] -> (a) RETURN r",
                parameters( "name", Symptom));
    }

    /*
    * 添加预防方法节点
    * */
    public void  addPreventNode(String prevent){
        session.run( "CREATE (a:Prevent {name: {name}})",
                parameters( "name", prevent) );
        session.run("MATCH (a:Prevent),(b:illness) WHERE a.name = {name} AND b.name = '脑卒中' CREATE (a)-[r:Prevent] -> (b) RETURN r",
                parameters( "name",prevent));
    }

    /*
    * 添加测试方法节点
    * */
    public void addTestNode(String test){
        session.run( "CREATE (a:Test {name: {name}})",
                parameters( "name", test) );
        session.run("MATCH (a:Test),(b:illness) WHERE a.name = {name} AND b.name = '脑卒中' CREATE (a)-[r:Test] -> (b) RETURN r",
                parameters( "name",test));
    }

    /*
     * 查询诱因节点
     * */
    public List<String> queryPredisposition(){
        List<String> list = new ArrayList<>();
        StatementResult result = session.run( "match (p:Predisposition) return p.name As name");
        while ( result.hasNext() )
        {
            Record record = result.next();
            list.add(record.get("name" ).asString());
        }
        return list;
    }

    /*
     * 查询早期症状节点
     * */
    public List<String> querySymptom(){
        List<String> list = new ArrayList<>();
        StatementResult result = session.run( "match (p:Symptom) return p.name As name");
        while ( result.hasNext() )
        {
            Record record = result.next();
            list.add(record.get("name" ).asString());
        }
        return list;
    }
    /*
     * 查询预防节点
     * */
    public List<String> queryPreventNode(){
        List<String> list = new ArrayList<>();
        StatementResult result = session.run( "match (p:Prevent) return p.name As name");
        while ( result.hasNext() )
        {
            Record record = result.next();
            list.add(record.get("name" ).asString());
        }
        return list;
    }

    /*
     * 查询预防节点
     * */
    public List<String> queryTestNode(){
        List<String> list = new ArrayList<>();
        StatementResult result = session.run( "match (p:Test) return p.name As name");
        while ( result.hasNext() )
        {
            Record record = result.next();
            list.add(record.get("name" ).asString());
        }
        return list;
    }



    /*
     * 删除节点
     * */
    public void deleteNode(String name){

        session.run( "MATCH (n { name: {name} }) DETACH DELETE n",parameters( "name",name));

    }

    /*
    * 修改节点
    * */
    public void updateNode(String name, String newName){
        /*try{
            session.run("MATCH (n { name: {name}) set n.name = {newName} return n",
                    parameters("name",name,"newName",newName));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }*/
        session.run("MATCH (n { name: {name}}) set n.name = {newName} return n",
                parameters("name",name,"newName",newName));
    }

    /*
    * AIML 处理方法
    * */
    public String aimlReply(String question){

        ai.CreateBot();
        // Here the bot's tree file so that it will know it aiml files
        ai.setTree("E:\\SpringCloud_Projects\\KnowledgeGraph\\src\\main\\java\\com\\example\\java\\brain\\files\\tree.xml");
        // Setting bot's info. there are many you can check in the class AIML.java
        ai.setInfo("name", "Alice","bot");
        ai.setInfo("master", "Karrar S. Honi","bot");
        ai.setInfo("birthday", "2017/7/29","bot");

        return ai.reply(question);
    }

    public String analyzeQuest(String question){
        String result = "";
        NLPTokenizer.ANALYZER.enableCustomDictionary(true); // 使用用词典分词。
        List<Term> list = NLPTokenizer.segment(question);

        if (!list.get(0).word.equals("脑卒中")){
            result = aimlReply(question);
        }else {
            if (list.get(2).word.equals("诱发")){
                result = queryPredisposition().toString();
            }else if (list.get(2).word.equals("早期")){
                result = querySymptom().toString();
            }else if (list.get(2).word.equals("预防")){
                result = queryPreventNode().toString();
            }else {
                result = queryTestNode().toString();
            }
            result = result.substring(1,result.length()-1);
        }

        return result;
    }


}
