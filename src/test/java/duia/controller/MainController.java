package duia.controller;

import org.testng.annotations.Test;
import org.webdriver.duiaui.action.front.pc.HomePageAction;

import java.io.IOException;

public class MainController {

    @Test
    public void selectSku() throws IOException {
        HomePageAction homePageAction = new HomePageAction();
        homePageAction.selectSku();
    }
}
