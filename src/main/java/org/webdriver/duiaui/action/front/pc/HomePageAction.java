package org.webdriver.duiaui.action.front.pc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.webdriver.duiaui.pageObject.front.pc.HomePage;
import org.webdriver.duiaui.util.ElementAction;
import org.webdriver.duiaui.util.Locator;
import org.webdriver.duiaui.util.TestBaseCase;

import java.io.IOException;
import java.util.List;

public class HomePageAction extends TestBaseCase {
    ElementAction action = new ElementAction();
    HomePage homePage = new HomePage();
    WebDriver driver = new FirefoxDriver();


    public void selectSku() throws IOException {

        driver.get("http://www.duia.com/#page1");
        List<WebElement> skuList = action.findElements(homePage.SKU列表());
        action.hover(skuList.get(0));
        List<WebElement> skuElements = action.findElements(homePage.SKU集合());
        skuElements.get(0).click();
    }
}
