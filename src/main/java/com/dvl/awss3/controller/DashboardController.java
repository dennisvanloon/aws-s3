package com.dvl.awss3.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class DashboardController {

    private final Logger LOGGER = getLogger(getClass());

    private final AmazonS3 amazonS3;
    private final String bucketName;

    @Autowired
    public DashboardController(AmazonS3 amazonS3, @Value("${custom.bucket}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    @PostConstruct
    public void postConstruct() {
        Optional<Bucket> optionalBucket = amazonS3.listBuckets().stream()
                .filter(bucket -> bucket.getName().equals(bucketName))
                .findFirst();
        LOGGER.info("Bucket {} exists {}", bucketName, optionalBucket.isPresent());
    }

    @GetMapping("/")
    public ModelAndView getDashboardView() {
        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withMaxKeys(20);
        List<S3ObjectSummary> availableFiles = amazonS3.listObjectsV2(req).getObjectSummaries();

        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("message", "Spring Boot with AWS");
        modelAndView.addObject("bucketName", bucketName);
        modelAndView.addObject("availableFiles", availableFiles);
        return modelAndView;
    }

}