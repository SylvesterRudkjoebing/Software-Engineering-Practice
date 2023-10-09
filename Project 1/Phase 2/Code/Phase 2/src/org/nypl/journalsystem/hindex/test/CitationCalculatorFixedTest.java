package org.nypl.journalsystem.hindex.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.nypl.journalsystem.hindex.CitationCalculator;
import org.nypl.journalsystem.hindex.CitationCalculatorFixed;
import org.nypl.journalsystem.hindex.ICitationCalculator;

public class CitationCalculatorFixedTest {
	private CitationCalculatorFixed calculator;
	
	@BeforeEach
	public void setup() {
		calculator = new CitationCalculatorFixed();
	}
	
	@AfterEach
	public void tearDown() {
		calculator = null;
	}

	//TODO: Implement test cases for the citation calculator
	@Test
	public void testEmptyArray() {
    int[] numbers = {};
    int result = calculator.calculateHIndex(numbers);
    assertEquals(0, result);
	}
	
	@Test
	public void testSingleElementArray() {
		int[] numbers = {5};
		int result = calculator.calculateHIndex(numbers);
		assertEquals(1, result);
	}

	@Test
	public void testUnsortedArray() {
    int[] numbers = {5, 1, 3, 2, 4};
    int result = calculator.calculateHIndex(numbers);
    assertEquals(3, result);
	}

	@Test
	public void testAllZeros() {
    int[] numbers = {0, 0, 0, 0, 0};
    int result = calculator.calculateHIndex(numbers);
    assertEquals(0, result);
	}

	
	@Test
	public void testNormal() {
		int[] numbers = {1, 2, 8, 4000, 5};
		int result = calculator.calculateHIndex(numbers);
		assertEquals(3, result);
	}

	@Test
	public void testNegativeNumbers() {
    int[] numbers = {-2, -10, 2, 7, 5};
    int result = calculator.calculateHIndex(numbers);
    assertEquals(2, result);
	}

	@Test
	public void testDuplicateValues() {
    int[] numbers = {5, 5, 5};
    int result = calculator.calculateHIndex(numbers);
    assertEquals(3, result);
	}

	@Test
	public void testLargeArray() {
    int[] numbers = {1000, 20, 30, 40, 50, 60, 70, 80, 90, 100, 33};
    int result = calculator.calculateHIndex(numbers);
    assertEquals(11, result);
	}	
}
