package com.bfm.training.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	
	Calculator calc;
	@Before
	public void initCalculator() {
		calc = new Calculator();
	}
	
	@Test
	public void shouldInitializeCalculator() {
		assertNotNull("Calculator must initialize",calc);
	}
	
	@Test
	public void shouldAdd2And3() {
		assertEquals("Should return 5",new Integer(5),calc.add(2,3));
	}
	
	@Test
	public void shouldSubtract2And3() {
		assertEquals("Should return -1", new Integer(-1), calc.subtract(2,3));
	}
}
