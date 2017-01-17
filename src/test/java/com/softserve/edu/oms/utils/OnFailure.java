package com.softserve.edu.oms.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * @author SOftserve academy
 *
 */
public class OnFailure extends TestListenerAdapter {

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			

			String methodName = result.getName();
			
			Object webDriverAttribute = 
					result.getTestContext().getAttribute("WebDriver");
			
			WebDriver screendriver = (WebDriver) webDriverAttribute;


			if (screendriver instanceof WebDriver) {
				
				ScreenShots.takeScreenShot(screendriver, methodName);
			
			} else {
				System.out.println("Driver not exist");
			}
		}
	}

}
