package duia.controller;

import org.testng.annotations.Test;
import org.webdriver.duiaui.action.front.pc.HomePageAction;
import org.webdriver.duiaui.action.front.pc.SkuPageAction;
import org.webdriver.duiaui.util.Assertion;
import org.webdriver.duiaui.util.ElementAction;
import org.webdriver.duiaui.util.TestBaseCase;

import java.io.IOException;

public class PageTester extends TestBaseCase{
    ElementAction action=new ElementAction();

    @Test(description="首页")
    public void selectSku() throws IOException {
        HomePageAction homePageAction = new HomePageAction();
        action.sleep(2);
        Assertion.VerityTitle("对啊网|重塑职业未来");
        homePageAction.selectSku();
        Assertion.VerityURL("http://sku.duia.com/8");
    }

    @Test(description="SKU页")
    public void clickSku() throws IOException {
        SkuPageAction skuPageAction = new SkuPageAction();
        action.sleep(2);
        skuPageAction.clickFirstSku();
        Assertion.VerityURL("http://item.duia.com/c/5260.html?appType=999");
    }
}
