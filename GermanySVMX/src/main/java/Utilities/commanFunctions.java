package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public class commanFunctions {
	public static WebDriver driver;
	static ExtentReportsFunctions Reports = new ExtentReportsFunctions();
	public static void initialize() throws InterruptedException
	{	
		DesiredCapabilities capabilities = new DesiredCapabilities();
		 capabilities.setCapability("something", true);
		 System.setProperty("webdriver.chrome.driver","Test\\resource\\driver\\chromedriver.exe");
		 ChromeOptions chromeOptions = new ChromeOptions();
		 //chromeOptions.addArguments("--start-maximized");
		 driver = new ChromeDriver(chromeOptions);
		 Thread.sleep(2000);
	}
	public static WebDriver OpenBrowser(int iTestCaseRow) throws Exception{
		String sBrowserName;
		cLog.startTestCase("Start Test cases");
		
		try{
		sBrowserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Browser);
		if(sBrowserName.equals("Mozilla")){
			driver = new FirefoxDriver();
			cLog.info("New driver instantiated");
			Reports.PassTest("New driver instantiated");
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    cLog.info("Implicit wait applied on the driver for 10 seconds");
		    driver.get(Constant.URL);
		    Reports.PassTest("Web application launched successfully");
		    cLog.info("Web application launched successfully");
			}
		if(sBrowserName.equals("Chrome")){
			
			System.setProperty("webdriver.chrome.driver",Constant.Path_Driver);
			driver = new ChromeDriver();
			Reports.PassTest("New driver instantiated");
			
			Thread.sleep(1000);
			cLog.info("New driver instantiated");
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    cLog.info("Implicit wait applied on the driver for 10 seconds");
		    Thread.sleep(1000);
		    driver.get(Constant.URL);
		    driver.manage().window().maximize();
		    Reports.PassTest("Web application launched successfully");
		    cLog.info("Web application launched successfully");
			}
		}catch (Exception e){
			cLog.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
			Reports.FailTest("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		}
		return driver;
	}
	public static String getTestCaseName(String sTestCase)throws Exception{
		String value = sTestCase;
		try{
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");	
			value = value.substring(posi + 1);
			System.out.println(value);
			return value;
				}catch (Exception e){
			cLog.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
			Reports.FailTest("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
			throw (e);
					}
			}
	
	
	//for Verifying if Element is Present or not
	public static boolean isElementPresent(String Element ) {
		 WebElement locator;
		 
	  try {
		  locator =driver.findElement(By.xpath(Element));
		  	locator.isDisplayed();
		    return true;
		  }
	  catch (NoSuchElementException e) {
		  Reports.FailTest("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		    return false;
		  }
		}

	public static boolean isElementPresentwithoutResult(String Element ) {
		 WebElement locator;
		 
	  try {
		  locator =driver.findElement(By.xpath(Element));
		  	locator.isDisplayed();
		    return true;
		  }
	  catch (NoSuchElementException e) {
		  //Reports.FailTest("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		    return false;
		  }
		}	
		//for focusing on perticular element 
		public static void mouse_over(String lnkApplication){
				
		
			Actions act = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(lnkApplication));
			act.moveToElement(we).perform();
			}

		// for Capturing Screenshot
		public static String capture_screenShot(String screenshotName) throws Exception {
			try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());			
			String destination = Constant.Path_ScreenShot + screenshotName + dateName + ".png";
			 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	            FileUtils.copyFile(scrFile, new File(destination));
			return destination;
			}
			catch (Exception e){
				Reports.FailTestwithScreenShot("Class Utils | Method capture_screenShot | Exception desc : "+e.getMessage(),"Fail");
				throw (e);
			}
		}
		//for waiting particular element to come on page
          public static  void fluent_wait(String element,long wait_time, int polling_wait_time) throws Exception {
        	  try {
        	  Duration dwait_time=Duration.ofSeconds(wait_time);
        	
            	By Elementby=By.xpath(element);
             
             FluentWait<WebDriver> fwait =  new FluentWait<WebDriver>(driver)  
                                          		.withTimeout(dwait_time)
                                          		
                                                .pollingEvery(dwait_time)  
                                                .ignoring(NoSuchElementException.class);                                                   
            fwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Elementby));                        
             System.out.println("Element Found.."+ element);
        	  }
        	  catch(Exception e) {
        		  Reports.FailTest("Class Utils | Method fluent_wait | Exception desc : "+e.getMessage());
  				throw (e);
        	  }
          }
          
          //for Clicking on any particular element
          public static void elementClick(String locator) throws Exception
      	{
        	  try {
        	 WebElement Elementby=driver.findElement(By.xpath(locator));
      		Elementby.click();
        	  }
        	  catch(Exception e) {
        		  //Reports.FailTest("Class Utils | Method elementClick | Exception desc : "+e.getMessage());
  				//throw (e);
        	  }
        	  
      	}
          
          public static void elementClickWithoutResult(String locator)
        	{
          	  try {
          	 WebElement Elementby=driver.findElement(By.xpath(locator));
        		Elementby.click();
          	  }
          	  catch(Exception e) {
          		  /*Reports.FailTest("Class Utils | Method elementClick | Exception desc : "+e.getMessage());
    				throw (e);*/
          		  System.out.println("clicked again");
          	  }
          	  
        	}
          //for SendKeys
          
          public static void inputValue(String locator, String val)
      	{
        	  WebElement Elementby=driver.findElement(By.xpath(locator));
        	  Elementby.sendKeys(val);
      	}
          public static void inputValueThenEnter(String locator, String val)
        	{
          	  WebElement Elementby=driver.findElement(By.xpath(locator));
          	  Elementby.sendKeys(val);
          	  Elementby.sendKeys(Keys.ENTER);
        	}
          
          //for Clear on any particular element
          public static void elementClear(String locator)
      	{
        	  WebElement Elementby=driver.findElement(By.xpath(locator));
      		  Elementby.clear();
      	}
          //for Selecting value
          public static void dropdownSelect(String locator, String val)
      	{
        	  WebElement Elementby=driver.findElement(By.xpath(locator));
      		Select drp = new Select(Elementby);
      		drp.selectByVisibleText(val);
      	}
          
          public static void dropdownSelectByindex(String locator, int val)
      	{
        	  WebElement Elementby=driver.findElement(By.xpath(locator));
      		Select drp = new Select(Elementby);
      		drp.selectByIndex(val);
      	}
          
          public static void dropdownSelectByValue(String locator, String val)
        	{
          	  WebElement Elementby=driver.findElement(By.xpath(locator));
        		Select drp = new Select(Elementby);
        		drp.selectByValue(val);
        		
        	}
          //for Getting Attribute
          public static String GetValue(String locator, String attri)
        	{
        	  
          	  String Elementby=driver.findElement(By.xpath(locator)).getAttribute(attri);
			return Elementby;
   	
        	}
          
          //Getting Text
          public static String GetText(String locator)
        	{
          	  String Elementby=driver.findElement(By.xpath(locator)).getText();
  			return Elementby;
   	
        	}
          //For unique Name
          
          //Getting Text
		 
		public static String UniqueName(String Name) throws Exception {
  			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
  			 
  		    String Name1= Name+dateName;
  	       
  			return Name1;
  			}
           //
          public static void KeyPressEnter(String locator)
        	{
          	  WebElement Elementby=driver.findElement(By.xpath(locator));
          	  Elementby.sendKeys(Keys.ENTER);
        	}
          
       // to close Authentication Window
          
          public static void CloseChildWindow()
          {
        	  String MainWindow=driver.getWindowHandle();		
       		
		        // To handle all new opened window.				
		            Set<String> s1=driver.getWindowHandles();		
		        Iterator<String> i1=s1.iterator();		
		        		
		        while(i1.hasNext())			
		        {		
		            String ChildWindow=i1.next();		
		            		
		            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
		            {    		
		                 
		                    // Switching to Child window
		                    driver.switchTo().window(ChildWindow);	                                                                                                           
		                    
		                    driver.close();		
		            }		
		        }		
		        // Switching to Parent window i.e Main Window.
		            driver.switchTo().window(MainWindow);				
		    
          }
          public static void GlobalSearch(String SearchValue, String ObjectLnk)throws Exception{
      		final Properties prop = new Properties();
      		try{
      			InputStream input = new FileInputStream(Constant.Path_PropertyFile);
      			prop.load(input);
      		 
      			String txtSearch=prop.getProperty("txtSearch");	 
      			fluent_wait(txtSearch,1000,1);
      			inputValue(txtSearch,SearchValue);
      			Reports.PassTest("SearchValue");
      			String btnSearch=prop.getProperty("btnSearch");
      			fluent_wait(btnSearch,100,1);
      			elementClick(btnSearch);
      			Reports.PassTest("Clicked on Search");
      			String lnkContact;
      			if(ObjectLnk.equalsIgnoreCase("lnkPeople"))
      				lnkContact=prop.getProperty(ObjectLnk);
      			else
      				lnkContact = ObjectLnk;
      			Reports.PassTest("Click on Search Result link");
      			fluent_wait(lnkContact,100,1);
      			elementClick(lnkContact);
      			Reports.PassTest("Clicked on contact");
      			Reports.PassTestWithScreenShot("Clicked on contact", "Contact");
      		}catch (Exception e){
      			cLog.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
      			throw (e);
      					}
      	}
          
          public static void SwitchToChildWindow()
          {
        	 String winHandleBefore = driver.getWindowHandle();
      		System.out.println(winHandleBefore);
      		int count=0;
      		for(String winHandle : driver.getWindowHandles())
      		{
      			System.out.println(winHandle);
      			count++;
      			if(count==2)
      			{
      			driver.switchTo().window(winHandle);
      			}
      		}
        	  /*String MainWindow=driver.getWindowHandle();		
       		
		        // To handle all new opened window.				
		            Set<String> s1=driver.getWindowHandles();		
		        Iterator<String> i1=s1.iterator();		
		        System.out.println(MainWindow);		
		        while(i1.hasNext())			
		        {		
		            String ChildWindow=i1.next();		
		            System.out.println(ChildWindow);		
		            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
		            {    		
		                 
		                    // Switching to Child window
		                    driver.switchTo().window(ChildWindow);	
		                    
		            }		
		        }*/			
          }
          
          public static void SwitchToFrame(String locator) {
        	  
        	  WebElement iframeElement = driver.findElement(By.id(locator));

     		 driver.switchTo().frame(iframeElement);
        	  
        	  
          }
          public static void SwitchToFrameXpath(String locator) {
        	  
        	  WebElement iframeElement = driver.findElement(By.xpath(locator));
        	  driver.switchTo().frame(iframeElement);
          }
          
          public static java.util.List<WebElement> ListOfElements(String locator)
        	{
          	  java.util.List<WebElement> Elementby=driver.findElements(By.xpath(locator));
          	  return Elementby;
          	  
        	}
          
        //for Clicking Move to Image 
          public static void MoveToelementClick(String locator)
      	{
        	  WebElement Elementby=driver.findElement(By.xpath(locator));
        	 Actions actions =new Actions(driver);
        	  actions.moveToElement(Elementby).click().perform();
      	
      	}
          
          public static void Search(String SearchBox, String SearchButton, String Product) throws Throwable
	       {
	            
	              //String SearchBox=prop.getProperty("txtLeftproductSearchBox");
	              elementClick(SearchBox);
	              elementClear(SearchBox);
	              inputValue(SearchBox, Product);
	              //String LeftSearchButton=prop.getProperty("btnLeftSearch");
	              elementClick(SearchButton);
	              //Reports.PassTestWithScreenShot("Searched product by Part Number on leftmost search box", "Pass");
	       }
          public static void SwitchToDefaultContent() {
        	  driver.switchTo().defaultContent();
      	
          }
          
          public static void ScrollDownPage() {

        	  JavascriptExecutor js = (JavascriptExecutor) driver;
        	  js.executeScript("window.scrollBy(0,1000)");
          }
