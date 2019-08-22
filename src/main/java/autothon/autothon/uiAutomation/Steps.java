package autothon.autothon.uiAutomation;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import autothon.autothon.nonUI.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import autothon.autothon.MainController;
import autothon.autothon.helper.*;

public class Steps extends Thread{

	
	
	String browser;
	String device;
	String status;
	WebDriver driver;
	
	
	public Steps(String browser, String device) {
		super();
		this.browser = browser;
		this.device = device;
		this.status = status;
		if(this.device.toLowerCase().equals("android") && this.browser.toLowerCase().equals("chrome") ) {
			try {
				this.driver = androidChromeFactory();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(this.device.toLowerCase().equals("windows") && this.browser.toLowerCase().equals("chrome") ) {
			try {
				this.driver = winChromeFactory();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(this.device.toLowerCase().equals("windows") && this.device.toLowerCase().equals("firefox") ) {
			try {
				this.driver = firefoxFactory();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private WebDriver androidChromeFactory() throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();// DesiredCapabilities.android();
		cap.setCapability("deviceName", "Moto G (5S) Plus");
		cap.setCapability("udid", "ZY32285CLZ");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "8.1.0");
		cap.setCapability("browserName", "Chrome");
		//cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
		WebDriver d = new RemoteWebDriver(new URL("http://localhost:4723/wd/hub"), cap);
		//WebDriver d = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), cap);
		
		return d;
	}

	private WebDriver firefoxFactory() throws MalformedURLException {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--start-maximized");
		//options.addArguments("--headless");
		//WebDriver d = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		WebDriver d = new FirefoxDriver(options);
		return d;
	}

	private WebDriver winChromeFactory() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		//options.addArguments("--headless");
		//WebDriver d = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		WebDriver d = new ChromeDriver(options);
		return d;
	}

	public void startExecution() {
		
		SeleniumUtilities utl = new autothon.autothon.helper.SeleniumUtilities();
		 
		 Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 String timeStamp = sdf.format(timestamp);
		HTMLOutputWriter html = new HTMLOutputWriter("C:\\Users\\i340909\\git\\Autothon2019\\src\\main\\resources\\"+this.device+"_run"+timeStamp+".html","Windows YouTube Execution");
		
		try{
			
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			html.createTable("Main");
			
			utl.goToPage(driver, "https://youtube.com", this.device+"_1.png", html); // go to you tube 1
			
			
			//WebDriverWait wait = new WebDriverWait(driver, 15);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div/div/input")));
			utl.enterInput(driver, "//form/div/div/input", "step-inforum", this.device+"2.png", html); // input in search bar 2
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/button")));
			utl.findAndClickElement(driver, "//form/button","", "dummy", html); // click on search button 
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/user/stepinforum' and @class='yt-simple-endpoint style-scope ytd-channel-renderer']")));
			WebElement e = utl.findElement(driver, "//a[@href='/user/stepinforum' and @class='yt-simple-endpoint style-scope ytd-channel-renderer']",html); //find the searched channel name;
			utl.clickElement(driver,"Step-In Channel", e, this.device+"_3.png",html);  // click on the channel name 3 
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//paper-tab[contains(*,'Videos')]")));
			e = utl.findElement(driver, "//paper-tab[contains(*,'Videos')]",html);
			utl.clickElement(driver,"Videos Tab", e, this.device+"_4.png",html);
			System.out.println("Clicked on videos tab");
			
			//API call
			youtube_api api = new youtube_api();
			String movieName;
			do {
				movieName = api.getMovieName();
				
			}while(movieName.length()>50);
			
			html.insertRow("API call - Windows", "", movieName,"", 1);
			System.out.println("Movie name caught in UI is :"+ movieName);
			
			
			WebElement element;
			while(true) {
				try {
					element = utl.findElement(driver, "//a[contains(@aria-label,'"+movieName+"')]",html); 
					break;
				}
				catch(Exception exp) {
					System.out.println("Scrolling...");
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0,40000)");
				}
				
				
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",element);
					
			html.insertRow("Captured Screenshot", movieName,"","C:\\Users\\i340909\\git\\Autothon2019\\src\\main\\resources\\screenshot\\"+device+"_6.png", 1);
			Screenshot.takeScreenshot(driver,"C:\\Users\\i340909\\git\\Autothon2019\\src\\main\\resources\\screenshot\\"+device+"_6.png");
			
			((JavascriptExecutor) driver).executeScript("function scrollToElement(pageElement) {    " + 
					"    var positionX = 0,         " + 
					"        positionY = 0;    " + 
					"" + 
					"    while(pageElement != null){        " + 
					"        positionX += pageElement.offsetLeft;        " + 
					"        positionY += pageElement.offsetTop;        " + 
					"        pageElement = pageElement.offsetParent;        " + 
					"           " + 
					"    }" + 
					"		positionY -= 500;	" + 
					"		window.scrollTo(positionX, positionY); " + 
					"}" + 
					"" + 
					"var pageElement = document.evaluate(\"(//a[contains(*,'SUMMIT 2017 Highlights')])[1]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;\r\n" + 
					"scrollToElement(pageElement);");
			
				
			System.out.println(" scrolled and opened the video successfully");
			utl.clickElement(driver,movieName, element, device+"_8.png",html);
	

			driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
			
			//div[@class='ytp-right-controls']/button[contains(@class,'ytp-button ytp-settings-button')]
			
			try {
				utl.findAndClickElement(driver, "//div[@class='ytp-right-controls']/button[contains(@class,'ytp-button ytp-settings-button')]","Settings Button" ,device+"_9.png", html);//settings button
				utl.findAndClickElement(driver, "//div[@role='menuitem' and contains(*,'Quality')]","Quality Button", device+"_10.png", html);//click on quality
				
			}
			catch(Exception exp) {
				utl.findAndClickElement(driver, "//button[contains(*,'Skip Ads')]","Skip Ads",device+"_skipad.png",html);
				utl.findAndClickElement(driver, "//div[@class='ytp-right-controls']/button[contains(@class,'ytp-button ytp-settings-button')]","Settings Button" ,device+"_9.png", html);//settings button
				utl.findAndClickElement(driver, "//div[@role='menuitem' and contains(*,'Quality')]","Quality Button", device+"_10.png", html);//click on quality
				
			}
			//utl.findAndClickElement(driver, "//*[@id=\"movie_player\"]/div[21]/div[2]/div[2]/button[3]","Settings Button" ,device+"_9.png", html);//settings button
			//utl.findAndClickElement(driver, "//*[@id=\"ytp-id-18\"]/div/div/div[3]/div[1]","Quality Button", device+"_10.png", html);//click on quality
			
			//utl.findAndClickElement(driver, "//div[@role='menuitem' and contains(*,'Quality')]","Quality Button", device+"_10.png", html);//click on quality
			//utl.findAndClickElement(driver, "(//button[@aria-label='Settings'])[2]","Resolution Settings",device+"_11.png", html);
		
			//utl.findAndClickElement(driver, "//*[text()='360p']", device+"_11.png", html);
			//div[contains(@class,'ytp-popup ytp-settings-menu')]//span[contains(text(),'360p')]
			utl.findAndClickElement(driver, "//div[contains(@class,'ytp-popup ytp-settings-menu')]//div[div[span[contains(text(),'360p')]]]","360p Resolution changed", device+"_11.png", html);
			utl.findAndClickElement(driver, "//div[@class='ytp-right-controls']/button[contains(@class,'ytp-button ytp-settings-button')]","Settings Button" ,device+"_check.png", html);//settings button
			
			
			//button[contains(*,'Skip Ads')]
			/*String inputFilePath = "C:\\Users\\I340909\\Pictures\\Sikuli";
			Screen s = new Screen();
			s.click(inputFilePath + "\\settingsButton.jpg"); 
			s.click(inputFilePath + "\\Quality.jpg"); 
			s.click(inputFilePath + "\\360p.jpg");*/
			
			// steps
			String mid = api.getMovieID(movieName);
			api.getRelatedMovies(mid,movieName);
			html.insertRow("API: Fetch Related Movies", "", "", "", 1);
			
			api.postData();
			html.insertRow("API: Upload/POST JSON file", "", "", "", 1);
			
			api.Validate();
			html.insertRow("API: Validating Response", "", "", "", 1);
			
		}
		catch (Exception e) {
			System.out.println("Exception E " + e.getMessage());
			html.closeTable(0);
			
		} finally {
			// html.closeHTML();
			html.closeHTML();
			html.cleanup();
			driver.quit();
			
		}
	
		
		
		
		
		
	}
	
	
	public void startExecutionMobile() {
		SeleniumUtilities utl = new autothon.autothon.helper.SeleniumUtilities();
		 
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 String timeStamp = sdf.format(timestamp);
		HTMLOutputWriter html = new HTMLOutputWriter("C:\\Users\\i340909\\git\\Autothon2019\\src\\main\\resources\\"+this.device+"_run"+timeStamp+".html","Android YouTube Execution");
		
		try{
			

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			html.createTable("Main2");
			
			utl.goToPage(driver, "https://youtube.com", this.device+"_1.png", html); // go to you tube 1
			
			//WebElement e = utl.findElement(driver, "//form//button[contains(@aria-label,'Search YouTube')]");
			
			WebElement e = utl.findElement(driver, "//*[@id=\"header-bar\"]/header/div/button",html);
		
			utl.clickElement(driver,"", e, this.device+"_1.png",html);
			
			utl.enterInput(driver, "//form//input[contains(@placeholder,'Search YouTube')]", "step-inforum", this.device+"2.png", html); // input in search bar 2
			
			WebElement e2 = utl.findElement(driver, "//form//button[contains(@aria-label,'Search YouTube')]",html);
			utl.clickElement(driver,"Search YouTube", e2, this.device+"_3.png",html);
			
			WebElement e3 = utl.findElement(driver, "(//ytm-compact-channel-renderer//a[@href='/user/stepinforum'])[1]",html);
			utl.clickElement(driver,"Step-IN channel", e3, this.device+"_4.png",html);
			
			WebElement e4 = utl.findElement(driver,"//a[@href='/user/stepinforum/videos']",html);
			utl.clickElement(driver,"Videos Tab", e4, this.device+"_5.png",html);
			
			//Step 5 API call
			String movieName;
			youtube_api api = new youtube_api();
			do {
				movieName = api.getMovieName();
				
			}while(movieName.length()>50);
			
			html.insertRow("API call - Mobile", "", movieName,"", 1);
			System.out.println("Movie name caught in UI is :"+ movieName);
			
			
			WebElement element;
			while(true) {
				try {
					element = utl.findElement(driver, "(//a[contains(*,'"+movieName+"')])[1]",html); 
					break;
				}
				catch(Exception exp) {
					System.out.println("Mobile: Scrolling...");
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0,40000)");
					WebElement e6 = utl.findElement(driver, "(//button[contains(*,'Show more')])[1]",html);
					utl.clickElement(driver,"Show More", e6, this.device+"_dummy.png",html);
				}
				
				
				
				
			}
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",element);
			
			Screenshot.takeScreenshot(driver,"C:\\Users\\i340909\\git\\Autothon2019\\src\\main\\resources\\screenshot\\"+device+"_6.png");
			
			/*((JavascriptExecutor) driver).executeScript("function scrollToElement(pageElement) {    " + 
					"    var positionX = 0,         " + 
					"        positionY = 0;    " + 
					"" + 
					"    while(pageElement != null){        " + 
					"        positionX += pageElement.offsetLeft;        " + 
					"        positionY += pageElement.offsetTop;        " + 
					"        pageElement = pageElement.offsetParent;        " + 
					"           " + 
					"    }" + 
					"		positionY -= 500;	" + 
					"		window.scrollTo(positionX, positionY); " + 
					"}" + 
					"" + 
					"var pageElement = document.evaluate(\"(//a[contains(*,'SUMMIT 2017 Highlights')])[1]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;\r\n" + 
					"scrollToElement(pageElement);");*/
			
				
			System.out.println("Mobile:  scrolled successfully");
			
			utl.clickElement(driver,movieName, element, device+"_8.png",html);
			
			utl.findAndClickElement(driver, "//*[@id='header-bar']/header/div/ytm-menu/button","Settings Button" ,device+"_9.png", html);//settings button
			
			utl.findAndClickElement(driver, "//button[@class='menu-item-button' and text()='Playback Settings']","Playback Settings",device+"_10.png", html);//click on quality
	
			utl.findAndClickElement(driver, "//ytm-select[@class='player-quality-settings']/select","Quality", device+"_11.png", html);
			
			utl.findAndClickElement(driver, "//ytm-select[@class='player-quality-settings']/select/option[contains(text(),'360p')]","360p", device+"_12.png", html);
		
			utl.findAndClickElement(driver, "//button[contains(*,'OK')]","Ok" ,device+"_13.png", html);
			
			
			String mid = api.getMovieID(movieName);
			
			api.getRelatedMovies(mid,movieName);
			html.insertRow("API: Fetch Related Movies", "", "", "", 1);
			
			api.postData();
			html.insertRow("API: Upload/POST JSON file", "", "", "", 1);
			
			api.Validate();
			html.insertRow("API: Validating Response", "", "", "", 1);
			
			System.out.println("Success");
			
			
			
			
		}
		catch(Exception e) {
			System.out.println("Exception E " + e.getMessage());
			html.closeTable(0);
			
			
		}finally {
			// html.closeHTML();
			html.closeHTML();
			html.cleanup();
			driver.quit();
			
		}
		
	}
	
	public void run() {
		
		if(this.device.toLowerCase().equals("windows"))
		{
			startExecution();
		}
		else if(this.device.toLowerCase().equals("android"))
		{
			startExecutionMobile();
		}
	}
	
}
