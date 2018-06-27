package org.webdriver.duiaui.action.front.pc;

import org.webdriver.duiaui.pageObject.front.pc.LoginPage;
import org.webdriver.duiaui.util.Assertion;
import org.webdriver.duiaui.util.ElementAction;
import org.webdriver.duiaui.util.Locator;
import org.webdriver.duiaui.util.TestBaseCase;

import java.io.IOException;

/**
 * Created by lichiguan on 2016/8/29 0029.
 */
public class LoginAction extends TestBaseCase{
    LoginPage loginPage=new LoginPage();
    ElementAction action=new ElementAction();
    String pageText;
    public LoginAction(String Url) throws IOException
    {
        //此driver变量继承自TestBase变量
        loginPage.open(Url);
        Assertion.VerityTitle("对啊-登录");
        //预留与页面源码对比
        driver.getPageSource().trim();
        System.out.println(driver.getCurrentUrl());

    }

    public void login(String userName,String passWord) throws IOException {
        //action.click(loginPage.账户登录());
        action.sleep(1);
        action.clear(loginPage.密码输入框());
        action.type(loginPage.用户名输入框(),userName);
        action.clear(loginPage.密码输入框());
        action.type(loginPage.密码输入框(),passWord);
        action.click(loginPage.登录按钮());
    }

    public void checkText(Locator locator){
        String elementText = action.getText(locator);
    }

    public void switchTo(String str) throws IOException {
        action.click(loginPage.切换登录方式(str));
    }



}
