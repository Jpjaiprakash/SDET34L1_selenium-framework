package com.vtiger.practice;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNgAssertion1 {
	
	@Test
	public void pracice1() {
		
		Reporter.log("a-practice1",true);
		Reporter.log("b-practice1",true);
		Reporter.log("c-practice1",true);
		Reporter.log("d-practice1",true);
		Assert.fail();
		Reporter.log("e-practice1",true);
	}
	
	public void pracice2() {
		Reporter.log("f-practice2",true);
		Reporter.log("g-practice2",true);
		Reporter.log("h-practice2",true);
		Reporter.log("i-practice2",true);
		Assert.fail();
		Reporter.log("j-practice2",true);
	}
	
	public void pracice3() {
		Reporter.log("k-practice3",true);
		Reporter.log("l-practice3",true);
		Reporter.log("m-practice3",true);
		Reporter.log("n-practice3",true);
		Assert.fail();
		Reporter.log("o-practice3",true);
	}



}
