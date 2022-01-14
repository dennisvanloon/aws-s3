package com.dvl.awss3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class)
public class AwsS3Application {

    public static void main(String[] args) {
        SpringApplication.run(AwsS3Application.class, args);
    }

}
