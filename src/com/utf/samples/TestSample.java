package com.utf.samples;

import static com.utf.lib.hamcrestapi.Assertion.*;

import com.utf.test.TestCase;

public class TestSample extends TestCase{
	
	
	
	public void testEqualsPass()
	{
		this.setName("testEqualsPass");
		System.out.println(this.getName());
		assertEquals(1,1);
	}
	
	public void testEqualFail()
	{	
		this.setName("testEqualsFail");
		System.out.println(this.getName());
		assertEquals(1,1);
	}


	public static void main(String args[]){
	
		TestSample ts = new TestSample();
		ts.testEqualFail();
		ts.testEqualsPass();
		
		
	}
}
