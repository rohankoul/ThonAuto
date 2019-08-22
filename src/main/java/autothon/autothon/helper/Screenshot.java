package autothon.autothon.helper;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.swing.text.html.HTML;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	public static void takeScreenshot(WebDriver driver,String path)
	{
		

		 File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 try {
		  // now copy the  screenshot to desired location using copyFile //method
			 
			 Timestamp ts = new Timestamp(System.currentTimeMillis());
			 File DestFile=new File(path);
		     FileUtils.copyFile(source, DestFile);
		     
			 
		 }
		  
		 catch (IOException exc)
		  {
			 System.out.println(exc.getMessage());
		  }
		
				
	}
	
}
