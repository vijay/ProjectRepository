package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportsFunctions extends commanFunctions {
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public void startReport() {
		
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
		
		//extent = new ExtentReports ("C://Users//vijay kumar//Desktop//SampleProjct//GermanySVMX//test-output//STMExtentReport.html", true);
		//extent = new ExtentReports ("D://Automation//eclipse-workspace//GermanySVMX//test-output//STMExtentReport.html", true);
		 extent
		                .addSystemInfo("Host Name", "Gloabl Web Automation")
		                .addSystemInfo("Environment", "Pre-Prod Automation");
		                //.addSystemInfo("User Name", "Sampada Deshmukh");
		                
		              extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		              //extent.loadConfig(new File("C://Users//vijay kumar//Desktop//SampleProjct//GermanySVMX//extent-config.xml"));
		 			//extent.loadConfig(new File("C:\\Users\\10621360\\Desktop\\CPSAT_Eamples\\extent-config.xml"));
		 }
		 
	public void StartTest(String ReportName){
		
		test = extent.startTest(ReportName);	
	}
	
	public void PassTest(String Result){
		
		test.log(LogStatus.PASS,Result);
	}
	
	public void PassTestWithScreenShot(String Result, String SnapName) throws Exception{
		
		test.log(LogStatus.PASS,Result);
	  	test.log(LogStatus.PASS, test.addScreenCapture(capture_screenShot(SnapName)));
	}
	public void FailTest(String Result){
		test.log(LogStatus.FAIL,Result);
		
	}
	public void FailTestwithScreenShot(String Result,String SnapName) throws Exception{
		test.log(LogStatus.FAIL,Result);
	  	test.log(LogStatus.FAIL, test.addScreenCapture(capture_screenShot(SnapName)));
		
	}
	public void SkipTestwithScreenhot() throws Exception{
		test.log(LogStatus.SKIP, test.addScreenCapture(capture_screenShot("Exception")));
	}
	
	public void EndTest(){
		extent.endTest(test);
		extent.flush();
	}
	
	public void QuitTest(){
		extent.flush();
			extent.close();
	}
	
	public void Take_TestOutput_Backup() throws IOException
	{

		String Foldername = new SimpleDateFormat("HH_mm_ss").format(new Date());
		File srcFolder = new File("./test-output");
		File destFolder = new File("D:\\OpenGlobeAuotmationReports\\"+"GERSVMXExecution_Archive_"+ Foldername);

		//make sure source exists
		if(!srcFolder.exists())
		{
			System.out.println("Directory does not exist at mentioned location.");
			//just exit
			System.exit(0);
		}
		else
		{
			try
			{
				File destFolder1 = new File(destFolder+ "\\test-output");
				System.out.println(destFolder1);
				if (!destFolder1.exists()) {
					destFolder1.mkdirs();
				}
			copy_Folder(srcFolder,destFolder1);
			}
			catch(IOException e){
			e.printStackTrace();
			//error, just exit
			System.exit(0);
			}
		}
	}
	
	public static void copy_Folder(File src, File dest) throws IOException{

		if(src.isDirectory()){
		//if directory not exists, create it
			if(!dest.exists()){
				dest.mkdir();
				System.out.println("Directory copied from "	+ src + " to " + dest);
			}
			//list all the directory contents
			String files[] = src.list();

			for (String file : files)
			{
				//construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				//recursive copy
				copy_Folder(srcFile,destFile);
			}
		}else
		{
			//if file, then copy it.
			//Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			//copy the file content in bytes
			while ((length = in.read(buffer)) > 0){
			out.write(buffer, 0, length);
		}

		in.close();
		out.close();
		System.out.println("File copied from " + src + " to " + dest);
		}
	}

}
