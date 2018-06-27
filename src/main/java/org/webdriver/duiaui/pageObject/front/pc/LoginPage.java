package org.webdriver.duiaui.pageObject.front.pc;
import java.io.IOException;

import org.webdriver.duiaui.util.BaseAction;
import org.webdriver.duiaui.util.Locator;

public class LoginPage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml";
 public   LoginPage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
    getLocatorMap();
}
/***
* 账户登录
* @return
* @throws IOException
*/
public Locator 账户登录() throws IOException
 {
   Locator locator=getLocator("账户登录");
   return locator;
 }

/***
* 用户名
* @return
* @throws IOException
*/
public Locator 用户名输入框() throws IOException
 {
   Locator locator=getLocator("用户名输入框");
   return locator;
 }

/***
* 密码
* @return
* @throws IOException
*/
public Locator 密码输入框() throws IOException
 {
   Locator locator=getLocator("密码输入框");
   return locator;
 }

/***
* 登录
* @return
* @throws IOException
*/
public Locator 登录按钮() throws IOException
 {
   Locator locator=getLocator("登录按钮");
   return locator;
 }

 public Locator 切换登录方式(String str) throws IOException{
    Locator locator = getLocator(str);
    return locator;
 }

}