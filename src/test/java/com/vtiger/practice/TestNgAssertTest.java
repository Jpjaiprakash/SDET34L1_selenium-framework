package com.vtiger.practice;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNgAssertTest {
	/*@Test
	public void practice1Test() {
		Reporter.log("a-practice1",true);
		Reporter.log("b-practice1",true);
		Reporter.log("c-practice1",true);
		Assert.assertEquals("abc", "xyz");
		Reporter.log("d-practice1",true);
		Reporter.log("e-practice1",true);
		Reporter.log("f-practice1",true);
	}
	
	@Test
	public void practice2Test() {
		Reporter.log("e-practice2",true);
		Reporter.log("d-practice2",true);
		Reporter.log("c-practice2",true);
		Reporter.log("c-practice2",true);
		Reporter.log("b-practice2",true);
		Reporter.log("a-practice2",true);
}
	@Test
	public void practice3Test() {
		Reporter.log("e-practice3",true);
		Reporter.log("d-practice3",true);
		Reporter.log("c-practice3",true);
		Reporter.log("c-practice3",true);
		Reporter.log("b-practice3",true);
		Reporter.log("a-practice3",true);
	}*/
	
	/*@Test
	public void practice1Test() {
		Reporter.log("a-practice1",true);
		Reporter.log("b-practice1",true);
		Reporter.log("c-practice1",true);
		Assert.fail();
		Reporter.log("d-practice1",true);
		Reporter.log("e-practice1",true);
		Reporter.log("f-practice1",true);
	}
	
	@Test
	public void practice2Test() {
		Reporter.log("e-practice2",true);
		Reporter.log("d-practice2",true);
		Reporter.log("c-practice2",true);
		Assert.fail();
		Reporter.log("c-practice2",true);
		Reporter.log("b-practice2",true);
		Reporter.log("a-practice2",true);
}
	@Test
	public void practice3Test() {
		Reporter.log("e-practice3",true);
		Reporter.log("d-practice3",true);
		Reporter.log("c-practice3",true);
		Reporter.log("c-practice3",true);
		Reporter.log("b-practice3",true);
		Reporter.log("a-practice3",true);
	}*/
	
	/*@Test
	public void practice1Test() {
		Reporter.log("a-practice1",true);
		Reporter.log("b-practice1",true);
		Reporter.log("c-practice1",true);
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertEquals("abc","xyz" );
		Reporter.log("d-practice1",true);
		Reporter.log("e-practice1",true);
		Reporter.log("f-practice1",true);
	}
	
	@Test
	public void practice2Test() {
		Reporter.log("e-practice2",true);
		Reporter.log("d-practice2",true);
		Reporter.log("c-practice2",true);
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertEquals("abc","xyz" );
		Reporter.log("c-practice2",true);
		Reporter.log("b-practice2",true);
		Reporter.log("a-practice2",true);
}
	@Test
	public void practice3Test() {
		Reporter.log("e-practice3",true);
		Reporter.log("d-practice3",true);
		Reporter.log("c-practice3",true);
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertEquals("abc","xyz" );
		Reporter.log("c-practice3",true);
		Reporter.log("b-practice3",true);
		Reporter.log("a-practice3",true);
	}*/
	
	@Test
	public void practice4Test() {
		Reporter.log("a-practice4",true);
		Reporter.log("b-practice4",true);
		Reporter.log("c-practice4",true);
		String a="abc";
		Assert.assertTrue(a.contains("abcd"));
		Reporter.log("d-practice4",true);
		Reporter.log("e-practice4",true);
		Reporter.log("f-practice4",true);
	}
	
	@Test
	public void practice5Test() {
		Reporter.log("a-practice5",true);
		Reporter.log("b-practice5",true);
		Reporter.log("c-practice5",true);
		String a="xyz";
		Assert.assertTrue(a.contains("xyza"));
		Reporter.log("d-practice5",true);
		Reporter.log("e-practice5",true);
		Reporter.log("f-practice5",true);
	}
}

	
