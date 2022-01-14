package com.dvl.awss3.config;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class S3TestConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${localstack.endpoint}")
    private String localStackEndpoint;

    @Bean
    public AmazonS3 amazonS3() {
        final EndpointConfiguration endpointConfiguration = new EndpointConfiguration(localStackEndpoint, region);
        return AmazonS3Client.builder()
                .withEndpointConfiguration(endpointConfiguration)
                .withPathStyleAccessEnabled(true)
                .build();
    }

}
