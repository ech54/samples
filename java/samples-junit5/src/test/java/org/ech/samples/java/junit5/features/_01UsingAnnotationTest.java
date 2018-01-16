package org.ech.samples.java.junit5.features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class _01UsingAnnotationTest {
	private static Logger logger = LoggerFactory.getLogger(_01UsingAnnotationTest.class);

	@Test
	public void useTestAnnotation() {
		assertEquals(2, 1+1);
	}
	
}
