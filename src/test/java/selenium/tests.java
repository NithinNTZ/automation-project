package selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class tests {

    WebDriver driver;
    String str = "https://www.makemytrip.com/";

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void url() {
        driver.get(str);
    }

    @Test
    public void test1() {
    	try {
        
            pages ob = new pages(driver);
            ob.ttverif();
            ob.contverify();
            ob.logoverify();
            ob.lctr();
            ob.links();
            ob.verfy1(str);
            ob.dropdown();
            ob.scroll();
            ob.action();
            ob.screenshot();
            ob.response(str);
            ob.alert();
            ob.datadriven();
            ob.datepic();
            ob.mouseover();
    	}catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
