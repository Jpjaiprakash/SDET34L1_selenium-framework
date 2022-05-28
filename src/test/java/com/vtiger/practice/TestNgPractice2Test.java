package com.vtiger.practice;

import org.testng.annotations.Test;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNgPractice2Test extends TestNGBasicConfigAnnotationPracticeTest {
	
	@Test(groups="sanity")
	public void practice1Test() {
		Reporter.log("TestNgPractice2----> Test1",true);
	}
	@Test(groups="regression")
	public void practice2Test() {
		Reporter.log("TestNgPracitce2----> Test2",true);
	}
	
	@Test(groups="sanity")
	public void practice3Test() {
		Reporter.log("TestNgPractice2----> Test3",true);
	}

}
