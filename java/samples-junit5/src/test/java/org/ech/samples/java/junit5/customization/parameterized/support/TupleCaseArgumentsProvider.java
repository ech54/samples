package org.ech.samples.java.junit5.customization.parameterized.support;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.junit.platform.commons.util.Preconditions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TupleCaseArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<TupleCase> {

    private Object[] arguments;

    @Override
    public void accept(TupleCase tupleCase) {
        final List<Object> arrays = new ArrayList<>();
        arrays.addAll(addTuples(Stream.of(tupleCase.stringTuples()), (tuple -> new Object[]{((StringTuple)tuple).hypothesis(), ((StringTuple)tuple).expected()})));
        arrays.addAll(addTuples(Stream.of(tupleCase.intTuples()), (tuple -> new Object[]{((IntTuple)tuple).hypothesis(), ((IntTuple)tuple).expected()})));

        Preconditions.condition(arrays.size() == 2, () ->
                "Exactly one type of input must be provided in the @" + TupleCase.class.getSimpleName()
                        + " annotation but there were " + arrays.size());
        arguments = arrays.toArray();
    }

    private static List<Object> addTuples(Stream<?> stream, Function mapper) {
        return (List<Object>)stream
                .map(mapper)
                .collect(Collectors.toList());
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {

        return Arrays.stream(this.arguments)
                .map(xva$0->(Object[])xva$0)
                .map((xva$0) -> Arguments.of(xva$0[0], xva$0[1]));
    }
}
