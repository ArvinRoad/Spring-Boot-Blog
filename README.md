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
| error.html | BeBug页面 |
| blogs.html | 后台管理页面 |
| blogs-input.html | 博客后台发布页面 |
| type.html | 后台分类管理页面 |
| type-input.html | 后台分类新增页面 |
| about.html | 关于我页面 |
| archives.html | 归档页面 |
| blog.html | 博客详情页面 |
| index.html | 博客首页 |
| tags.html | 标签页面 |
| type.html | 分类页面 |
| login.html | 登录页面 |
| _fragments.html | 动态页面 定义Thymeleaf片段 |
| IndexController.java | Web控制器 |
| LoginController.java | WEB登录模块控制器 |
| BlogController.java | Blog后台页面权限过滤管理类 |
| TypeController.java | Web层分类模块操作 |
| TagController.java | Web层标签模块操作 |
| LongInterceptor.java | Blog后台页面权限(登录过滤)类 |
| WebConfig.html | Blog后台页面权限(拦截配置) 类|
| ControllerExceptionHandler.java | BeBug拦截器 |
| NotFoundException.java | 异常类，业务相关（如果没有页面报错404） |
| Blog.java | Blog实体类 |
| Type.java | 分类实体类 |
| Tag.java | 标签实体类 |
| Comment.java | 评论实体类 |
| User.java | 用户实体类 |
| UserService.java | User登录业务逻辑处理接口类 |
| UserServiceImpl.java | User登录业务逻辑处理实现类 |
| TypeService.java | 分类业务逻辑处理接口 |
| TypeServiceImpl.java | 分类业务逻辑处理实现类 |
| TagService.java | 标签业务逻辑处理接口 |
| BlogService.java | 博客业务逻辑处理接口 |
| BlogServiceImpl.java | 博客业务逻辑处理实现类 |
| TagServiceImpl.java | 标签业务逻辑处理实现类 |
| UserRepository.java | 引用SpringJPA SQL操作接口 |
| TypeRepository.java | 分类业务处理 SQL操作接口 |
| TagRepository.java | 标签业务处理 SQL操作接口 |
| BlogRepository.java | 博客业务处理 SQL操作接口 |
| BlogQuery.java | 博客搜索查询类 |
| MD5Utils.java | MD5加密类 |

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
    <artifactId>blog</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>blog</name>
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
后面导入的Jar包(新版本原因曾经的Jar不包含了)
```xml
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
</dependency>

<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>6.0.13.Final</version>
</dependency>
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
    url: jdbc:mysql://localhost:3306/blog?useSSL=false&useUnicode=true&characterEncoding=utf-8
    username: root
    password: 123456789
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
    url: jdbc:mysql://localhost:3306/blog?useSSL=false&useUnicode=true&characterEncoding=utf-8
    username: root
    password: 123456789

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
    url: jdbc:mysql://localhost:3306/blog?useSSL=false&useUnicode=true&characterEncoding=utf-8
    username: root
    password: 123456789

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
package com.cxkj.blog.web;

import com.cxkj.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  Created by Arvin on 2021/2/3.
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        String blog = null;
        if (blog == null){
            throw  new NotFoundException("博客不存在");
        }
        return "index";
    }

}
```
ControllerExceptionHandler.java BeBug拦截器
```java
package com.cxkj.blog.handler;

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
package com.cxkj.blog;

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

### 日志处理

LogAspect.java 接口记录日志AOP类
```java
package com.cxkj.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 *  Created by Arvin on 2021/2/4.
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.cxkj.blog.web.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}",requestLog);
    }

    @After("log()")
    public void doAfter(){
        //logger.info("------ doAfter ------");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("Result : {}" + result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
```
IndexController.java
```java
package com.cxkj.blog.web;

import com.cxkj.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *  Created by Arvin on 2021/2/3.
 */
@Controller
public class IndexController {
    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id,@PathVariable String name){
            /*String blog = null;
            if (blog == null){
                throw  new NotFoundException("博客不存在");
            }*/
        System.out.println("------ Index ------");
        return "index";
    }
}
```

### 页面处理
IndexController.java Web控制器
```java
package com.cxkj.blog.web;

