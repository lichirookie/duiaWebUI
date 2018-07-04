package duia.controller;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.webdriver.duiaui.action.front.pc.*;
import org.webdriver.duiaui.util.Assertion;
import org.webdriver.duiaui.util.ElementAction;
import org.webdriver.duiaui.util.RegexUtils;
import org.webdriver.duiaui.util.TestBaseCase;

import java.io.IOException;

public class MainController extends TestBaseCase {
    ElementAction action=new ElementAction();

    @Test(description = "下单成功流程")
    public void placeOrder() throws IOException {
        /*
        * 1、登录
        * 2、选择sku
        * 3、确认订单
        * 4、提交成功
        * 5、查看订单列表
        * 6、窗口切换
        *       如果打开了新窗口则将窗口数量+1
        * 7、订单比较
        *       产生订单后将订单num记录，与未支付订单num比较
        *
        * */
        String tempURL;
        String confirmOrderNum;
        RegexUtils regexUtils = new RegexUtils();

        //1、登录
        LoginAction loginAction = new LoginAction("http://sso.duia.com/uc");
        action.sleep(2);
        Assertion.VerityTitle("对啊-登录");
        loginAction.switchTo();
        loginAction.login("18510991012","1012glz");
        action.switchToWindow();
        Assertion.VerityURL("http://www.duia.com/#page1");
        Assertion.VerityTitle("对啊网|重塑职业未来");

        //2、选择SKU
        HomePageAction homePageAction = new HomePageAction();
        action.switchToWindow();
        homePageAction.selectSku();
        action.sleep(2);
        Assertion.VerityURL("http://sku.duia.com/8");
        Assertion.VerityTitle("会计初级职称在线系统班教育频道|对啊网");
        //Assertion.VerityTextPresent();

        //3、SKU
        SkuPageAction skuPageAction = new SkuPageAction();
        skuPageAction.clickFirstSku();
        action.sleep(2);
        action.switchToWindow();
        //action.switchToWindow();
        Assertion.VerityURL("http://item.duia.com/c/5260.html?appType=999");
        Assertion.VerityTitle("对啊网-会计初级职称-经验分享—从会计新人到合伙人");

        //4、SKU详情
        SkuDetailPageAction skuDetailPageAction = new SkuDetailPageAction();
        skuDetailPageAction.clickSignUp();
        action.sleep(2);
        action.switchToWindow();
        //Assertion.VerityURL("http://item.duia.com/order/confirmRoute?p=");
        Assertion.VerityTitle("确认订单");
        Assertion.VerityTextPresent("去结算");

        //5、提交订单
        ConfirmOrderPageAction confirmOrderPageAction = new ConfirmOrderPageAction();
        confirmOrderPageAction.confirm();
        action.sleep(2);
        action.switchToWindow();
        //Assertion.VerityURL("http://item.duia.com/order/payment?num="+订单号+"os=0");\d+
        Assertion.VerityTitle("支付订单");
        Assertion.VerityTextPresent("订单提交成功，去付款咯~");
        tempURL = action.getUrl();
        confirmOrderNum = regexUtils.getSubUtilSimple(tempURL,"((\\d){29})");


        //confirmOrderNum = confirmOrderTemp[0];

        //6、订单列表页
        OrderListPageAction orderListPageAction = new OrderListPageAction();
        action.switchToWindow();
        String orderColumn  = orderListPageAction.getToBeOrderNumList().get(0);
        String orderNum = regexUtils.getSubUtilSimple(orderColumn,"(\\d){29}");
        //确定订单是否有产生
       /* Assert.assertEquals(orderNum,confirmOrderNum);*/
        try{
            if(orderNum.equals(confirmOrderNum)){
                log.info("未付款订单在列表中可见");
            }else{
                log.info("订单不可见");
                Assert.fail();
            }
        }catch (Exception e){

        }

        //7、结算失败页面


       /* //8、付款页面
        PayPage confirmSuccessPageAction = new PayPage();
        OrderDetailPageAction orderDetailPageAction = new OrderDetailPageAction();*/


    }

}
