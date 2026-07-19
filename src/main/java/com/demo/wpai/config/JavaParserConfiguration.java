package com.demo.wpai.config;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaParserConfiguration {

    @PostConstruct
    public void configure() {

        StaticJavaParser.getParserConfiguration()

                .setLanguageLevel(

                        ParserConfiguration.LanguageLevel.JAVA_17

                );

    }

}