import com.cxkj.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *  Created by Arvin on 2021/2/3.
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
            /*String blog = null;
            if (blog == null){
                throw  new NotFoundException("博客不存在");
            }*/
        return "index";
    }

}
```
动态页面 定义Thymeleaf片段
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">

    <head th:fragment="head(title)">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title th:replace="${title}">详情</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
        <link rel="stylesheet" href="../static/css/typo.css" th:href = "@{/css/typo.css}">
        <link rel="stylesheet" href="../static/css/animate.css" th:href = "@{/css/animate.css}">
        <link rel="stylesheet" href="../static/lib/prism/prism.css" th:href = "@{/lib/prism/prism.css}">
        <link rel="stylesheet" href="../static/lib/tocbot/tocbot.css" th:href = "@{/lib/tocbot/tocbot.css}">
        <link rel="stylesheet" href="../static/css/meCs.css" th:href = "@{/css/meCs.css}">
    </head>

    <body>

        <!--导航-->
        <nav th:fragment="menu(n)" class="ui inverted attached segment m-padded-tb-mini m-shadow-small">
            <div class="ui container">
                <div class="ui inverted secondary stackable menu">
                    <h2 class="ui teal header item">Guest Island</h2>
                    <a href="#" class="m-item item m-mobile-hide" th:classappend="${n==1} ? 'active'"><i class="home icon"></i>首页</a>
                    <a href="#" class="m-item item m-mobile-hide" th:classappend="${n==2} ? 'active'"><i class="idea icon"></i>分类</a>
                    <a href="#" class="m-item item m-mobile-hide" th:classappend="${n==3} ? 'active'"><i class="tags icon"></i>标签</a>
                    <a href="#" class="m-item item m-mobile-hide" th:classappend="${n==4} ? 'active'"><i class="clone icon"></i>归档</a>
                    <a href="#" class="m-item item m-mobile-hide" th:classappend="${n==5} ? 'active'"><i class="info icon"></i>关于我</a>
                    <!--搜索栏-->
                    <div class="right m-item item m-mobile-hide">
                        <div class="ui icon inverted transparent input">
                            <input type="text" placeholder="Search......">
                            <i class="search link icon"></i>
                        </div>
                    </div>
                </div>
            </div>
            <a href="#" class="ui menu toggle black icon button m-right-top m-mobile-show">
                <i class="sidebar icon"></i>
            </a>
        </nav>

        <!--底部-->
        <footer th:fragment="footer" class="ui inverted vertical segment m-padded-tb-massivs">
            <div class="ui center aligned container">
                <div class="ui inverted divided stackable grid">
                    <div class="three wide column">
                        <div class="ui inverted link list">
                            <div class="item">
                                <img src="../static/images/WX_Arvin.jpg" th:src = "@{/images/WX_Arvin.jpg}" class="ui rounded image" alt="Guest Island" style="width: 100px">
                            </div>
                        </div>
                    </div>
                    <div class="three wide column">
                        <h4 class="ui inverted header m-text-thin m-text-spaced">最新博客</h4>
                        <div class="ui inverted link list">
                            <a href="#" class="item">用户故事 (User Story) </a>
                            <a href="#" class="item">关于脑机的那些事</a>
                            <a href="#" class="item">2021年计划</a>
                        </div>
                    </div>
                    <div class="three wide column">
                        <h4 class="ui inverted header m-text-thin m-text-spaced">关于我</h4>
                        <div class="ui inverted link list">
                            <a href="#" class="item">Email: 2644266656@qq.com</a>
                            <a href="#" class="item">QQ: 2644266656</a>
                        </div>
                    </div>
                    <div class="seven wide column">
                        <h4 class="ui inverted header m-text-thin m-text-spaced">Guest Island</h4>
                        <p class="m-text-thin m-text-spaced m-opacity-mini">南有孤岛北有亡梦，南柯一梦终是虚无。</p>
                    </div>
                </div>
                <div class="ui inverted section divider"></div>
                <p class="m-text-thin m-text-spaced m-opacity-tiny">Copyright &copy; 2020-2021 Guest Island Personal blog</p>
            </div>
        </footer>

        <!--script-->
        <th:block th:fragment="script">
            <script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
            <script src="//cdn.jsdelivr.net/npm/jquery.scrollto@2.1.2/jquery.scrollTo.min.js"></script>
            <script src="../static/lib/prism/prism.js" th:src="@{/lib/prism/prism.js}"></script>
            <script src="../static/lib/tocbot/tocbot.min.js" th:src="@{/lib/tocbot/tocbot.min.js}"></script>
            <script src="../static/lib/qrcode/qrcode.min.js" th:src="@{/lib/qrcode/qrcode.min.js}"></script>
            <script src="../static/lib/waypoints/jquery.waypoints.min.js" th:src="@{/lib/waypoints/jquery.waypoints.min.js}"></script>
        </th:block>

    </body>

</html>
```

