package com;

import java.time.LocalDate;

import org.junit.Test;

public class LocalDateTest {

	@Test
	public void case1() {
		LocalDate date = LocalDate.now();
		System.out.println(date);
	}
	
}
