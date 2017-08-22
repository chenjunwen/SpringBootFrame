## 运行环境
jdk1.8+

## 运行
>直接运行(需将mysql和redis打开)
    
    $ ./mvnw spring-boot:run    #linux
    $ mvnw.cmd spring-boot:run  #windows
    或者使用idea/eclipse启动Application.class
    
    
IDE 导入ide后等待maven依赖下载完成后执行

## 目录说明
    ----------src/main/java
    ---------------com.tuling
    --------------------modal,controller,dao,service #不做过多说明，都懂
    --------------------config #配置文件类
    --------------------shiro #权限管理配置
    --------------------
    --------------------Application.java                    #项目启动类,直接运行启动项目
    ----------resources
    ---------------mappers                          #mybatis mapper配置文件
    ---------------tickdata.sql                     #系统首次启动执行的sql
    ---------------templates                        #模板文件(freemarker)
    ---------------static                           #静态文件目录
    ---------------application.yml                  #应用主配置,数据源啥的都在这里

## 配置springboot热启动
1. 设置idea ctrl+alt+s 选择Build,Exection,Deployment->Compiler-> 勾选Build project automatically
2. 设置idea ctrl+alt+shift+/  选择Registry -> 勾选compiler.automaket.allow.when.app.running