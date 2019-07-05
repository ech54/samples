package org.ech.example;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

public class WordCountStreamDefinition {
	
	public static final String TOPIC_INPUT = "wordcount-input";
	public static final String TOPIC_OUTPUT = "wordcount-output";
	
	
	public static void countNumberOfWordPerLine(final StreamsBuilder builder) {
		
		final KStream<String, String> textLinesStream =  builder.stream(TOPIC_INPUT, Consumed.with(Serdes.String(), Serdes.String()));
		final Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);
		final KTable<String, Long> occurencePerLine = textLinesStream
														.flatMapValues(v->Arrays.asList(pattern.split(v.toLowerCase())))
														.groupBy((k,v)->v)
														.count();
		occurencePerLine.toStream().to(TOPIC_OUTPUT, Produced.with(Serdes.String(), Serdes.Long()));
	}
	
}
