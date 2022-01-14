package com.dvl.awss3.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.credentials.profile-name:fakeprofilename}")
    private String profileName;

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3Client.builder()
                .withRegion(region)
                .withCredentials(new ProfileCredentialsProvider(profileName))
                .build();
    }

}
