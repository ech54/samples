package org.ech.samples.java.junit5.customization.parameterized.support;

import org.junit.jupiter.params.provider.ArgumentsSource;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(TupleCaseArgumentsProvider.class)
public @interface TupleCase {

    StringTuple[] stringTuples() default {};
    IntTuple[] intTuples() default {};
}
