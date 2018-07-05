package org.webdriver.duiaui.pageObject.front.pc;
import java.io.IOException;
import java.io.InputStream;
import org.webdriver.duiaui.util.BaseAction;
import org.webdriver.duiaui.util.Locator;
import org.webdriver.duiaui.pageObjectConfig.PageObjectAutoCode;//PC端付款页面_对象库类
public class PayPage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml";
 public   PayPage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
getLocatorMap();
}
/***
* 
* @return
* @throws IOException
*/
public Locator 微信支付按钮() throws IOException
 {
   Locator locator=getLocator("微信支付按钮");
   return locator;
 }

/***
* 
* @return
* @throws IOException
*/
public Locator 支付页弹窗的支付遇到问题按钮() throws IOException
 {
   Locator locator=getLocator("支付页弹窗的支付遇到问题按钮");
   return locator;
 }
}