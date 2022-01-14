package com.dvl.awss3.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqsConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.credentials.profile-name:fakeprofilename}")
    private String profileName;

    @Bean
    public AmazonSQS amazonSQS() {
        return AmazonSQSClient.builder()
                .withCredentials(new ProfileCredentialsProvider(profileName))
                .withRegion(region)
                .build();
    }

}
