package org.webdriver.duiaui.action.front.pc;

import org.webdriver.duiaui.pageObject.front.pc.LoginPage;
import org.webdriver.duiaui.util.*;

import java.io.IOException;

/**
 * Created by lichiguan on 2016/8/29 0029.
 */
public class LoginAction extends TestBaseCase{
    LoginPage loginPage=new LoginPage();
    ElementAction action=new ElementAction();
    String pageText;
    String currentUrl;
    //来源：1为不是跳转来的，2为跳转进来的
    int Scene;

    public int getScene() {
        return Scene;
    }

    public void setScene(int scene) {
        Scene = scene;
    }

    public LoginAction(int scene, String url) throws IOException
    {
        switch (scene){
            case 1:
                getCurrentUrl();
            case 2:
                loginPage.open(url);
        }
       /* //此driver变量继承自TestBase变量
        if(Url!=null){
            loginPage.open(Url);
            //预留与页面源码对比
            driver.getPageSource().trim();
            System.out.println(driver.getCurrentUrl());
        }else{
            System.out.println("重定向的链接为："+action.getUrl());
        }*/
    }



    //直接打开链接登录与跳转过来登录返回的地址不同，所以需要记录来源
    public String getCurrentUrl(){
        currentUrl = action.getUrl();
        if(currentUrl!=null){
            //1、拿到url先要截取returnurl=后的字符串，之后将%2F替换为"/",%3A替换为":",%3F替换为"?",%3D替换为"="
            UrlRecombine recombine = new UrlRecombine();
            currentUrl = recombine.urlRecombine(currentUrl);

        }
        return currentUrl;
    }

    public void login(String userName,String passWord) throws IOException {
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

    public void switchTo() throws IOException {
        action.click(loginPage.切换到账号登录());
    }
}
