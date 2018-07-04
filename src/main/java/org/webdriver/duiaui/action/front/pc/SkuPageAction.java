package org.webdriver.duiaui.action.front.pc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.webdriver.duiaui.pageObject.front.pc.SkuPage;
import org.webdriver.duiaui.util.Assertion;
import org.webdriver.duiaui.util.ElementAction;
import org.webdriver.duiaui.util.FileManger;
import org.webdriver.duiaui.util.TestBaseCase;

import java.io.IOException;
import java.util.List;

public class SkuPageAction  {
    ElementAction action = new ElementAction();
    SkuPage skuPage = new SkuPage();

    public SkuPageAction(){
        String skuPageURL = "http://sku.duia.com/8";
        if(!action.getUrl().matches("(http://sku.duia.com/)+(\\d)+")){
            skuPage.open(skuPageURL);
        }
    }

    @Test
    public void clickFirstSku() throws IOException {
        List<WebElement> skuList = action.findElements(skuPage.SKU列表());
        skuList.get(0).click();
    }
}