### 实体设计
blog.java 博客实体类

```java
package com.cxkj.blog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  Created by Arvin on 2021/2/4.
 */
@Entity
@Table(name = "t_blog")
public class Blog {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
```
Type.java 分类实体类

```java
package com.cxkj.blog.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 *  Created by Arvin on 2021/2/4.
 */
@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "分类名称不能为空哦")
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```
Tag.java 标签实体类

```java
package com.cxkj.blog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Created by Arvin on 2021/2/4.
 */
@Entity
@Table(name = "t_tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```
Comment.java 评论实体类

```java
package com.cxkj.blog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  Created by Arvin on 2021/2/4.
 */
@Entity
@Table(name = "t_comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private Blog blog;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();
    @ManyToOne
    private Comment parentComment;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
```
User.java 用户实体类

```java
package com.cxkj.blog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  Created by Arvin on 2021/2/4.
 */
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", type=" + type +
                ", CreateTime=" + CreateTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
```
### 后台登录业务
UserService.java User登录业务逻辑处理接口类

```java
package com.cxkj.blog.service;

import com.cxkj.blog.pojo.User;

/**
 *  Created by Arvin on 2021/2/5.
 */

public interface UserService {
    
    User checkUser(String username,String password);
}
```
UserServiceImpl.java User登录业务逻辑处理实现类

```java
package com.cxkj.blog.service;

import com.cxkj.blog.dao.UserRepository;
import com.cxkj.blog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Created by Arvin on 2021/2/5.
 */
@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);
        return user;
    }
    
}
```
UserRepository.java 引用SpringJPA SQL操作接口

```java
package com.cxkj.blog.dao;

import com.cxkj.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Created by Arvin on 2021/2/5.
 */

public interface UserRepository extends JpaRepository<User,Long> {
    
    User findByUsernameAndPassword(String username,String password);
}
```
LoginController.java WEB登录模块控制器

```java
package com.cxkj.blog.web.admin;

import com.cxkj.blog.pojo.User;
import com.cxkj.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 *  Created by Arvin on 2021/2/5.
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名和密码存在异常错误");
            return "redirect:/admin";
        }
        
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
```
### MD5加密
MD5Utils.java MD5加密工具类

```java
package com.cxkj.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  Created by Arvin on 2021/2/5.
 *
 *  MD5加密类
 * @parm str 要加密的字符串
 * @return 加密后的字符串
 */

public class MD5Utils {

    public static String code(String str){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] bytesDigest = md.digest();
            int i;
            StringBuffer buffer = new StringBuffer("");
            for (int offset = 0;offset<bytesDigest.length;offset++){
                i = bytesDigest[offset];
                if (i<0)
                    i+= 256;
                if (i<16)
                    buffer.append("0");
                buffer.append(Integer.toHexString(i));
            }
            //32位加密
            return buffer.toString();
            //16位加密
            //return buffer.toString().substring(8,20);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
    
    //加密测试Main方法
    public static void main(String[] args) {
        System.out.println(code("123456789"));
    }
}
```
UserServiceImpl.java

```java
package com.cxkj.blog.service;

import com.cxkj.blog.dao.UserRepository;
import com.cxkj.blog.pojo.User;
import com.cxkj.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Created by Arvin on 2021/2/5.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

}
```
BlogController.java 博客后台页面权限过滤管理类

```java
package com.cxkj.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  Created by Arvin on 2021/2/5.
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping("/blogs")
    public String blogs() {
        return "blogs";
    }

}
```
LongInterceptor.java Blog后台页面权限(登录过滤)类

