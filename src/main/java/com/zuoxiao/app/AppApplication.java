package com.zuoxiao.app;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAspectJAutoProxy
public class AppApplication {

	private static final Logger logger = LoggerFactory.getLogger(AppApplication.class);

	public static void main(String[] args) {
		logger.info("开始启动项目....");
		SpringApplication.run(AppApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "jojo") String name) {
		return String.format("Hello %s! 你在干啥呀？", name);
	}

	@Bean
	public Connector connector(){
		Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(80);
		connector.setSecure(false);
		connector.setRedirectPort(443);
		return connector;
	}

	@Bean
	public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector){
		TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory(){
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint=new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection=new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(connector);
		return tomcat;
	}

}
