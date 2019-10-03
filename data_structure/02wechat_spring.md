
#  
新建项目maven 项目  
所属组com.imooc  
项目名  
netty-hello  


在网站搜索 netty  
https://mvnrepository.com/  

选  
Netty/All In One  
选4.1.31版本（注意netty5.x版本是废弃版本）  

进入版本后，在maven的tab复制  
<!-- https://mvnrepository.com/artifact/io.netty/netty-all -->  
<dependency>  
    <groupId>io.netty</groupId>  
    <artifactId>netty-all</artifactId>  
    <version>4.1.31.Final</version>  
</dependency>  

复制到项目pom文件的dependencys里面  

#  
hello服务器启动  
HelloServer.java右键 run as java application  

浏览器访问  
http://localhost:8088/    （HelloServer中，设置了端口号为8088）  
显示内容  Hello netty~  

或者curl也可以访问：  
curl 192.168.1.1:8088  

服务器打印：  
助手类添加  
channel。。。注册  
channel。。。活跃  
/192.168.1.1:58049  
channeld读取完毕。。。  
channeld读取完毕。。。  
channel。。。不活跃  
channel。。。移除  
助手类移除  

实时通信3种方式：  
ajax轮训（http）		//浏览器循环执行ajax  
long pull（http）		//浏览器发起请求，等服务端响应后，再继续发起请求  
websocket			//监听，主动推送消息给客户端  


mui:
https://www.dcloud.io
前端使用mui

http://www.html5plus.org/doc/
h5调用手机原生

图标资源：
http://www.iconfont.cn/









