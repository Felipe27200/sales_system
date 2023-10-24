package com.sales.sales_system.config.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig
{
    /*
    * Making a bean available for the project
    * so in this form we can convert the model DTO
    * to an entity.
    * */
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
