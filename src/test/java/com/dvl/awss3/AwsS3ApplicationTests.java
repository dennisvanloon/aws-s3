package com.dvl.awss3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.dvl.awss3.config.S3TestConfig;
import com.dvl.awss3.config.SqsTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

@SpringBootTest(classes = {S3TestConfig.class, SqsTestConfig.class})
class AwsS3ApplicationTests {

    private final Logger LOGGER = getLogger(getClass());

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private AmazonSQS amazonSQS;

    @Value("${custom.bucket}")
    private String bucketName;

    @Value("${custom.sqs-queue-name}")
    private String sqsQueueName;

    @BeforeEach
    public void beforeEach() {
        LOGGER.info("Testing with bucket {} and queue {}", bucketName, sqsQueueName);

        Optional<Bucket> bucket = amazonS3.listBuckets().stream().filter(b -> b.getName().equals(bucketName)).findFirst();
        bucket.ifPresentOrElse(this::clearBucket, () -> createBucket(bucketName));

        amazonSQS.createQueue(sqsQueueName);
    }

    @Test
    void s3BucketIsEmpty() {
        List<S3ObjectSummary> objectSummaries = amazonS3.listObjects(bucketName).getObjectSummaries();
        assertEquals(0, objectSummaries.size());
    }

    @Test
    void sqsQueueIsEmpty() {
        String queueUrl = amazonSQS.getQueueUrl(sqsQueueName).getQueueUrl();
        ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(queueUrl);
        assertEquals(0, receiveMessageResult.getMessages().size());
    }

    private void clearBucket(Bucket bucket) {
        LOGGER.info("Clearing bucket {}", bucket.getName());
        //TODO implement
    }

    private void createBucket(String bucketName) {
        LOGGER.info("Creating bucket {}", bucketName);
        amazonS3.createBucket(bucketName);
    }
}
