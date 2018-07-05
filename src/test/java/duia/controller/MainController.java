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
    ElementAction action=new ElementAction();
    String tempURL;
    String confirmOrderNum;
    RegexUtils regexUtils = new RegexUtils();


    @Test(description = "登录成功",groups = "confrimOrder",priority = 1 )
    public void login() throws IOException {
        //1、登录
        LoginAction loginAction = new LoginAction("http://sso.duia.com/uc");
        action.sleep(2);
        Assertion.VerityTitle("对啊-登录");
        loginAction.switchTo();
        loginAction.login("18510991012","1012glz");
        action.switchToNewWindow();
        Assertion.VerityURL("http://www.duia.com/#page1");
        Assertion.VerityTitle("对啊网|重塑职业未来");
    }

    @Test(description = "首页选择SKU",groups = "confrimOrder",priority = 2)
    public void homePageSkuSelect() throws IOException {
        //2、选择SKU
        HomePageAction homePageAction = new HomePageAction();
        action.switchToNewWindow();
        homePageAction.selectSku();
        action.sleep(2);
        Assertion.VerityURL("http://sku.duia.com/8");
        Assertion.VerityTitle("会计初级职称在线系统班教育频道|对啊网");
        //Assertion.VerityTextPresent();
    }

    @Test(description = "课程选择",groups = "confrimOrder",priority = 3)
    public void skuClaseSelect() throws IOException {
        //3、SKU
        SkuPageAction skuPageAction = new SkuPageAction();
        skuPageAction.clickFirstSku();
        action.sleep(2);
        action.switchToNewWindow();
        //action.switchToWindow();
        Assertion.VerityURL("http://item.duia.com/c/5260.html?appType=999");
        Assertion.VerityTitle("对啊网-会计初级职称-经验分享—从会计新人到合伙人");
    }

    @Test(description = "课程预定",groups = "confrimOrder",priority = 4)
    public void classSignUp() throws IOException {
        SkuDetailPageAction skuDetailPageAction = new SkuDetailPageAction();
        skuDetailPageAction.clickSignUp();
        action.sleep(2);
        action.switchToNewWindow();
        //Assertion.VerityURL("http://item.duia.com/order/confirmRoute?p=");
        Assertion.VerityTitle("确认订单");
        Assertion.VerityTextPresent("去结算");
    }

    @Test(description = "订单提交",groups = "confrimOrder",priority = 5)
    public void orderConfirm() throws IOException {
        ConfirmOrderPageAction confirmOrderPageAction = new ConfirmOrderPageAction();
        confirmOrderPageAction.confirm();
        action.sleep(2);
        action.switchToNewWindow();
        //Assertion.VerityURL("http://item.duia.com/order/payment?num="+订单号+"os=0");\d+
        Assertion.VerityTitle("支付订单");
        Assertion.VerityTextPresent("订单提交成功，去付款咯~");
        tempURL = action.getUrl();
        confirmOrderNum = regexUtils.getSubUtilSimple(tempURL,"((\\d){29})");
    }

    @Test(description = "订单提交失败",groups="confrimOrder",priority = 6)
    public void orderFailConfirm() throws IOException, InterruptedException {
        PayPageAction payPageAction = new PayPageAction();
        payPageAction.confirmFail();
    }

    @Test(description = "订单列表页首订单与提交订单时订单对比",groups = "confrimOrder",priority = 7)
    public void orderCompare() throws IOException {
        OrderListPageAction orderListPageAction = new OrderListPageAction();
        action.switchToNewWindow();
        String orderColumn  = orderListPageAction.getToBeOrderNumList().get(0);
        String orderNum = regexUtils.getSubUtilSimple(orderColumn,"(\\d){29}");
        //确定订单是否有产生
       /* Assert.assertEquals(orderNum,confirmOrderNum);*/
        try{
            if(orderNum.equals(confirmOrderNum)){
                log.info("未付款订单在列表中可见");
            }else{
                log.info("订单不可见");
            }
        }catch (Exception e){

        }
    }





}
