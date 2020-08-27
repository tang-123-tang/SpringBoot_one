package com.tjj.springcloud.springCloudClientAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudClientAccountApplication {
    @Bean
    @LoadBalanced//加载负载均衡
  public RestTemplate restTemplate(){
      return new RestTemplate();
  }
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClientAccountApplication.class, args);
    }

}
