package org.ech.samples.java.junit5.customization.parameterized.support;

public @interface IntTuple {
    int expected();
    int[] hypothesis();
}
