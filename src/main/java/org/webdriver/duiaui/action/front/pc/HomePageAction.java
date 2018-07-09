package org.webdriver.duiaui.action.front.pc;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class HomePageAction extends TestBaseCase{
    ElementAction action = new ElementAction();
    HomePage homePage = new HomePage();


    //WebDriver driver = new FirefoxDriver();


    public HomePageAction(){
        homePage.open("http://www.duia.com/#page1");
    }

    public void clickLogin() throws IOException {
        List<WebElement> loginSign = action.findElements(homePage.登录注册按钮());
        WebElement loginButton = loginSign.get(0);
        loginButton.click();
    }

    public void clickSign(){

    }

    /*
    * 页面元素及动作：
    * 1、导航条
    * */

    public void selectSku() throws IOException {
/*        List<WebElement> skuList = action.findElements(homePage.SKU列表());
        WebElement spu =  skuList.get(0);
        //action.hover(spu);
        spu.click();
        //List<WebElement> skuElements = action.findElements(homePage.SKU集合());
        List<WebElement> skuElements = spu.findElements(By.cssSelector(homePage.SKU集合().getElement()));
        skuElements.get(0).click();*/
        JavascriptExecutor dr = (JavascriptExecutor)driver;
        dr.executeScript("document.querySelector('.sku-mr').click();");
    }
}
