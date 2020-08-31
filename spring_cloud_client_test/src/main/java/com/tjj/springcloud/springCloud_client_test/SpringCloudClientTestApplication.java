package com.tjj.springcloud.springCloud_client_test;

import com.netflix.discovery.EurekaNamespace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudClientTestApplication {

	 public static void main(String[] args) {
	        SpringApplication.run(SpringCloudClientTestApplication.class, args);
	    }

}