package com.cloud.setup.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ServiceApplicationTests<SpringBootMetricsCollector> {
    @MockBean
    private SpringBootMetricsCollector springBootMetricsCollector;
    @Test
    void contextLoads() {
    }

}
