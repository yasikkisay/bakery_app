package com.bakery.test.config;

import com.bakery.test.entities.Cake;
import com.bakery.test.models.dtos.CakeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CakeConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Cake, CakeDto>() {
            @Override
            protected void configure() {
                map().setStatus(source.defineStatus());
            }
        });
        return modelMapper;
    }
}
