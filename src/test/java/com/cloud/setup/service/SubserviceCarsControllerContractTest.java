package com.cloud.setup.service;

import com.cloud.setup.service.properties.AppProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.metrics.AutoConfigureMetrics;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.example:spring-cloud-subservice:+:8082"
)
@AutoConfigureWireMock(port = 8081)
@AutoConfigureMetrics
@TestPropertySource("classpath:application-test.yml")
public class SubserviceCarsControllerContractTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AppProperties appProperties;

    @StubRunnerPort("spring-cloud-subservice")
    Integer producerPort;

    @Test
    public void shouldReturnRandomCar() throws Exception {
        this.appProperties.getSubservice().setPort(producerPort);
        // When
        ResultActions result = mockMvc.perform(get("/cars"));
        // Then
        result.andExpect(status().isOk())
                .andExpect(content().json("{\"Name\":\"someString\",\"Year\":\"1970-01-01\",\"Origin\":\"USA\"}"));
    }
}
