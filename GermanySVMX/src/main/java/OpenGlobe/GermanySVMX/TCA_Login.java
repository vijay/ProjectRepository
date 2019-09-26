package OpenGlobe.GermanySVMX;

import Utilities.Constant;
import Utilities.ExcelUtils;
import Utilities.ExtentReportsFunctions;

import Utilities.commanFunctions;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TCA_Login extends commanFunctions {
	static ExtentReportsFunctions Reports = new ExtentReportsFunctions();
	static Properties prop = new Properties();

	public static void AdminLogin(int iTestCaseRow) throws Throwable {

		InputStream input = new FileInputStream(Constant.Path_PropertyFile);
		prop.load(input);

		String sUserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_UserName);
		String txtUserName = prop.getProperty("txtUserName");

		// fluent_wait(txtUserName,100,1);
		elementClear(txtUserName);

		inputValue(txtUserName, sUserName);
		// System.out.print("Test passed1");
		String sPassword = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Password);
		String txtPassword = prop.getProperty("txtPassword");
		elementClear(txtPassword);
		inputValue(txtPassword, sPassword);
		// System.out.print("Test passed2");
		String btnLogin = prop.getProperty("btnLogin");
		

		if (isElementPresent(btnLogin)) {
			Reports.PassTestWithScreenShot("Clicked login button successfully ", "PASS");
		} else {
			Reports.FailTestwithScreenShot("Not Clicked login button successfully ", "Fail");
		}
		elementClick(btnLogin);
		Thread.sleep(25000);
		
		//verify button
		String btnVerify = prop.getProperty("btnVerify");
		if (isElementPresent(btnVerify)) {
			elementClick(btnVerify);
			Reports.PassTestWithScreenShot("Clicked Verify button and Admin Login sucessfully", "PASS");
		} else {
			Reports.FailTestwithScreenShot("Doesn't clicked Verify button and Admin Login Failed", "Fail");
		}
		//String txtSearch = prop.getProperty("txtSearch");
		/*if (isElementPresent(txtSearch)) {
			Reports.PassTestWithScreenShot("Admin Login Sucessfully", "AdminPASS");
		} else {
			Reports.FailTestwithScreenShot("Admin Login Failed", "AdminFail");
		}*/


	}

	public static void ProfileLogin(int iTestCaseRow) throws Throwable {

		InputStream input = new FileInputStream(Constant.Path_PropertyFile);
		prop.load(input);

		String ProfileUser = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_ProfileUser);
		String ProfileName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_ProfileName);
		String txtSearch = prop.getProperty("txtSearch");
		fluent_wait(txtSearch, 100, 1);
		inputValueThenEnter(txtSearch, ProfileUser);

		Thread.sleep(15000);
		String lnkPeople = prop.getProperty("lnkPeople");
		fluent_wait(lnkPeople, 100, 1);
		MoveToelementClick(lnkPeople);
		Thread.sleep(15000);
		if (isElementPresent(lnkPeople)) {
			Reports.PassTestWithScreenShot("Clicked People tab", "PASS");
			System.out.print("Test passed4");
		} else {
			Reports.FailTestwithScreenShot("Doesn't Clicked People tab", "Fail");
			// System.out.print("Test Failed5");
		}

		Thread.sleep(15000);
		String eleUserActionMenu = prop.getProperty("eleUserActionMenu");

		fluent_wait(eleUserActionMenu, 100, 1);
		MoveToelementClick(eleUserActionMenu);
		Thread.sleep(15000);
		if (isElementPresent(eleUserActionMenu)) {
			Reports.PassTestWithScreenShot("Clicked UserAction Menu", "PASS");
			System.out.print("Test passed4");
		} else {
			Reports.FailTestwithScreenShot("Doesn't Clicked  UserAction Menu", "Fail");
			// System.out.print("Test Failed5");
		}
		Thread.sleep(15000);
		String lnkUserDetail = prop.getProperty("lnkUserDetail");
		fluent_wait(lnkUserDetail, 100, 1);
		MoveToelementClick(lnkUserDetail);
		Thread.sleep(15000);
		if (isElementPresent(lnkUserDetail)) {
			Reports.PassTestWithScreenShot("Clicked UserDetails", "PASS");
			System.out.print("Test passed4");
		} else {
			Reports.FailTestwithScreenShot("Doesn't Clicked UserDetails", "Fail");
			// System.out.print("Test Failed5");
		}
		String btnLogin = prop.getProperty("btnUserLogin");
		fluent_wait(btnLogin, 100, 1);
		MoveToelementClick(btnLogin);
		Thread.sleep(15000);
		if (isElementPresent(btnLogin)) {
			Reports.PassTestWithScreenShot("Profile Login Sucessfully", "ProfilePASS");
			System.out.print("Test passed4");
		} else {
			Reports.FailTestwithScreenShot("Profile Login Failed", "ProfileFail");
			// System.out.print("Test Failed5");
		}

		//Reports.EndTest();

	}

}
