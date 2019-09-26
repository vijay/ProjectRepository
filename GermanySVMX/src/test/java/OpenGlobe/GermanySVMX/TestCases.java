package OpenGlobe.GermanySVMX;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utilities.Constant;
import Utilities.ExcelUtils;
import Utilities.ExtentReportsFunctions;
import Utilities.cLog;
import Utilities.commanFunctions;

public class TestCases extends commanFunctions {
	Properties prop = new Properties();
	InputStream input = null;
	ExtentReportsFunctions Reports = new ExtentReportsFunctions();
	public WebDriver driver;
	private String sTestCaseName;
	private int iTestCaseRow;
	File index = new File("./test-output/Screenshots/");

	@BeforeClass
	public void beforeClass() {
		Reports.startReport();
	}

	@BeforeMethod
	public void beforeMethod(Method result) throws Exception {
		DOMConfigurator.configure("log4j.xml");

		Reports.startReport();
		sTestCaseName = this.toString();
		String sTest1 = result.getName();
		Reports.StartTest(sTest1);
		// System.out.println(sTestCaseName);
		sTestCaseName = commanFunctions.getTestCaseName(this.toString());
		cLog.startTestCase(sTestCaseName);
		System.out.println(sTestCaseName);
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		iTestCaseRow = ExcelUtils.getRowContains(sTest1, Constant.Col_TestCaseName);
		System.out.println(iTestCaseRow);
		driver = commanFunctions.OpenBrowser(iTestCaseRow);
		new BaseClass(driver);
	}

	@Test
	public void GERSMCont0101() throws Throwable {
		TCA_Login.AdminLogin(iTestCaseRow);
		TCA_Login.ProfileLogin(iTestCaseRow);

		InputStream input = new FileInputStream(Constant.Path_PropertyFile);
		prop.load(input);
		Thread.sleep(5000);
		String AccountName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_AccountName);

		String txtSearch = prop.getProperty("txtSearch");
		fluent_wait(txtSearch, 100, 1);
		inputValue(txtSearch, AccountName);
		
		

