# toshop    
ssm框架实战项目-toshop网上商城
## 简介
这是个人的SSM框架学习的实战项目，通过Maven进行版本控制。目前只开发了部分服务器端的功能。如果你感兴趣，欢迎各位同学star、fork。
## 前后端接口文档(wiki)
如果你想参与到项目的前端开发中，请参考本项目的[文档(wiki)][document]
## Technology Stack技术栈
+ 开发语言：Java
+ 数据库：Mysql
+ Java开发框架：Spring MVC+Spring+Mybatis    
  #### 版本说明：
    + Java：1.8
    + Mysql：5.7.21
    + Spring：4.0.0
    + Mybatis：3.4.1
    + Mybatis-spring：1.3.0
    + Tomcat：8.5.24
## 部署说明    
**在运行项目前，你需要要如下准备：**    
+ 数据库准备
  1. 创建数据库‘toshop’(字符集选择为utf8)
  2. 修改[datesource.properties][datesource.properties]文件的数据库用户名(username)和密码(password)为你设置的密码
  3. 并执行数据库脚本,插入测试数据.脚本位置：[待添加]
+ 安装Maven工具，并设置环境变量
+ 安装Tomcat 服务器，并设置环境变量
+ Email服务器设置
  * 在进行用户注册以及修改密码等操作时，需要进行邮箱验证.所以还需要对你的**SMTP服务器**进行设置，设置文件位于：[MailUtil][mailProperties]
+ 使用IDEA导入项目，并选择Maven的方式
+ 其他IDEA的设置请自行百度、Google：“Idea使用Maven导入JavaWeb项目设置”
**经过如上设置，启动Tomcat，即可通过URL：http://localhost:8080/toshop/ 访问项目！**    
说明：由于在使用`@Autowired`导入`Spring.mail`相关类时，抛出了Bean无法初始化的问题，所以改用了`@Bean`的方式，暂时没有将“设置文件分离出来”。如果你有很好的解决方案，请“Issues”。非常感谢你的支持！

[document]:https://github.com/ForDingdong/toshop/wiki
[datesource.properties]:https://github.com/ForDingdong/toshop/blob/master/src/main/resources/datasource.properties
[mailProperties]:https://github.com/ForDingdong/toshop/blob/master/src/main/java/com/toshop/util/MailUtil.java
