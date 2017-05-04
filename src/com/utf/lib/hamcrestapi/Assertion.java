package com.utf.lib.hamcrestapi;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class Assertion {
	
	public String result = null;
	
	public static void assertEquals(Object expected, Object actual){
		
		try{
			assertThat(expected, equalTo(actual));	
		}
		catch(Exception e){
			
		}
	}
}