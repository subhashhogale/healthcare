package HealthCare;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class takeScreenshot {
	
	
	public static void takeScreenshot1(WebDriver driver,String name) throws IOException  {

	    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	   File f= new File("target/" + name + ".png");
	   
	   org.openqa.selenium.io.FileHandler.copy(src, f);
	    
	}
	
	
	
}
