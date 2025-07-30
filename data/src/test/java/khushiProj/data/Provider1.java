package khushiProj.data;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Provider1 {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
    	
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\KHUSHI SHAH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

	    driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://www.facebook.com/");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws Exception {
    	
        File src3 = new File("C:\\Users\\KHUSHI SHAH\\eclipse-workspace\\data\\testdata.properties");
		
		FileInputStream fis3 = new FileInputStream(src3);
		
		Properties pro3 = new Properties();
		
		pro3.load(fis3);
		
		Object arr[][] = new Object[3][2];
		
		arr[0][0] = pro3.getProperty("TestData1");
		arr[0][1] = pro3.getProperty("TestData2");
		
		arr[1][0] = pro3.getProperty("TestData3");
		arr[1][1] = pro3.getProperty("TestData4");
		
		arr[2][0] = pro3.getProperty("TestData5");
		arr[2][1] = pro3.getProperty("TestData6");
		
		return arr;
		
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) throws InterruptedException {
        WebElement email = driver.findElement(By.id("email"));
        WebElement pass = driver.findElement(By.id("pass"));

        email.sendKeys(username);
        pass.sendKeys(password);

        Thread.sleep(2000); // just to observe
        email.clear();
        pass.clear();
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("All windows are opened");
    }
}
