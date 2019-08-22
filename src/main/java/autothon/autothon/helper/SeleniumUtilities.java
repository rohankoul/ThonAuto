package autothon.autothon.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import autothon.autothon.MainController;

public class SeleniumUtilities {
	
	static String loc = "C:\\Users\\i340909\\git\\Autothon2019\\src\\main\\resources\\screenshot\\";
	
	public Boolean isEnabled(WebElement element,String xpath) {
		return element.isEnabled();
	}
	
	public Boolean isVisible(WebElement element,String xpath) {
		return element.isDisplayed();
	}
	
	
	static int TIMEOUT_WAIT_UNTIL = 20;
	public void enterInput(WebDriver driver,String xpath, String value,String screenshot_name, HTMLOutputWriter html) throws SeleniumScriptFailedException
	{
		try {
			//WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_WAIT_UNTIL);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			WebElement e = driver.findElement(By.xpath(xpath));
			e.clear();
			e.sendKeys(value);
			Screenshot.takeScreenshot(driver, loc+screenshot_name);
			html.insertRow("Input", value, "",loc+screenshot_name, 1);
			System.out.println("Entered"+value);
		}catch(Exception e) {
			
			//String loc = "C:\\\\Users\\\\i340909\\\\OneDrive - SAP SE\\\\Autothon\\\\screenshots\\\\error_"+screenshot_name;
			Screenshot.takeScreenshot(driver, loc+screenshot_name);
			System.out.println("Exception in enter input method -> xpath = "+xpath);
			html.insertRow("Input",e.getMessage(),"",loc+screenshot_name, 0);
			throw new SeleniumScriptFailedException (e);
		}finally {
			
		}
		
	}
	
	public static void mouseHover(WebDriver driver, WebElement videoScreen, WebElement settingsQuality, WebElement resolutionPath)
	{
		//Instantiate Action Class        
	    Actions actions = new Actions(driver);
	    //Mouse hover video screen
	    actions.moveToElement(videoScreen).perform();
	    System.out.println("Done Mouse hover");
	 
	    //Now Select 'Settings' from sub menu which has got displayed on mouse hover of 'Video Screen'
	    //Mouse hover menuOption 'Settings'
	    actions.moveToElement(settingsQuality).perform();
	    System.out.println("Done Mouse hover on 'Settings' ");
	 
	    //Now , finally, it displays the desired menu list from which required option needs to be selected
	    WebElement selectMenuOption = driver.findElement(By.xpath(".//div[contains(text(),'Alternative')]"));
	    settingsQuality.click();
	    System.out.println("Selected 'settings' from Video");
	    resolutionPath.click();

	}
	
	public void enterInputAndSubmit(WebDriver driver,String xpath, String value,String screenshot_name, HTMLOutputWriter html) throws SeleniumScriptFailedException
	{
		
		try {
			//WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_WAIT_UNTIL);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			WebElement e = driver.findElement(By.xpath(xpath));
			e.clear();
			e.sendKeys(value);
			e.submit();
			html.insertRow("Input & Submit",value, "",loc+screenshot_name, 1);
			Screenshot.takeScreenshot(driver, loc+screenshot_name);
			System.out.println("Entered & submitted"+value);
		}
		catch(Exception e) {
		//	String loc = "C:\\\\Users\\\\i340909\\\\OneDrive - SAP SE\\\\Autothon\\\\screenshots\\\\error_"+screenshot_name;
			Screenshot.takeScreenshot(driver, loc+screenshot_name);
			html.insertRow("Input & Submit",e.getMessage(),"", loc+screenshot_name, 0);
			System.out.println("Exception in enter input and submit method -> xpath = "+xpath);
			throw new SeleniumScriptFailedException (e);
		}finally {
			
		}
		
		
	}
	
	
	public void scrollDown(WebDriver driver, WebElement element)
	{
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		//To maximize the window.		
	     driver.manage().window().maximize();
	   //This will scroll the page till the element is found		
	     js.executeScript("arguments[0].scrollIntoView();", element);

	}
	
	public WebElement findElement(WebDriver driver,String xpath,HTMLOutputWriter html) throws SeleniumScriptFailedException {
		try {
			//WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_WAIT_UNTIL);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			WebElement e = driver.findElement(By.xpath(xpath));
			//e.click();
			//html.insertRow("Click", xpath, "", 1);
			System.out.println("Found "+xpath+" Sucessfully");
			
			//Screenshot.takeScreenshot(driver, loc+screenshot_name);
			return e;
			
		}
		catch(Exception ex)
		{
			//String loc = "C:\\\\Users\\\\i340909\\\\OneDrive - SAP SE\\\\Autothon\\\\screenshots\\\\error_"+screenshot_name;
			System.out.println("Exception in enter find and click element method -> xpath = "+xpath);
			//Screenshot.takeScreenshot(driver, "C:\\\\Users\\\\i340909\\\\OneDrive - SAP SE\\\\Autothon\\\\screenshots\\\\error_"+screenshot_name);
			//html.insertRow("Click", ex.getMessage(),loc, 0);
			//Screenshot.takeScreenshot(driver, loc+screenshot_name);
			
			
			throw new SeleniumScriptFailedException (ex);
			
		}finally {
			
		}
		
	}
	