```java
package com.cxkj.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  Created by Arvin on 2021/2/5.
 */

public class LongInterceptor extends WebMvcConfigurerAdapter {

    //WebMvcConfigurerAdapter这个也过时了
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/admin");
            return false;
        }

        return true;
    }
}
```
WebConfig.java Blog后台页面权限(拦截配置)类

```java
package com.cxkj.blog.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *  Created by Arvin on 2021/2/5.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    //WebMvcConfigurerAdapter这个过时了
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LongInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }

}
```
### 关于弃用类整改
LongInterceptor.java Blog后台页面权限(登录过滤)类

```java
package com.cxkj.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  Created by Arvin on 2021/2/5.
 */

public class LongInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/admin");
            return false;
        }

        return true;
    }
}
```
WebConfig.java Blog后台页面权限(拦截配置)类

```java
package com.cxkj.blog.interceptor;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  Created by Arvin on 2021/2/5.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LongInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }

}
```
### 分类业务处理
TypeService.java 分类业务逻辑处理接口

```java
package com.cxkj.blog.service;

import com.cxkj.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *  Created by Arvin on 2021/2/6.
 */

public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
```
TypeServiceImpl.java 分类业务逻辑处理实现类

```java
package com.cxkj.blog.service;

import com.cxkj.blog.NotFoundException;
import com.cxkj.blog.dao.TypeRepository;
import com.cxkj.blog.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  Created by Arvin on 2021/2/6.
 */
@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).get();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findById(id).get();
        if (t == null){
            throw new NotFoundException("您查找的信息不存在(︶︹︺)");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
```
TypeRepository.java 分类业务 SQL操作接口

```java
package com.cxkj.blog.dao;

import com.cxkj.blog.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Created by Arvin on 2021/2/6.
 */

public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
```
TypeController.java Web层操作

```java
package com.cxkj.blog.web.admin;

import com.cxkj.blog.pojo.Type;
import com.cxkj.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 *  Created by Arvin on 2021/2/6.
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                Pageable pageable, Model model){

        model.addAttribute("page",typeService.listType(pageable));
        return "/admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "/admin/types-input";
    }

    @GetMapping("types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "/admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type typename = typeService.getTypeByName(type.getName());
        if (typename != null){
            result.rejectValue("name","nameError","管理员大大，这个分类已经有了。((٩(//̀Д/́/)۶))做人要专一哦！");
        }
        if (result.hasErrors()){
            return "/admin/types-input";
        }
        Type t =  typeService.saveType(type);
        if (t == null){
            attributes.addFlashAttribute("message","新增失败 ﾍ(;´Д｀ﾍ),管理员大大重新试下吧");
        }else {
            attributes.addFlashAttribute("message","新增成功 ≖‿≖✧ 快去发布新内容吧");
        }
        return "redirect:../admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Type typename = typeService.getTypeByName(type.getName());
        if (typename != null){
            result.rejectValue("name","nameError","管理员大大，这个分类已经有了。((٩(//̀Д/́/)۶))做人要专一哦！");
        }
        if (result.hasErrors()){
            return "/admin/types-input";
        }
        Type t =  typeService.updateType(id,type);
        if (t == null){
            attributes.addFlashAttribute("message","更新失败（⊙o⊙）,管理员大大重新试下吧");
        }else {
            attributes.addFlashAttribute("message","更新成功 (≥◇≤) 快去发布新内容吧");
        }
        return "redirect:../types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功,可能是管理员大大不喜欢它了吧(.◕ฺˇд ˇ◕ฺ)");
        return "redirect:../";
    }
}
```
### 标签业务处理
TagService.java 标签业务逻辑处理接口

```java
package com.cxkj.blog.service;

import com.cxkj.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *  Created by Arvin on 2021/2/7.
 */

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);
}
```
TagServiceImpl.java 标签业务逻辑处理实现类

