package com.weblab.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.util.List;

@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String rootPath = System.getProperty("user.home");
        String imagePath = "file:" + rootPath + File.separator + "app/tmp";
        System.out.println(imagePath);
        registry.addResourceHandler("/audio/**").addResourceLocations(imagePath);
        super.addResourceHandlers(registry);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {


        // http
        HttpMessageConverter converter = new StringHttpMessageConverter();
        converters.add(converter);

        // string
        converter = new FormHttpMessageConverter();
        converters.add(converter);

        // json
        converter = new MappingJackson2HttpMessageConverter();
        converters.add(converter);


    }
}