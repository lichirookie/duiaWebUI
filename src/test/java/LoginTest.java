import java.io.IOException;

import org.dom4j.DocumentException;
import org.testng.annotations.DataProvider;

import org.webdriver.duiaui.action.front.pc.LoginAction;
import org.webdriver.duiaui.util.*;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends TestBaseCase {
	ElementAction action=new ElementAction();
	@Test(description="登录成功测试")
	@Parameters({"BaseUrl"})//读取testng.xml参数
	public void login(String BaseUrl) throws IOException
	{
		//调用登录方法，输入正确的用户名和密码
		LoginAction loginAction=new LoginAction("http://192.168.3.247:8809/casservice/login?"+BaseUrl);
		action.sleep(2);
		loginAction.login("","");
		//设置检查点
		Assertion.VerityTextPresentPrecision("请登录","输入正确的用户名和密码，验证是否成功进入主页");
		//设置用例断言，判断用例是否失败
		Assertion.VerityError();
	}

	//数据驱动案例--start
	@DataProvider(name="longinData")
	public Object[][] loginData()
	{
		//读取登录用例测试数据
		String filePath="src/main/resources/data/loginData.xls";
		//读取第一个sheet，第2行到第5行-第2到第4列之间的数据
		return ExcelReadUtil.case_data_excel(0, 1, 4, 1, 3,filePath);
	}

	@Test(description="登录失败用例",dataProvider = "longinData")
	public void loginFail (String userName,String password,String message) throws IOException, DocumentException {
	/*	//代替testng参数化的方法
		String BaseUrl= XmlReadUtil.getTestngParametersValue("testng.xml","DuiABaseUrl");*/
		//调用登录方法
		LoginAction loginAction=new LoginAction("http://sso.so.duia.com/uc");
		action.sleep(1);
		loginAction.switchTo("切换到账号登录");
		loginAction.login(userName,password);
		//设置检查点
		Assertion.VerityTextPresent(message,"验证是否出现预期的错误提示信息:"+message);
		//设置断言
		Assertion.VerityError();
	}
	//数据驱动案例--end

}