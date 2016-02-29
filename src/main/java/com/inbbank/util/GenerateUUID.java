package com.inbbank.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class GenerateUUID {


	 private GenerateUUID() {
        
	    }

	public static final String getRendomString(){
	    //generate random UUIDs
	    return String.valueOf(UUID.randomUUID());
	    	  }
	  
	public static BigDecimal getRandomNumber(){
		
		
		
		  BigDecimal randFromDouble = new BigDecimal(Math.random());
		  return randFromDouble;
	}
	
	public static BigDecimal getRandomNumberInRange() {
		int min=1000,  max=1000000;
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return new BigDecimal(r.nextInt((max - min) + 1) + min);
	}
}
