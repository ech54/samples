package org.ech.samples.java.junit5.features;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class _03LifecycleTest {

    private static Logger LOGGER = LoggerFactory.getLogger(_03LifecycleTest.class);

    @BeforeAll
    public static void beforeAll() {
        LOGGER.debug("start beforeAll");
    }

    @AfterAll
    public static void afterAll() {
        LOGGER.debug("start afterAll");
    }

    @BeforeEach
    public void beforeEach() {
        LOGGER.debug("start beforeEach");
    }

    @AfterEach
    public void afterEach() {
        LOGGER.debug("start afterEach");
    }

    @Test
    @DisplayName("First test case")
    public void firstTestCase() {
        LOGGER.debug("start first test");

        assertEquals(2, 1+1);
    }

    @Test
    @DisplayName("Second test case")
    public void secondTestCase() {
        LOGGER.debug("start second test");

        assertEquals(4, 2+2);
    }

}
