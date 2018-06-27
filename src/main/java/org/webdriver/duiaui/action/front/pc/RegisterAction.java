package org.webdriver.duiaui.action.front.pc;

import org.webdriver.duiaui.pageObject.front.pc.RegisterPage;
import org.webdriver.duiaui.util.Assertion;
import org.webdriver.duiaui.util.ElementAction;
import org.webdriver.duiaui.util.TestBaseCase;

import java.io.IOException;

public class RegisterAction extends TestBaseCase {
    RegisterPage registerPage = new RegisterPage();
    ElementAction action=new ElementAction();

    public void registerAction(){

    }
    public RegisterAction(String Url) throws IOException
    {
        //此driver变量继承自TestBase变量
        registerPage.open(Url);
        Assertion.VerityTitle("对啊-注册");
        //预留与页面源码对比
        driver.getPageSource().trim();
        System.out.println(driver.getCurrentUrl());

    }

    public void login(String userName,String passWord) throws IOException {
        action.sleep(1);
    }
}
