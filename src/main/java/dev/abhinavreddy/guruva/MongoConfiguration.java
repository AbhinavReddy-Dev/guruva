package dev.abhinavreddy.guruva;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@EnableMongoRepositories()
@EnableMongoAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class MongoConfiguration {

//  Mongo URI is stored in application.properties
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

//  SimpleMongoClientDatabaseFactory is used for connecting to MongoDB
    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        MongoClient mongoClient = MongoClients.create(mongoUri);
        return new SimpleMongoClientDatabaseFactory(mongoClient, "guruva");
    }

//  For disabling the _class column in MongoDB
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoCustomConversions conversions) {
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDatabaseFactory()), new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null)); // Disables the _class column
        converter.setCustomConversions(conversions);
        return converter;
    }

//    For auditing the created and updated_at fields
    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(LocalDateTime.now());
    }


}
