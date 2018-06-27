package org.webdriver.duiaui.pageObject.front.m;

import org.webdriver.duiaui.util.BaseAction;
import org.webdriver.duiaui.util.Locator;

import java.io.IOException;

/**
 *
 */
public class LoginPage extends BaseAction {
    /**
     * 用于eclipse工程内运行查找对象库文件路径
     */
    private String path = "src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml";

    public LoginPage() {
        //工程内读取对象库文件
        setXmlObjectPath(path);
        /**
         * 初始化时调用父类locatorMap方法，locatorMap方法会将得到的Map赋给自己的Map
         * */
        getLocatorMap();
    }

/*
    */
/***
     * 账户登录
     * @return
     * @throws IOException
     *//*

    public Locator 账户登录() throws IOException {

        Locator locator = getLocator("账户登录");
        return locator;
    }
*/

    /***
     * 用户名
     * @return
     * @throws IOException
     */
    public Locator 用户名输入框() throws IOException {
        /**
         * 调用父类的getLocator方法，此方法封装了LocatorMap,并通过locatorName作为key来读取其中的对象
         * */
        Locator locator = getLocator("用户名输入框");
        return locator;
    }

    /***
     * 密码
     * @return
     * @throws IOException
     */
    public Locator 密码输入框() throws IOException {
        Locator locator = getLocator("密码输入框");
        return locator;
    }

    /***
     * 登录button
     * @return
     * @throws IOException
     */
    public Locator 登录按钮() throws IOException {
        Locator locator = getLocator("登录button");
        return locator;
    }





}