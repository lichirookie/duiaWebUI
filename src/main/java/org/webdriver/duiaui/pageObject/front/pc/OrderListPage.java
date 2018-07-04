package org.webdriver.duiaui.pageObject.front.pc;
import java.io.IOException;
import java.io.InputStream;
import org.webdriver.duiaui.util.BaseAction;
import org.webdriver.duiaui.util.Locator;
import org.webdriver.duiaui.pageObjectConfig.PageObjectAutoCode;//PC端订单列表页_对象库类
public class OrderListPage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml";
 public   OrderListPage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
getLocatorMap();
}
/***
* 
* @return
* @throws IOException
*/
public Locator 未支付订单列表() throws IOException
 {
   Locator locator=getLocator("未支付订单列表");
   return locator;
 }
}