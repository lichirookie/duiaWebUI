package org.webdriver.duiaui.action.front.pc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.webdriver.duiaui.pageObject.front.pc.OrderListPage;
import org.webdriver.duiaui.util.ElementAction;
import org.webdriver.duiaui.util.Locator;
import org.webdriver.duiaui.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.webdriver.duiaui.util.TestBaseCase.driver;


public class OrderListPageAction {
    /*
    * 1、已支付列表
    * 2、未支付列表
    * 3、已关闭列表
    * */
    OrderListPage orderListPage = new OrderListPage();
    ElementAction action = new ElementAction();
    private String paidUrl;
    private String toBeUrl;
    private String closedUrl;
    List<String> paidOrderNumList = new ArrayList<String>();
    List<String> toBeOrderNumList = new ArrayList<String>();
    List<String> closeOrderNumList = new ArrayList<String>();
    List<WebElement>  paidOrderList = new ArrayList<>();
    List<WebElement>  toBeOrderList = new ArrayList<>();
    List<WebElement>  closeOrderList = new ArrayList<>();



    public OrderListPageAction(){
        setPaidUrl("http://uc.duia.com/userOrder?nav=4#/orderList/1");
        setToBeUrl("http://uc.duia.com/userOrder?nav=4#/orderList/2");
        setClosedUrl("http://uc.duia.com/userOrder?nav=4#/orderList/3");
        if(!action.getUrl().matches("(http://uc.duia.com/userOrder\\?nav=4#/orderList/)+(\\d)+")){
            orderListPage.open(getPaidUrl());
        }
    }

    public void switchToToBePayList(){
        if(!action.getUrl().matches("(http://uc.duia.com/userOrder\\?nav=4#/orderList/)+(\\d)+")){

        }else{
            orderListPage.open(getToBeUrl());
        }
        setToBeOrderList(driver.findElements(By.cssSelector(".order-title")));
    }


    public List<WebElement> getPaidOrderList() {
        return paidOrderList;
    }

    public void setPaidOrderList(List<WebElement> paidOrderList) {
        this.paidOrderList = paidOrderList;
    }

    public List<WebElement> getToBeOrderList() {
        return toBeOrderList;
    }

    public void setToBeOrderList(List<WebElement> toBeOrderList) {
        this.toBeOrderList = toBeOrderList;
    }

    public List<WebElement> getCloseOrderList() {
        return closeOrderList;
    }


    public void setCloseOrderList(List<WebElement> closeOrderList) {
        this.closeOrderList = closeOrderList;
    }

    public List<String> getPaidOrderNumList() {

        return paidOrderNumList;
    }



    public List<String> getToBeOrderNumList() throws IOException {
       switchToToBePayList();
       action.sleep(2);
       List<WebElement> webElement = action.findElements(orderListPage.未支付订单列表());
       setToBeOrderList(webElement);
       for(WebElement we:getToBeOrderList()){
           toBeOrderNumList.add(we.getText());
       }
        return toBeOrderNumList;
    }

    public List<String> getCloseOrderNumList() {
        return closeOrderNumList;
    }

    public String getPaidUrl() {
        return paidUrl;
    }

    public void setPaidUrl(String paidUrl) {
        this.paidUrl = paidUrl;
    }

    public String getToBeUrl() {
        return toBeUrl;
    }

    public void setToBeUrl(String toBeUrl) {
        this.toBeUrl = toBeUrl;
    }

    public String getClosedUrl() {
        return closedUrl;
    }

    public void setClosedUrl(String closedUrl) {
        this.closedUrl = closedUrl;
    }

}
