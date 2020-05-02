package com.marketplace.produto.config;

import com.marketplace.produto.service.DBTeste;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Autowired
    private DBTeste dbTeste;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public void testarDatabase() {
        dbTeste.testDatabase();
    }
}
