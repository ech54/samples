package org.ech.samples.java.junit5.features;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class _02ParameterizedTest {

    @ParameterizedTest
    @ValueSource(strings={"value1", "value2", "value3", "value4"})
    public void caseTestParameterized(final String parameter) {

        assertNotNull(parameter);

    }
}
