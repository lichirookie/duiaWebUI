package org.webdriver.duiaui.util;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class TestBaseCase {
	public static WebDriver driver;
	public static WebDriverWait wait;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	//方法描述
	public static String description;
	private String url;
	public Log log=new Log(this.getClass().getSuperclass());
	@BeforeTest
	@Parameters({"driver","nodeURL","BaseUrl"})
	public void  setup( String driver,String nodeURL,String baseURL) throws MalformedURLException {
		log.info("------------------开始执行测试---------------");
		if(nodeURL.equals("")||nodeURL.isEmpty())
		{
			log.info("读取testng.xml配置的"+driver+"浏览器并将其初始化\n");
			try {
				this.driver=setDriver(driver);
				setUrl(baseURL);
			} catch (Exception e) {
				log.error("没有成功浏览器环境配置错误");
				e.printStackTrace();
			}
			System.out.println(nodeURL);
			this.driver.manage().window().maximize();
		}
		else {
			log.info("读取xml配置：浏览器:"+driver+"；gridNodeURL:"+nodeURL);
			try {
				this.driver=setRemoteDriver(driver,nodeURL);
			} catch (Exception e) {
				// TODO: handle exception
				log.error("没有成功浏览器环境配置错误");
			}

			this.driver.manage().window().maximize();
		}
	}

	@AfterTest
	public void tearDown() {
		this.driver.close();
		this.driver.quit();
		log.info("-------------结束测试，并关闭退出浏览器-------------");
	}

	/**
	 * 用枚举类型列出浏览器列表，用于设置浏览器类型的函数参数
	 * @author zheng
	 *
	 */
	private WebDriver setDriver(String browsername)
	{

		switch (browsername)
		{

			case "FirefoxDriver" :
				/*System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
				FirefoxProfile firefoxProfile=new FirefoxProfile();*/
				this.driver=new FirefoxDriver();
				break;
			case "ChromeDriver":
				System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
				this.driver=new ChromeDriver();
				break;
			case "InternetExplorerDriver":
				System.setProperty("webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe");
				DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				dc.setCapability("ignoreProtectedModeSettings", true);
				this.driver=new InternetExplorerDriver(dc);
				break;
			case "HtmlUnitDriver":
				this.driver=new HtmlUnitDriver();
				break;
			default:
				this.driver=new FirefoxDriver();
				break;
		}
		driver.manage().deleteAllCookies();
		return driver;
	}



	private WebDriver setRemoteDriver(String browsername,String nodeURL) throws MalformedURLException
	{
		switch (browsername)
		{

			case "FirefoxDriver" :
				DesiredCapabilities capabilities=DesiredCapabilities.firefox();
				capabilities.setBrowserName("firefox");
				capabilities.setPlatform(Platform.WINDOWS);
				//driver= new RemoteWebDriver(new URL("http://192.168.0.205:4455/wd/hub"), capabilities);
				driver= new RemoteWebDriver(new URL(nodeURL), capabilities);
				break;
			case "ChromeDriver":
				// System.setProperty("webdriver.chrome.driver", "E:\\autotest\\autotmaiton\\resource\\chromedriver.exe");
				//driver=new ChromeDriver();
				DesiredCapabilities dcchorme=DesiredCapabilities.chrome();
				dcchorme.setBrowserName("chrome");
				dcchorme.setVersion("46.0.2490.86 m");
				dcchorme.setPlatform(Platform.WINDOWS);
				driver=new RemoteWebDriver(new URL(nodeURL), dcchorme);
				break;
			case "InternetExplorerDriver-8":
				DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				dc.setCapability("ignoreProtectedModeSettings", true);
				dc.setBrowserName("internet explorer");
				dc.setVersion("8.0.6001.18702");
				dc.setPlatform(Platform.XP);
				driver= new RemoteWebDriver(new URL(nodeURL), dc);
				break;
			case "InternetExplorerDriver-9":
				DesiredCapabilities dc2 = DesiredCapabilities.internetExplorer();
				dc2.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				dc2.setCapability("ignoreProtectedModeSettings", true);
				dc2.setBrowserName("internet explorer");
				dc2.setVersion("9.0.8112.16421");
				dc2.setPlatform(Platform.WINDOWS);
				driver= new RemoteWebDriver(new URL(nodeURL), dc2);
				//driver=new InternetExplorerDriver(dc2);
				break;
			case "HtmlUnitDriver":
				this.driver=new HtmlUnitDriver();
				break;
			default:
				this.driver=new FirefoxDriver();
				break;
		}
		return driver;
	}
	public static void main(String args[]) throws IOException, Exception {
		WebDriver driver2=new FirefoxDriver();
		driver2.get("http://www.baidu.com");
	}


}
