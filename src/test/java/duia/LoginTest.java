package duia;

import org.dom4j.DocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.webdriver.duiaui.action.front.pc.LoginAction;
import org.webdriver.duiaui.util.Assertion;
import org.webdriver.duiaui.util.ElementAction;
import org.webdriver.duiaui.util.ExcelReadUtil;
import org.webdriver.duiaui.util.TestBaseCase;

import java.io.IOException;

public class LoginTest extends TestBaseCase{
    ElementAction action=new ElementAction();
    @Test(description="登录成功测试",dataProvider = "loginSuccessData")
    public void loginSuccess(String caseName,String userName,String passWord,String url,String test) throws IOException
    {
        //调用登录方法，输入正确的用户名和密码
        LoginAction loginAction=new LoginAction("http://sso.duia.com/uc");
        action.sleep(2);
        loginAction.switchTo("切换到账号登录");
        loginAction.login(userName,passWord);
        //设置检查点
        Assertion.VerityTextPresentPrecision(test,"输入正确的用户名和密码，验证是否成功进入主页");
        //设置用例断言，判断用例是否失败
        Assertion.VerityError();
    }

    //数据驱动案例--start
    @DataProvider(name="loginSuccessData")
    public Object[][] loginSuccessData()
    {
        //读取登录用例测试数据
        String filePath="src/main/resources/data/loginData.xls";
        //读取第一个sheet，第2行到第5行-第2到第4列之间的数据
        return ExcelReadUtil.case_data_excel(1,filePath);
    }

    //数据驱动案例--start
    @DataProvider(name="longinFailData")
    public Object[][] loginData()
    {
        //读取登录用例测试数据
        String filePath="src/main/resources/data/loginData.xls";
        //读取第一个sheet，第2行到第5行-第2到第4列之间的数据
        //return ExcelReadUtil.case_data_excel(0, 1, 4, 1, 3,filePath);
        return ExcelReadUtil.case_data_excel(0,filePath);
    }
    @Test(description="登录失败用例",dataProvider = "longinFailData")
    public void loginFail (String caseName,String userName,String password,String message) throws IOException, DocumentException {
	//代替testng参数化的方法
    //调用登录方法
        LoginAction loginAction=new LoginAction("http://sso.duia.com/uc");
        action.sleep(1);
        loginAction.switchTo("切换到账号登录");
        loginAction.login(userName,password);
        //设置检查点
        Assertion.VerityTextPresent(message,"验证是否出现预期的错误提示信息:"+message);
        //设置断言
        Assertion.VerityError();
    }


}
