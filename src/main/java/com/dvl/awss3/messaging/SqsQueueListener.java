package com.dvl.awss3.messaging;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class SqsQueueListener {

    private final Logger LOGGER = getLogger(getClass());

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("SqsQueueListener is alive");
    }

}
