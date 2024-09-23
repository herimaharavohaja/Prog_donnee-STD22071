package com.backend.patrimoine.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JsonConfig {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObjectMapperIsNotNull() {
        assertThat(objectMapper).isNotNull();
    }
}