```java
package com.cxkj.blog.service;

import com.cxkj.blog.NotFoundException;
import com.cxkj.blog.dao.TagRepository;
import com.cxkj.blog.pojo.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by Arvin on 2021/2/7.
 */
@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {  //1,2,3
        return tagRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if ("".equals(ids) && ids != null){
            String[] idarray = ids.split(",");
            for (int i=0; i<idarray.length; i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findById(id).get();
        if (t == null){
            throw new NotFoundException("您查找的信息不存在(︶︹︺)");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}

```
TagRepository.java 标签业务 SQL操作接口

```java
package com.cxkj.blog.dao;

import com.cxkj.blog.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Created by Arvin on 2021/2/7.
 */

public interface TagRepository extends JpaRepository<Tag,Long> {
    
    Tag findByName(String name);
}
```
TagController.java WEB操作

```java
package com.cxkj.blog.service;

import com.cxkj.blog.NotFoundException;
import com.cxkj.blog.dao.TagRepository;
import com.cxkj.blog.pojo.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Created by Arvin on 2021/2/7.
 */
@Service
public class TagServiceImpl implements TagService{

    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findById(id).get();
        if (t == null){
            throw new NotFoundException("您查找的信息不存在(︶︹︺)");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
```
### 博客业务处理
BlogService.java 博客业务处理逻辑接口
```java
package com.cxkj.blog.service;

import com.cxkj.blog.pojo.Blog;
import com.cxkj.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *  Created by Arvin on 2021/2/8.
 */

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
```
BlogServiceImpl.java 博客业务处理逻辑实现类
```java
package com.cxkj.blog.service;

import com.cxkj.blog.NotFoundException;
import com.cxkj.blog.dao.BlogRepository;
import com.cxkj.blog.pojo.Blog;
import com.cxkj.blog.pojo.Type;
import com.cxkj.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  Created by Arvin on 2021/2/8.
 */
@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery) {

        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (!"".equals(blogQuery.getTitle()) && blogQuery.getTitle() != null){
                    predicateList.add(criteriaBuilder.like(root.<String>get("title"),"%"+blogQuery.getTitle()+"%"));
                }
                if (blogQuery.getTypeID() != null){
                    predicateList.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blogQuery.getTypeID()));
                }
                if (blogQuery.isRecommend()){
                    predicateList.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blogQuery.isRecommend()));
                }
                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.findById(id).get();
        if (b == null){
            throw new NotFoundException("管理员大大,这个博客不存在哦！～(　TロT)σ");
        }
        BeanUtils.copyProperties(b,blog);
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
```
BlogRepository.java 博客业务 SQL操作接口
```java
package com.cxkj.blog.dao;

import com.cxkj.blog.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *  Created by Arvin on 2021/2/8.
 */

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    
}
```
BlogController.java WEB操作
```java
package com.cxkj.blog.web.admin;

import com.cxkj.blog.pojo.Blog;
import com.cxkj.blog.pojo.User;
import com.cxkj.blog.service.BlogService;
import com.cxkj.blog.service.TagService;
import com.cxkj.blog.service.TypeService;
import com.cxkj.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 *  Created by Arvin on 2021/2/5.
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final  String INPUT = "/admin/blogs-input";
    private static final  String LIST = "/admin/blogs";
    private static final  String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blogQuery, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,BlogQuery blogQuery,Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        return "/admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return INPUT;
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput (@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);

        if (b == null){
            attributes.addFlashAttribute("message","操作失败 ﾍ(;´Д｀ﾍ),管理员大大重新试下吧");
        }else {
            attributes.addFlashAttribute("message","操作成功 ≖‿≖✧ 快去发布新内容吧");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功,期待您发布更加美好的内容︿(￣︶￣)︿");
        return REDIRECT_LIST;
    }
}
```
BlogQuery.java 博客搜索查询类
```java
package com.cxkj.blog.vo;

/**
 *  Created by Arvin on 2021/2/8.
 */

public class BlogQuery {
    
    private String title;
    private Long typeID;
    private boolean recommend;

    public BlogQuery() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
```
Blog.java 博客实体类
```java
package com.cxkj.blog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  Created by Arvin on 2021/2/4.
 */
@Entity
@Table(name = "t_blog")
public class Blog {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    @Transient
    private String tagIds;

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    //初始化tagIds方法
    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }
    private String tagsToIds(List<Tag> tags){
        if (!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags){
                if (flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
```