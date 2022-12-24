package org.junit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;

public class Amazon {
	public static ChromeDriver driver;
	public static long startTime;
	public static  long endTime;
	String text1 = "";
	String text2 = "";
	
	 
    @BeforeClass
    public static void browserLaunch() {
    	WebDriverManager.chromedriver().setup();
    	driver = new ChromeDriver();
    	driver.get("https://www.Amazon.com/");
    	driver.manage().window().maximize();
    }
    @AfterClass
    public static void close() {
    	driver.close();
    }
    @Before
    public void startTime() {
    	long startTime = System.currentTimeMillis();
    }
    @After
    public void endTime() {
    long endTime = System.currentTimeMillis();
    System.out.println("Total Time Taken="+(endTime-startTime));
    
    }
    @Test
    public void test1() throws Throwable {
    	WebElement search = driver.findElement(By.xpath("//input[@id = 'twotabsearchtextbox']"));
    	search.sendKeys("Redmimobiles",Keys.ENTER);
    	Thread.sleep(3000);
    	List<WebElement> listofmobiles = driver.findElements(By.xpath("//*[@class ='a-size-medium a-color-base a-text-normal']"));
    	  System.out.println(listofmobiles.size());
    	  File f = new File("C:\\Users\\rkapi\\OneDrive\\Documents\\junit mobilelist.xlsx");
    	    Workbook workbook = new XSSFWorkbook();
    	    Sheet sheet = workbook.createSheet("sheet1");
    	     
    	for (int i = 0; i<listofmobiles.size(); i++) {
    		   WebElement mobilelist = listofmobiles.get(i);
    		   String text = mobilelist.getText();
    		   System.out.println(text); 
    		   
    		   Row row = sheet.createRow(i);
    		   Cell cell = row.createCell(0);
    		   cell.setCellValue(text);
    	}
    	Thread.sleep(1000);
    	FileOutputStream fileOutputStr = new FileOutputStream(f);
    	workbook.write(fileOutputStr);
    	fileOutputStr.close();
    }
    	@Test
    	public void test2() throws Throwable {
    		driver.findElement(By.xpath("(//span[@class ='a-size-medium a-color-base a-text-normal'])[2]")).click();
    		String parent = driver.getWindowHandle();
    		Set<String> child = driver.getWindowHandles();
    		for (String x : child) {
    			if(!parent.equals(x));
    			driver.switchTo().window(x);
    			WebElement mob2 = driver.findElement(By.xpath("//span[@id ='productTitle']"));
    			String text1 = mob2.getText();
    			System.out.println(text1);
    		}
    		FileWriter f2 = new FileWriter("C:\\Users\\rkapi\\OneDrive\\Documents\\junit.txt");
    		f2.write(text1);
    		f2.close();
    	}
    	   
        
         
         
    
    }
    

