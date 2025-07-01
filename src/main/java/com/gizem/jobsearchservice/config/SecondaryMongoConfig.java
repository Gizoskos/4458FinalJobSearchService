package com.gizem.jobsearchservice.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.gizem.jobsearchservice.repository", // ilanları okuyan repo
        mongoTemplateRef = "jobMongoTemplate"
)
public class SecondaryMongoConfig {

    @Value("${app.secondary.mongodb.uri}")
    private String uri;

    @Value("${app.secondary.mongodb.database}")
    private String dbName;

    @Bean
    public MongoClient jobMongoClient() {
        return MongoClients.create(uri);
    }

    @Bean(name = "jobMongoTemplate")
    public MongoTemplate jobMongoTemplate() {
        return new MongoTemplate(jobMongoClient(), dbName);
    }
}