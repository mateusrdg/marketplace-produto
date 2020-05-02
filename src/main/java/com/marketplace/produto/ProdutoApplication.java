package com.marketplace.produto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
public class ProdutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdutoApplication.class, args);
    }

}
