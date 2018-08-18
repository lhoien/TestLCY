package com.calculate;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class JUnit4 {
	
	Calculator cal = new Calculator();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHandleBackspace() {
		cal.setResultText("12300");
		cal.handleBackspace();
		assertEquals("1230.0", cal.getResultText());
	}


	@Test
	public void testHandleC() {
		cal.setResultText("12300");
		cal.handleC();
		assertEquals("0.0", cal.getResultText());
	}


	@Test
	public void testGetNumberFromText() {
		cal.setResultText("12300");
		assertEquals("12300.0", String.valueOf(cal.getNumberFromText()));
	}
	
	@Test
	public void testHandleOperator()
	{
		cal.setResultNum(10.0);
		cal.setResultText("10.0");
		cal.handleOperator("+");		
		cal.handleOperator("=");
		assertEquals("20.0", String.valueOf(cal.getResultNum()));
	}

}
