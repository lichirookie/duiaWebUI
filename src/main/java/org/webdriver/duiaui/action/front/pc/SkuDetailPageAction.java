package org.webdriver.duiaui.action.front.pc;

import org.webdriver.duiaui.pageObject.front.pc.SkuDetailPage;
import org.webdriver.duiaui.util.ElementAction;
import org.webdriver.duiaui.util.TestBaseCase;

import java.io.IOException;

public class SkuDetailPageAction extends TestBaseCase {
    ElementAction action = new ElementAction();
    SkuDetailPage skuDetailPage = new SkuDetailPage();
    public void clickSignUp() throws IOException {
        action.click(skuDetailPage.报名按钮());
    }
}
