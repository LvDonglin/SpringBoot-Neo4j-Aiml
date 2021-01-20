# SpringBoot+Neo4j+AIML实现机器人简单对话



### 开发环境：

后端：IDEA+SpringBoot框架

前端：HBuilder软件+JQuery+CSS+Html5

数据库：Neo4j

中文分词器：Handlp

语料库：AIMl



### 实现功能：

1. 简单的日常交流
2. 关于“脑卒中” 主题的知识问答
3. 对Neo4j图形数据库数据节点进行CRUD



### 主要思路：

输入问题后，后台会对该语句进行分词解析，提炼其中的关键词，如果与主题词有关，则会查询Neo4j数据库，否则直接匹配语料库，找到对应语句之后进行返回。



### 实现效果：

![image-20210120210133731](https://gitee.com/dongldl/my-cdn/raw/master/image/image-20210120210133731.png)