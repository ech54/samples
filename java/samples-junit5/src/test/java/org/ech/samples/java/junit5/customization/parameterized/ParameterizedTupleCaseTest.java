package org.ech.samples.java.junit5.customization.parameterized;

import static org.junit.jupiter.api.Assertions.*;
import org.ech.samples.java.junit5.customization.parameterized.support.IntTuple;
import org.ech.samples.java.junit5.customization.parameterized.support.TupleCase;
import org.junit.jupiter.params.ParameterizedTest;
import java.util.stream.IntStream;
public class ParameterizedTupleCaseTest {

    @ParameterizedTest
    @TupleCase(intTuples = {
            @IntTuple(hypothesis = {1, 2}, expected = 3),
            @IntTuple(hypothesis = {2, 2}, expected = 4),
    })
    public void sample(final int[] hypothesis, final int expected) {
        assertEquals(expected, sum(hypothesis));
    }

    private int sum(int ...arguments) {
        return IntStream.of(arguments).sum();
    }
}
