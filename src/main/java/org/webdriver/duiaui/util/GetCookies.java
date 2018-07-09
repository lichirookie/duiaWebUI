package org.webdriver.duiaui.util;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.SimpleDateFormat;
import java.util.*;

public class GetCookies{
    static WebDriver driver = new FirefoxDriver();
    ElementAction action = new ElementAction();
    Log log = new Log(this.getClass());


    public static String[]  splitCookiesBysemicolon(Set<Cookie> cookies){
        List<String> loginNeed = new ArrayList<>();
        //遍历cookie
        for (Cookie cookie : cookies) {
            //cookie字符串化
            String cookiestr = cookie.toString();
            //先以分号切割cookie
            String[] temp = cookiestr.split(";");
            //过滤出a_n和d_t开头的存入List
            for (int j = 0; j < temp.length; j++) {
                if (temp[j].trim().contains("a_n") || temp[j].trim().contains("d_t")) {
                    loginNeed.add(temp[j]);
                }
            }
        }
        //List转数组
        String[] logincookie = new String[loginNeed.size()];
        return loginNeed.toArray(logincookie);
    }

    public static List<Map> storeCookies2List(String[] logincookie){

        Map<String, String> cookieMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < logincookie.length; i++) {

            String[] temp1 = null;
            temp1 = logincookie[i].split("=");
            try {
                if (temp1.length == 2) {
                    cookieMap.put(temp1[0], temp1[1]);
                } else if (temp1.length < 2) {
                    cookieMap.put(temp1[0], null);
                }
            } catch (Exception e) {
                System.out.println("那就不对了");
            }

        }
        list.add(cookieMap);
        return list;
    }

    public static void getCookies(String username,String password,int row,String sheetName) throws Exception {
        driver.get("http://sso.duia.com/uc");
        Thread.sleep(1000);
        driver.findElement(By.className("check")).click();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("pwd")).sendKeys(password);
        driver.findElement(By.id("loginSubmit")).click();
        Set<Cookie> cookies = driver.manage().getCookies();
        //存放cookie的map
        String[] logincookie;
        logincookie = splitCookiesBysemicolon(cookies);

        List<Map> list = storeCookies2List(logincookie);

        System.out.println("存放的cookie"+list.get(0));

        ExcelWriter.writeToExcelMapKey("E:\\7.xls", sheetName, list, row);
 }



    public static String[][] readExcel(int sheet,String path){
       return ExcelReadUtil.case_data_excel2String(sheet,path);
    }

    public static void stroeCookie2Excel() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format( new Date().getTime());
        String sheetName = time+"LoginCookies";
        ExcelWriter.createExcel("E:\\7.xls",sheetName,new String[]{"a_n","d_t"});
        String[][] excelDate = readExcel(0,"src/main/resources/data/num.xls");
        for(int i =0;i<excelDate.length;i++){
             String username = excelDate[i][0];
             String password = excelDate[i][1];
             getCookies(username,password,i,sheetName);
             driver.manage().deleteAllCookies();
        }
        driver.close();
    }
}