		String btnSearch = prop.getProperty("btnSearch");
		fluent_wait(btnSearch, 100, 1);
		elementClick(btnSearch);
		Thread.sleep(5000);
		elementClick("//div[@id='Account_body']//a[text()='" + AccountName + "']");
		Thread.sleep(8000);
		Thread.sleep(5000);
		// String iFrameContact=prop.getProperty("iFrameContact");
		SwitchToFrame("066w0000001guUA");
		Thread.sleep(1000);
		// TCA_ContactOpertaions.Contact_Create(iTestCaseRow);
		Thread.sleep(5000);
		// String iFrameContact=prop.getProperty("iFrameContact");
		// SwitchToFrame("066w0000001guUJ");
		// Thread.sleep(1000);
		// TCA_ContactOpertaions.Contact_Create(iTestCaseRow);

	}

	@Test
	public void PSSFRCFNAC01() throws Throwable {
		TCA_Login.AdminLogin(iTestCaseRow);
		TCA_Login.ProfileLogin(iTestCaseRow);

		Thread.sleep(10000);
		InputStream input = new FileInputStream(Constant.Path_PropertyFile);
		prop.load(input);
		String lnkAccount = prop.getProperty("lnkAccount");
		fluent_wait(lnkAccount, 100, 1);
		MoveToelementClick(lnkAccount);
		Thread.sleep(15000);
		if (isElementPresent(lnkAccount)) {
			Reports.PassTestWithScreenShot("Clicked Account link", "PASS");
			
		} else {
			Reports.FailTestwithScreenShot("Doesn't Clicked Account link", "Fail");
			// System.out.print("Test Failed5");
		}
		//Reports.EndTest();
		String btnNew = prop.getProperty("btnNew");
		fluent_wait(btnNew, 100, 1);
		elementClick(btnNew);
		Thread.sleep(15000);
		String btnNext = prop.getProperty("btnNext");
		fluent_wait(btnNext, 100, 1);
		elementClick(btnNext);

		Thread.sleep(10000);

		String eleForm = prop.getProperty("eleForm");
		fluent_wait(eleForm, 100, 1);
		SwitchToFrameXpath(eleForm);
		Thread.sleep(10000);

		String searchAccountName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_AccountName);
		String txtSearchCriteria = prop.getProperty("txtSearchCriteria");
		fluent_wait(txtSearchCriteria, 100, 1);
		inputValueThenEnter(txtSearchCriteria, searchAccountName);

		Thread.sleep(10000);
		String btnNewAccountButton = prop.getProperty("btnNewAccountButton");
		fluent_wait(btnNewAccountButton, 100, 1);
		elementClick(btnNewAccountButton);

		String eleForm2 = prop.getProperty("eleForm");
		fluent_wait(eleForm2, 100, 1);
		SwitchToFrameXpath(eleForm2);
		Thread.sleep(10000);

		String drpStatus = prop.getProperty("drpStatus");
		fluent_wait(drpStatus, 100, 1);
		dropdownSelect(drpStatus, "Active");
		
		//account type
		/*String drpAccountType = prop.getProperty("drpAccountType");
		fluent_wait(drpAccountType, 100, 1);
		
		
		if (isElementPresent(drpAccountType)) {
			dropdownSelect(drpAccountType, "Competitor");
			Reports.PassTestWithScreenShot("selected account type ", "PASS");
			
		} else {
			Reports.FailTestwithScreenShot("Doesn't account type ", "Fail");
		}*/

		String drpChannel = prop.getProperty("drpChannel");
		fluent_wait(drpChannel, 100, 1);
		dropdownSelect(drpChannel, "OE");

		String drpSalesRegion = prop.getProperty("drpSalesRegion");
		fluent_wait(drpSalesRegion, 100, 1);

		if (isElementPresent(drpSalesRegion)) {
			dropdownSelect(drpSalesRegion, "EMEA");
			Reports.PassTestWithScreenShot("selected options ", "PASS");
			
		} else {
			Reports.FailTestwithScreenShot("Doesn't selected options ", "Fail");
		}
		String drpSalesSubRegion = prop.getProperty("drpSalesSubRegion");
		fluent_wait(drpSalesSubRegion, 100, 1);
		dropdownSelectByindex(drpSalesSubRegion, 2);

		String btnSave = prop.getProperty("btnSave");
		fluent_wait(btnSave, 100, 1);
		
		
		if (isElementPresent(btnSave)) {
			MoveToelementClick(btnSave);
			Reports.PassTestWithScreenShot("Clicked Save button", "PASS");
			
		} else {
			Reports.FailTestwithScreenShot("Doesn't Clicked Save button", "Fail");
		}

		Thread.sleep(5000);
		SwitchToDefaultContent();

		String btnNewOpp = prop.getProperty("btnNewOpp");
		fluent_wait(btnNewOpp, 100, 1);
		elementClick(btnNewOpp);

		String btnNextO = prop.getProperty("btnNext");
		fluent_wait(btnNextO, 100, 1);
		elementClick(btnNextO);
		Thread.sleep(5000);

		String txtOpportunityName = prop.getProperty("txtOppName");
		fluent_wait(txtOpportunityName, 100, 1);
		
		
		if (isElementPresent(txtOpportunityName)) {
			inputValue(txtOpportunityName, "testA001");
			Reports.PassTestWithScreenShot("Entered opportunity", "PASS");
			
		} else {
			Reports.FailTestwithScreenShot("Doesn't entered opportunity", "Fail");
		}

		String txtCloseDate = prop.getProperty("txtCloseDate");
		fluent_wait(txtCloseDate, 100, 1);
		elementClick(txtCloseDate);

		String date = prop.getProperty("date");
		fluent_wait(date, 100, 1);
		elementClick(date);

		String drpStage = prop.getProperty("drpStage");
		fluent_wait(drpStage, 100, 1);
		elementClick(drpStage);

		String stageEle = prop.getProperty("eleStage");
		fluent_wait(stageEle, 100, 1);
		elementClick(stageEle);

		String btnSaveO = prop.getProperty("btnSaveO");
		fluent_wait(btnSaveO, 100, 1);
		

		Thread.sleep(15000);

		if (isElementPresent(btnSaveO)) {
			elementClick(btnSaveO);
			Reports.PassTestWithScreenShot("Save button clicked ", "PASS");
			
		} else {
			Reports.FailTestwithScreenShot("Save button not clicked ", "Fail");
		}

	}

	@AfterMethod
	public void afterMethod() {
		// driver.close();
		Reports.EndTest();
		driver.quit();
		// System.out.println("End");
	}

	@AfterClass
	public void afterClass() throws InterruptedException {

		Thread.sleep(1000);
		Reports.QuitTest();
	}

}
