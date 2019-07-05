package org.ech.sample;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.test.ConsumerRecordFactory;
import org.ech.example.WordCountStreamDefinition;
import org.junit.Test;

public class WordCountStreamTest extends EmbeddedKafka {

	@Override
	protected void initStreamBuilder() {
		// initialized streams.
		WordCountStreamDefinition.countNumberOfWordPerLine(builder);
	}
	
	@Override
	protected Properties config() {
		Properties config = super.config();
		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		return config;
	}
	
	@SuppressWarnings("all")
	protected ConsumerRecordFactory<String, String> consumerFactory() {
		return new ConsumerRecordFactory(new StringSerializer(), new StringSerializer());
	}
	
	@Test
	public void countTextWord() {
		
		driver.pipeInput(consumerFactory().create(WordCountStreamDefinition.TOPIC_INPUT, Arrays.asList(
				new KeyValue<String, String>("one", "Un texte est un ensemble cohérent d’énoncés qui forme une unité de sens et qui a une intention communicative")))
				);
		
		ProducerRecord<String, Long> results = driver.readOutput(WordCountStreamDefinition.TOPIC_OUTPUT, new StringDeserializer(), new LongDeserializer());
		while(results!=null) {
			LOGGER.info("-> k:{}, v:{}", results.key(), results.value());
			results = driver.readOutput(WordCountStreamDefinition.TOPIC_OUTPUT, new StringDeserializer(), new LongDeserializer());
		}
		
	}
	
}
