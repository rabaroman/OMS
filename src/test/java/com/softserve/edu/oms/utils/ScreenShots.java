package com.softserve.edu.oms.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.allure.annotations.Attachment;



/**
 * @author snob
 *
 */
public class ScreenShots {
	
	/**
	 * @param driver
	 * @param methodName
	 * @return
	 */

	public static void takeScreenShot(WebDriver driver, String methodName) {
		
		if (driver !=null){
			
		File screenShot = 
				((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		DateFormat dateFormat = 
				new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
		
		Date date = new Date();
		
		String fileName = 
				"test-output/screenshots/" + methodName +"_" + dateFormat.format(date) + ".png";
		
		String screnshotName = methodName + "_" + dateFormat.format(date);
		
		attachScreenshotToAllure(screnshotName, driver);
		
		try {
			
			FileUtils.copyFile(screenShot, new File(fileName));
		
		} catch (IOException e) {
			
			System.out.println("ERROR OCCURRED WHILE TAKING SCREENSHOT");
			e.printStackTrace();
		}
		
		} else
		{System.out.println("driver not exist");
		}
		
	}
	
	@Attachment(value = "{0}", type="image/png")
	private static byte[] attachScreenshotToAllure(String screenshotName, WebDriver driver ){
		
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		
	}

}
