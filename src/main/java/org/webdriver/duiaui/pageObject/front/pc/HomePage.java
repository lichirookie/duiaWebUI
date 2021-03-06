package org.webdriver.duiaui.pageObject.front.pc;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.webdriver.duiaui.util.BaseAction;
import org.webdriver.duiaui.util.Locator;
import org.webdriver.duiaui.pageObjectConfig.PageObjectAutoCode;//PC端首页_对象库类
public class HomePage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml";
 public   HomePage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
getLocatorMap();
}
/***
* list
* @return
* @throws IOException
*/
public Locator SKU列表() throws IOException
 {
   Locator locator=getLocator("SKU列表");
   return locator;
 }

/***
* list
* @return
* @throws IOException
*/
public Locator SKU集合() throws IOException
 {
   Locator locator=getLocator("SKU集合");
   return locator;
 }

/***
* list
* @return
* @throws IOException
*/
public Locator 登录注册按钮() throws IOException
 {
   Locator locator=getLocator("登录注册按钮");
   return locator;
 }


}