package com.cli.rpg.base;

import java.io.Serializable;

public class ProbabilityCheck implements Serializable {
	 private double value;
	 
	 public ProbabilityCheck(double value) {
	        if (value < 0) {
	            throw new IllegalArgumentException("Invalid probability check!");
	        }
	        this.value = value;
	    }
	 
	 public boolean pass() {
	        return Math.random() < value;
	    }

}
