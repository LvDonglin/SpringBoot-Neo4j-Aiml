package com.example.java;

import com.example.java.aiml.AIMLParser;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import org.neo4j.driver.v1.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

import static org.neo4j.driver.v1.Values.parameters;

@SpringBootApplication
public class KnowledgeGraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgeGraphApplication.class, args);

        /*Driver driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "123456" ) );
        Session session = driver.session();
        session.run( "CREATE (a:Person {name: {name}, title: {title}})",
                parameters( "name", "Arthur001", "title", "King001" ) );

        StatementResult result = session.run( "MATCH (a:Person) " +
                        "RETURN a.name AS name, a.title AS title" );
        while ( result.hasNext() )
        {
            Record record = result.next();
            System.out.println( record.get( "title" ).asString() + " " + record.get( "name" ).asString() );
        }
        session.close();
        driver.close();
*/
       /* NLPTokenizer.ANALYZER.enableCustomDictionary(true); // 使用用词典分词。
        List<Term> list = NLPTokenizer.segment("脑卒中的诱发因素有哪些");
        System.out.println(NLPTokenizer.segment("脑卒中的诱发因素有哪些"));
        System.out.println(list.get(0).word+"==="+list.get(1).word);*/
    }

}
