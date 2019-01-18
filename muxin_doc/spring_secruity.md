###imooc_spring_secruity视频笔记

Spring IO Platform
项目引入这个依赖，用来控制其他依赖的版本；这样引入其他依赖时，不需要填写版本，例如：
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
</dependency>

建立项目maven project
其中主项目imooc-security配置pom
其他副项目配置jar

所有项目都在hallojava目录项目
imooc-security					//在pom.xml引入下面其他模块
imooc-security-app
imooc-security-authorize
imooc-security-browser
imooc-security-core				//核心模块
imooc-security-demo


### 打包
在项目中imooc-security-demo，添加配置
<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
			<version>1.5.6.RELEASE</version>
			<executions>
				<execution>
					<goals>
						<goal>repackage</goal>
					</goals>
				</execution>
			</executions>
		</plugin>
	</plugins>
	<finalName>demo</finalName>
</build>

右键demo项目，maven，update project

右键项目imooc-security,run as,maven build
goals:clean package
勾选：skip tests
apply，run

生成jar包

## 运行jar
java -jar demo.jar
这样就运行起来了，跟在ide中run as spring boot一致

##
hibernate.validator 可以用来验证表单的一些方法  

MyConstraintValidator 自定义表单验证  

restlet client 类似postman

exception 异常处理/自定义异常处理

restful api的拦截

过滤器 filter
拦截器 interceptor    com.imooc.web.interceptor
切片 aspect 可以拿到方法调用的参数值  com.imooc.web.aspect

3个拦截机制各有特点，根据业务选择
最先起作用 filter
然后 interceptor
然后aspect（最先捕获异常）
最后再进到调用的controller方法


com.imooc.web.config.WebConfig  引入第三方或自定义filter


切片（类）
切入点（注解）
1.在哪些方法上起作用  
2.在什么时候起作用  

增强（方法）  
起作用时执行的业务逻辑  



