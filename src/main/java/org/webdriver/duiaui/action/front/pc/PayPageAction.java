package org.webdriver.duiaui.action.front.pc;

import org.testng.annotations.Test;
import org.webdriver.duiaui.pageObject.front.pc.PayPage;
import org.webdriver.duiaui.util.ElementAction;
import org.webdriver.duiaui.util.TestBaseCase;

import java.io.IOException;

public class PayPageAction extends TestBaseCase {
    ElementAction action = new ElementAction();
    PayPage payPage = new PayPage();


    public void confirmFail() throws IOException, InterruptedException {
        action.click(payPage.微信支付按钮());
        action.switchToOldWindow();
        action.sleep(2);
        action.click(payPage.支付页弹窗的支付遇到问题按钮());

    }
}
