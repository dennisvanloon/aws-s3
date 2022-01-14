package com.dvl.awss3.config;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SqsTestConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${localstack.endpoint}")
    private String localStackEndpoint;

    @Bean
    public AmazonSQS amazonSQS() {
        final EndpointConfiguration endpointConfiguration = new EndpointConfiguration(localStackEndpoint, region);
        return AmazonSQSClient.builder()
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }

}
