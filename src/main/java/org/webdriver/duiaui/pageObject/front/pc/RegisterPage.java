package org.webdriver.duiaui.pageObject.front.pc;

import org.webdriver.duiaui.util.BaseAction;
import org.webdriver.duiaui.util.Locator;

import java.io.IOException;

public class RegisterPage extends BaseAction {
    private String path="src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml";
    public   RegisterPage() {
//工程内读取对象库文件
        setXmlObjectPath(path);
        getLocatorMap();
    }

    /**
     * 免费注册button
     * @return
     * @throws IOException
     */
    public Locator 手机号输入框()throws IOException{
        Locator locator = getLocator("手机号输入框");
        return locator;
    }


    /**
     * 账号登录下的注册
     * @return
     * @throws IOException
     */
    public Locator 账号登录注册按钮()throws IOException{
        Locator locator = getLocator("账号登陆下注册");
        return locator;
    }

    public Locator 忘记密码() throws  IOException{
        Locator locator = getLocator("账号登陆下注册");
        return locator;
    }

}
