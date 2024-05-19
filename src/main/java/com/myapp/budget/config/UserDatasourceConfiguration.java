package com.myapp.budget.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
@EnableConfigurationProperties
public class UserDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.data.mongodb.user")
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(databaseFactory(mongoProperties()));
    }

    @Bean
    public SimpleMongoClientDatabaseFactory databaseFactory(@Qualifier("mongoProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoProperties().getUri());
    }
}
