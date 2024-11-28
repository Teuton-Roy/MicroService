package com.example.jobms.Job;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;

@Configuration
public class AppConfig {

    @LoadBalanced //configure the RestTemplate to use load balancer client under the hood and this is a client
                  // that is capable of using service IDs registered with Eureka to locate the services
    @Bean //specifying bean means tell spring framework that it needs to manage this objects or object of this class
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
