package org.ech.samples.java.junit5.features;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class _01UsingAnnotationTest {
	private static Logger logger = LoggerFactory.getLogger(_01UsingAnnotationTest.class);

	@Test
	@DisplayName("use test annotation")
	public void useTestAnnotation() {
		assertEquals(2, 1+1);
	}

	@RepeatedTest(2)
	@DisplayName("use repeated test annotation")
	public void useRepeatedTestAnnotation() {assertEquals(2, 1+1);}

	@Test
	public void useTestAnnotationAndAssertAll_case1() {
		assertAll(
			() -> {
					final Address address = new Address("Nancy", "Place Carnot");
					assertNotNull(address);
					assertEquals("Nancy", address.getCity());
			}, () -> {
					final Address address = new Address(null, null);
					assertNotNull(address); // not in failure.
					assertEquals("Nancy", address.getCity());
			}
			);
	}
	@Test
	public void useTestAnnotationAndAssertAll_case2() {
		assertAll(
				() -> {
					final Address address = null;
					assertNotNull(address); // in failure
					assertEquals("Nancy", address.getCity()); // not processed
				},
				() -> {
					final Address address = new Address("Nancy", "Place Carnot");
					assertNotNull(address);
					assertEquals("Nancy", address.getCity());
				});
	}


	public static class Address {
		String city;
		String street;
		public Address(String city, String street) {
			this.city = city;
			this.street = street;
		}
		public String getStreet() {
			return street;
		}
		public String getCity() {
			return city;
		}
	}
}
