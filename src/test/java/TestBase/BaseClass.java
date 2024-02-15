package TestBase;


import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;


public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeTest
	@Parameters({"os" , "browser"})
	public void setUp(String os , String br) throws IOException {
		
		// Loading properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		// Loading log4j file
		logger = LogManager.getLogger(this.getClass());
		
		// Launching browser based on choice
		if(br.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(br.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else {
			System.out.println("No matching browser");
			return;
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("PageURL"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

