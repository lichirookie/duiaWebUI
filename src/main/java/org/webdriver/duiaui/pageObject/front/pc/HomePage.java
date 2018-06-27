package org.webdriver.duiaui.pageObject.front.pc;
import java.io.IOException;

import org.webdriver.duiaui.util.BaseAction;
import org.webdriver.duiaui.util.Locator;

public class HomePage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml";
 public   HomePage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
getLocatorMap();
}
/***
* 预约挂号
* @return
* @throws IOException
*/
public Locator appiont_memue() throws IOException
 {
   Locator locator=getLocator("appiont_memue");
   return locator;
 }

/***
* 账号信息管理
* @return
* @throws IOException
*/
public Locator account_management() throws IOException
 {
   Locator locator=getLocator("account_management");
   return locator;
 }

/***
* 预约明细
* @return
* @throws IOException
*/
public Locator AppointmentDetail() throws IOException
 {
   Locator locator=getLocator("AppointmentDetail");
   return locator;
 }

/***
* 咨询统计
* @return
* @throws IOException
*/
public Locator queryConsultation() throws IOException
 {
   Locator locator=getLocator("queryConsultation");
   return locator;
 }

/***
* 客服工作统计
* @return
* @throws IOException
*/
public Locator queryCustomerWork() throws IOException
 {
   Locator locator=getLocator("queryCustomerWork");
   return locator;
 }

/***
* 短信查询
* @return
* @throws IOException
*/
public Locator queryMessagePage() throws IOException
 {
   Locator locator=getLocator("queryMessagePage");
   return locator;
 }

/***
* 退出登录
* @return
* @throws IOException
*/
public Locator layout() throws IOException
 {
   Locator locator=getLocator("layout");
   return locator;
 }
}