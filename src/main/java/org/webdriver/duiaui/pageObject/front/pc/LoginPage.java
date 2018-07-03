package org.webdriver.duiaui.pageObject.front.pc;
import java.io.IOException;
import java.io.InputStream;
import org.webdriver.duiaui.util.BaseAction;
import org.webdriver.duiaui.util.Locator;
import org.webdriver.duiaui.pageObjectConfig.PageObjectAutoCode;//PC端登录页面_对象库类
public class LoginPage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml";
 public   LoginPage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
getLocatorMap();
}
/***
* 切换账号登录
* @return
* @throws IOException
*/
public Locator 切换到账号登录() throws IOException
 {
   Locator locator=getLocator("切换到账号登录");
   return locator;
 }

/***
* 用户名输入框
* @return
* @throws IOException
*/
public Locator 用户名输入框() throws IOException
 {
   Locator locator=getLocator("用户名输入框");
   return locator;
 }

/***
* 密码输入框
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

/***
* 扫码登录时下方注册
* @return
* @throws IOException
*/
public Locator 扫码注册按钮() throws IOException
 {
   Locator locator=getLocator("扫码注册按钮");
   return locator;
 }

/***
* 扫码登录时下方注册
* @return
* @throws IOException
*/
public Locator 账号登录注册按钮() throws IOException
 {
   Locator locator=getLocator("账号登录注册按钮");
   return locator;
 }

/***
* 忘记密码
* @return
* @throws IOException
*/
public Locator 忘记密码() throws IOException
 {
   Locator locator=getLocator("忘记密码");
   return locator;
 }
}