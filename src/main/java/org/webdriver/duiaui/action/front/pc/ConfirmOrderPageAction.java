package org.webdriver.duiaui.action.front.pc;

import org.webdriver.duiaui.pageObject.front.pc.ConfirmOrderPage;
import org.webdriver.duiaui.util.ElementAction;
import org.webdriver.duiaui.util.TestBaseCase;

import java.io.IOException;

public class ConfirmOrderPageAction extends TestBaseCase{
    ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage();
    ElementAction action = new ElementAction();

    public void confirm() throws IOException {
        action.click(confirmOrderPage.结算按钮());
    }
}