public static String currentUrl() {
        	  
        	  String expectedUrl = driver.getCurrentUrl();
        	  return expectedUrl;
          }
          
		public static void navigateBack() {
			        	  
			driver.navigate().back();
			
	 }
		
		
		
		public static void popUpAccept() {
      	 
			driver.switchTo().alert().accept();
	 }
		
		public static void scrollDown() {
	      	 
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,550)", "");
			
	 }
		
		public static void scrollUp() {
	      	 
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			//jse.executeScript("window.scrollBy(0,-450)", "");
			jse.executeScript("window.scrollBy(2000,0)", "");
			
	 }
		
		public static String GetSelectedText(String Locator) throws Throwable
	       {
	            
			 Select sel = new Select(driver.findElement(By.xpath(Locator)));
			  String strCurrentValue = sel.getFirstSelectedOption().getText();
			  //Print the Currently selected value
			  return strCurrentValue;
	       }
		
		public static String GetTodaysDate() throws Throwable
	       {
	            
			 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		       Calendar cal = Calendar.getInstance();
		       cal.add(Calendar.DATE, 0);
		       String Today=dateFormat.format(cal.getTime());
		       
		     return Today;
	       }
		
		public static String GetYesterdaysDate() throws Throwable
	       {
	            
			 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		       Calendar cal = Calendar.getInstance();
		       cal.add(Calendar.DATE, -1);
		       String Yesterday=dateFormat.format(cal.getTime());
		       
		     return Yesterday;
	       }
		
		public static String GetFutureDate() throws Throwable
	       {
	            
			 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		       Calendar cal = Calendar.getInstance();
		       cal.add(Calendar.DATE, +2);
		       String Future=dateFormat.format(cal.getTime());
		       
		     return Future;
	       }
		
		public static int getRandomNumber(int min, int max) {

			if (min >= max) {
				throw new IllegalArgumentException("max must be greater than min");
			}

			Random r = new Random();
			return r.nextInt((max - min) + 1) + min;
		}
}
