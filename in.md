# 基于Spring Boot网站架构参考

文件说明：

|  文件名称   | 文件说明  |
|  ----  | ----  |
| pom.xml  | 项目配置文件 |
| in.md | 开发参考说明文档 |
| application.yml  | SpringBoot开发环境配置文件(公共环境下配置) |
| application-pro.yml | SpringBoot开发环境配置文件(生产环境下配置) |
| application-dev.yml | SpringBoot开发环境配置文件(开发环境下配置) |
| logback-spring.xml | 日志模块配置文件 |
| 404.html | 404错误页面 |
| 505.html | 505错误页面 |
| IndexController.java | Web控制器 |
| ControllerExceptionHandler.java | BeBug拦截器 |
| NotFoundException.java | 异常类，业务相关（如果没有页面报错404） |


项目配置(Jar包)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.2</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.cxkj</groupId>
    <artifactId>bolg</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>bolg</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
        <tymeleaf.version>3.0.2.RELEASE</tymeleaf.version>
        <tymeleaf-layout-dialect.version>2.1.1</tymeleaf-layout-dialect.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```
### Application.yml配置文件

thymeleaf模板配置
```yml
spring:
  thymeleaf: 
    mode: HTML
```
数据库相关连接配置

```yml
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/bolg?useSSL=false&useUnicode=true&characterEncoding=utf-8
    username: root
    password: 1917723401Syc
```
JPA的连接配置
```yml
    jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```
日志配置
```yml
logging:
  level:
    root: info
    com.cxkj: debug
  file:
    name: log/blog.log
```
日志的细节操作(logback-spring.xml)
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>
    <!--包含Spring boot对logback日志的默认配置-->
    <include resource="org/springframework/boot/logging/logback/defaults.xml" />
    <property name="LOG_FILE" value="${LOG_FILE:-${LOG_PATH:-${LOG_TEMP:-${java.io.tmpdir:-/tmp}}}/spring.log}"/>
    <include resource="org/springframework/boot/logging/logback/console-appender.xml" />

    <!--重写了Spring Boot框架 org/springframework/boot/logging/logback/file-appender.xml 配置-->
    <appender name="TIME_FILE"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
        <file>${LOG_FILE}</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_FILE}.%d{yyyy-MM-dd}.%i</fileNamePattern>
            <!--保留历史日志一个月的时间-->
            <maxHistory>30</maxHistory>
            <!--
            Spring Boot默认情况下，日志文件10M时，会切分日志文件,这样设置日志文件会在100M时切分日志
            -->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>

        </rollingPolicy>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="TIME_FILE" />
    </root>

</configuration>
        <!--
            1、继承Spring boot logback设置（可以在appliaction.yml或者application.properties设置logging.*属性）
            2、重写了默认配置，设置日志文件大小在100MB时，按日期切分日志，切分后目录：

                blog.2017-08-01.0   80MB
                blog.2017-08-01.1   10MB
                blog.2017-08-02.0   56MB
                blog.2017-08-03.0   53MB
                ......
        -->
```
公共环境(开发环境指定为dev)
```yml
spring:
  thymeleaf:
    mode: HTML
  profiles:
    active: dev
```
开发环境
```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/bolg?useSSL=false&useUnicode=true&characterEncoding=utf-8
    username: root
    password: 1917723401Syc

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

logging:
  level:
    root: info
    com.cxkj: debug
  file:
    name: log/blog-dev.log
```
生产环境
```yml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/bolg?useSSL=false&useUnicode=true&characterEncoding=utf-8
    username: root
    password: 1917723401Syc

  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true

logging:
  level:
    root: warn
    com.cxkj: info
  file:
    name: log/blog-pro.log

server:
  port: 808
```
### 异常处理

IndexController.java Web控制器
```java
package com.cxkj.bolg.web;

import com.cxkj.bolg.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  Created by Arvin on 2021/2/3.
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        String bolg = null;
        if (bolg == null){
            throw  new NotFoundException("博客不存在");
        }
        return "index";
    }

}
```
ControllerExceptionHandler.java BeBug拦截器
```java
package com.cxkj.bolg.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 *  Created by Arvin on 2021/2/3.
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        logger.error("Requst URL : {},Exception : {}", request.getRequestURI(),e);

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURI());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
```
error.html BeBug页面
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">

    <head>
        <meta charset="UTF-8">
        <title>BeBug</title>
    </head>

    <body>
        <h1>BeBug</h1>
        <div>
            <div th:utext="'&lt;!--'" th:remove="tag"></div>
            <div th:utext="'Failed Request URL : ' + ${url}" th:remove="tag"></div>
            <div th:utext="'Exception message : ' + ${exception.message}" th:remove="tag"></div>
            <ul th:remove="tag">
                <li th:each="st : ${exception.stackTrace}" th:remove="tag"><span th:utext="${st}" th:remove="tag"></span></li>
            </ul>
            <div th:utext="'--&gt;'" th:remove="tag"></div>
        </div>
    </body>

</html>
```
NotFoundException.java 异常类，业务相关（如果没有页面报错404）
```java
package com.cxkj.bolg;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  Created by Arvin on 2021/2/3.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
```
