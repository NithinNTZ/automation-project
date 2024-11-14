package selenium;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.io.FileHandler;

	public class pages {
		WebDriver driver;
		public pages(WebDriver driver)
		{
			this.driver=driver;
		}
		public void ttverif()
		{
			System.out.println(driver.getTitle());
			String actualcontent=driver.getTitle();
			String expectedcontent="Make My Trip";
			if(actualcontent.contains(expectedcontent))
			{
				System.out.println("pass");
			}
			else
			{
				System.out.println("fail");
			}
		}
		public void contverify()
		{
			String cont=driver.getPageSource();
			if(cont.contains("English"))
			{
				System.out.println("pass");
			}
			else
			{
				System.out.println("fail");
			}
		}
		public void logoverify()
		{
			 WebElement logo = driver.findElement(By.id("//*[@id=\"SW\"]/div[1]/div[1]/a/picture/img"));
			 boolean lg = logo.isDisplayed();
			 if(lg)
			 {
				 System.out.println("is visible");
			 }
			 else
			 {
				 System.out.println("is not visible");
			 }
			 boolean sr = driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[1]/div[1]/ul/li[1]")).isSelected();
			 if(sr)
			 {
				 System.out.println(" is selected");
			 }
			 else
			 {
				 System.out.println("is not selected");
			 }
		}
		public void lctr()
		{
			driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div/div/nav/ul/li[2]/span/a/span[2]")).click();
			driver.navigate().back();
		}
		public void links()
		{
			List<WebElement> li = driver.findElements(By.tagName("a"));
			System.out.println(li.size());
			if (li.isEmpty()) {
			    System.out.println("No links found.");
			} else {
			    for (WebElement g : li) {
			        String link = g.getAttribute("href");
			        String text = g.getText();
			        System.out.println(link + "------" + text);
			        verfy1(link);
			    }
			}
		}
		public void verfy1(String link)
		{
			try
			{
				URL ob=new URL(link);
				HttpURLConnection con=(HttpURLConnection)ob.openConnection();
				con.connect();
				if(con.getResponseCode()==200)
				{
					System.out.println("valid lnk"+link);
				}
				else
				{
					System.out.println("invalid lnk"+link);
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
		public void dropdown()
		{
			WebElement dropdown =driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[1]/ul/li[5]/div/div[2]/div[2]/div[1]/span[2]"));
			dropdown.click();
			Select st=new Select(dropdown);
			st.selectByVisibleText("hin-language");
		}
		public void scroll()
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)", "");
			js.executeScript("window.scrollBy(0,1000)", "");
			driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/main/main/div[8]/div[2]/div/div/div/div[1]/div/div/a/div/img")).click();		
		}
		public void action()
		{
			WebElement from=driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[1]/div[2]/div[1]/div[1]/div[1]/div/div/div/input"));
			from.sendKeys("Bengaluru, India");
			WebElement to=driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[1]/div[2]/div[1]/div[2]/div[2]/div/div/div[1]/input"));
			Actions act=new Actions(driver);
			act.keyDown(from,Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL);
			act.keyDown(from,Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL);
			act.keyDown(to,Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL);
			act.perform();
		}
		public void screenshot() throws IOException
		{
			File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(screenshot,new File("C:\\Users\\NITHIN T K\\OneDrive\\Desktop\\Scren.png\""));
			WebElement hotel=driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div/div/nav/ul/li[2]/span/a/span[2]"));
			File tk=hotel.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(tk, new File("\"C:\\\\Users\\\\NITHIN T K\\\\OneDrive\\\\Desktop\\\\hotel_screenshot.png\""));
		}
		public void response(String str) throws Exception
		{
			    try {
			        URL oj = new URL(str);
			        HttpURLConnection con = (HttpURLConnection) oj.openConnection();
			        int code = con.getResponseCode();
			        System.out.println(code);
			        if (code == 200) {
			            System.out.println("valid");
			        } else {
			            System.out.println("invalid");
			        }
			    } catch (Exception e) {
			        System.out.println("Error: " + e.getMessage());
			    }
			}
		public void alert()
		{
			
			Alert a=driver.switchTo().alert();
			String actual=a.getText();
			System.out.println(actual);
			a.accept();
			if(actual.equals("good"))
			{
				System.out.println("pass");
			}
			else
			{
				System.out.println("fail");
			}
		}
		public void datadriven() throws IOException
		{
			File f=new File("\"C:\\Users\\NITHIN T K\\OneDrive\\Documents\\fileo.xlsx\"");
			FileInputStream fi=new FileInputStream(f);
			XSSFWorkbook wb=new XSSFWorkbook(fi);
			XSSFSheet sh=wb.getSheet("sheet 1");
			System.out.println(sh.getLastRowNum());
			for(int i=1;i<=sh.getLastRowNum();i++)
			{
				String username=sh.getRow(i).getCell(0).getStringCellValue();
				System.out.println("username="+username);
				driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div[2]/div/section/div[2]/div[1]/form/div[2]/div/input")).clear();
				driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div[2]/div/section/div[2]/div[1]/form/div[2]/div/input")).sendKeys(username);
				driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div[2]/div/section/div[2]/div[1]/form/div[2]/span")).click();									
			}
			
		}
		public void datepic()
		{
			driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[1]/div[2]/div[1]/div[3]/label/p[1]")).click();
			while(true)
			{
				WebElement month = driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[1]/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[1]"));
				String month1=month.getText();
				if(month1.equals("November 2024"))
				{
					System.out.println(month1);
					break;
				}
				else
				{
					driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[1]/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[1]")).click();
				}
			}
		List<WebElement> alldate = driver.findElements(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[1]/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[3]/div[3]/div"));
		for(WebElement dateelement:alldate)
		{
			String date=dateelement.getText();
			System.out.println("date");
			if(date.equals("15"))
			{
				System.out.println(date);
				dateelement.click();
				System.out.println("date selected");
				break; 
			}
		}

	}
		public void mouseover()
		{

			WebElement hto = driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div/div/nav/ul/li[2]/span/a"));
			Actions act=new Actions(driver);
			act.moveToElement(hto).perform();
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		    driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div[1]/div[2]/div/div[2]/div/div/div/ul/li[1]/a/p")).click();		
		}
}



