package org.ech.sample;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.test.ConsumerRecordFactory;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EmbeddedKafka {
	
	protected static final String TOPIC_IN = "topic-in";
	protected static final String TOPIC_OUT = "topic-out";
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(VerifyTestingUtilsWithBuilder.class);
	
	protected ConsumerRecordFactory<String, Long> consumerFactory = new ConsumerRecordFactory<String, Long>(
			TOPIC_IN, new StringSerializer(), new LongSerializer());
	protected StreamsBuilder builder = null;
	protected TopologyTestDriver driver = null;
	
	@Before
	public void before() {
		builder = new StreamsBuilder();
		initStreamBuilder();
		Topology topology = builder.build();
		driver = new TopologyTestDriver(topology, config());
	}
	
	protected Properties config() {
		Properties config = new Properties();
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "test");
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:1234");
		return config;
	}
	
	protected void initStreamBuilder() {
		builder.stream(TOPIC_IN).to(TOPIC_OUT);
	}
	
	@After
	public void after() {
		try {
			driver.close();
		} 
		catch(Exception exception) {
			LOGGER.error("driver close in error", exception);
		}
	}
}
