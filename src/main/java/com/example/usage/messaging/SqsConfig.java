package com.example.usage.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.SqsClientBuilder;

import java.net.URI;

@Configuration
@EnableScheduling
public class SqsConfig {

    @Bean
    SqsClient sqsClient(@Value("${app.sqs.endpoint:}") String endpoint,
                        @Value("${app.sqs.region}") String region) {
        SqsClientBuilder builder = SqsClient.builder().region(Region.of(region));
        if (!endpoint.isBlank()) {
            // Local ElasticMQ: custom endpoint and dummy credentials.
            // On real AWS the endpoint is empty and the SDK finds credentials itself.
            builder.endpointOverride(URI.create(endpoint))
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create("local", "local")));
        }
        return builder.build();
    }
}