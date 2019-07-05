package org.ech.sample;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.test.ConsumerRecordFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerifyTestingUtilsWithBuilder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VerifyTestingUtilsWithBuilder.class);
	
	final ConsumerRecordFactory<String, Long> consumerFactory = new ConsumerRecordFactory<String, Long>(
													"topic-in", new StringSerializer(), new LongSerializer());
	
	TopologyTestDriver driver = null;
	
	@Before
	public void before() {
		StreamsBuilder builder = new StreamsBuilder();
		builder.stream("topic-in").to("topic-out");
		Topology topology = builder.build();
		Properties config = new Properties();
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "test");
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:1234");
		driver = new TopologyTestDriver(topology, config);
	}
	
	@After
	public void after() {
		driver.close();
	}
	
	@Test
	public void produceAndConsume() {
		
		driver.pipeInput(consumerFactory.create("topic-in", "one", 1l, 9999L));
		final ProducerRecord<String, Long> result = driver.readOutput("topic-out", new StringDeserializer(), new LongDeserializer());
		LOGGER.info("k: {}, v: {} ",result.key(), result.value());
	}
	
}