	public WebElement clickElement(WebDriver driver, String label, WebElement e,String screenshot_name,HTMLOutputWriter html) throws SeleniumScriptFailedException {
		try {
			//WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_WAIT_UNTIL);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			e.click();
			html.insertRow("Click",label, "",loc+screenshot_name, 1);
			System.out.println("Click on Sucessfully");
			Screenshot.takeScreenshot(driver, loc+screenshot_name);
			
			return e;
			
			
		}
		catch(Exception ex)
		{
			//String loc = "C:\\\\Users\\\\i340909\\\\OneDrive - SAP SE\\\\Autothon\\\\screenshots\\\\error_"+screenshot_name;
			//System.out.println("Exception in enter find and click element method -> xpath = "+xpath);
			//Screenshot.takeScreenshot(driver, "C:\\\\Users\\\\i340909\\\\OneDrive - SAP SE\\\\Autothon\\\\screenshots\\\\error_"+screenshot_name);
			html.insertRow("Click", ex.getMessage(),"",loc+screenshot_name, 0);
			Screenshot.takeScreenshot(driver, loc+screenshot_name);
			
			
			throw new SeleniumScriptFailedException (ex);
			
		}finally {
			
		}
		
	}
	
	public void findAndClickElement(WebDriver driver,String xpath,String label,String screenshot_name, HTMLOutputWriter html) throws SeleniumScriptFailedException {
		
		
		try {
			//WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_WAIT_UNTIL);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			WebElement e = driver.findElement(By.xpath(xpath));
			e.click();
			html.insertRow("Click", label, "",loc+screenshot_name, 1);
			System.out.println("Clicked "+xpath+" Sucessfully");
			Screenshot.takeScreenshot(driver, loc+screenshot_name);
			MainController.generateResultsWindow(1,"Click", "", "", "success", "","ui");
			
			
		}
		catch(Exception ex)
		{
			//String loc = "C:\\\\Users\\\\i340909\\\\OneDrive - SAP SE\\\\Autothon\\\\screenshots\\\\error_"+screenshot_name;
			System.out.println("Exception in enter find and click element method -> xpath = "+xpath);
			//Screenshot.takeScreenshot(driver, "C:\\\\Users\\\\i340909\\\\OneDrive - SAP SE\\\\Autothon\\\\screenshots\\\\error_"+screenshot_name);
			html.insertRow("Click", ex.getMessage(),"",loc+screenshot_name, 0);
			Screenshot.takeScreenshot(driver, loc+screenshot_name);
			MainController.generateResultsWindow(1,"Click", "", "", "Failed", "","ui");
			
			throw new SeleniumScriptFailedException (ex);
		}finally {
			
		}
		
	}
	
	public void goToPage(WebDriver driver,String url,String screenshot_name, HTMLOutputWriter html) throws SeleniumScriptFailedException {
		try{
			
			driver.get(url);
			html.insertRow("Navigate to", url , "",loc+screenshot_name, 1);
			MainController.generateResultsWindow(1,"Open YouTube", "", "", "success", "","ui");
			
		}
		catch(Exception e) {
			//String loc = "C:\\\\Users\\\\i340909\\\\OneDrive - SAP SE\\\\Autothon\\\\screenshots\\\\error_"+screenshot_name;
			//Screenshot.takeScreenshot(driver, "C:\\\\Users\\\\i340909\\\\OneDrive - SAP SE\\\\Autothon\\\\screenshots\\\\error_"+screenshot_name);
			html.insertRow("Navigate to", e.getMessage(), "", loc+screenshot_name , 0);
			System.out.println("Exception in Go to Page method");
			Screenshot.takeScreenshot(driver, loc+screenshot_name);
			MainController.generateResultsWindow(1,"Open YouTube", "", "", "Failed", "","ui");
			throw new SeleniumScriptFailedException (e);
		}finally {
			
		}
	}
	
	public String getDataFromElement(WebDriver driver,String xpath, String screenshot_name, HTMLOutputWriter html)
	{
		try {
			//WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_WAIT_UNTIL);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			WebElement e = driver.findElement(By.xpath(xpath));
			String text = e.getText();
			html.insertRow("Read", "" , text, loc+screenshot_name, 1);
			Screenshot.takeScreenshot(driver, loc+screenshot_name);
			return text;
		}
		catch(Exception ex) {
			//String loc = "C:\\\\Users\\\\i340909\\\\OneDrive - SAP SE\\\\Autothon\\\\screenshots\\\\error_"+screenshot_name;
		//	Screenshot.takeScreenshot(driver, loc);
			Screenshot.takeScreenshot(driver, loc+screenshot_name);
			html.insertRow("Read", ex.getMessage() , "",loc+screenshot_name, 0);
			System.out.println("Exception in get data from element method xpath ->"+xpath);
			return "";
			
		}finally {
			
		}
		
	}
	
	
}
		
	
	

