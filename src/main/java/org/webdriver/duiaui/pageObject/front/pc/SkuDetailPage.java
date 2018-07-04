package org.webdriver.duiaui.pageObject.front.pc;
import java.io.IOException;
import java.io.InputStream;
import org.webdriver.duiaui.util.BaseAction;
import org.webdriver.duiaui.util.Locator;
import org.webdriver.duiaui.pageObjectConfig.PageObjectAutoCode;//PC端SKU详情页面_对象库类
public class SkuDetailPage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml";
 public   SkuDetailPage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
getLocatorMap();
}
/***
* 
* @return
* @throws IOException
*/
public Locator 报名按钮() throws IOException
 {
   Locator locator=getLocator("报名按钮");
   return locator;
 }
}