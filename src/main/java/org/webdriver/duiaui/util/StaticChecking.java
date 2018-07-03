package org.webdriver.duiaui.util;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.webdriver.duiaui.util.TestBaseCase.driver;

public class StaticChecking {
    /**
     *
     * @测试点: 检查 js是否有报错
     * @验证点: 如果传入的url中有报错,返回报错信息
     @param  urllist 传入的url
     @param  timeout  页面超时时间
     @param  loopTimes  重试次数
     @param  timeoutDefault 默认加载页面的时间
     @return    报错的url和报错的信息
      * @备注： HashMap<String,List<String>>
     * @author zhangjun
     * @date 2018年5月24日
     @修改说明
     */
    public HashMap<String, List<String>> checkJsSEVERE(List<String> urllist, int timeout, int loopTimes, int timeoutDefault){
        List<String> levelList;
        HashMap<String, List<String>> severeLevel = new HashMap<String, List<String>>();
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (int i = 0; i < urllist.size(); i++) {
            try {
                if (logEntries.getAll().isEmpty() == false) { //判断是否有内容
                    logEntries.iterator().remove(); //有内容进行删除
                }
            } catch (Exception e) {
            }
            String get_url = urllist.get(i); //在重新进行删除
            //loopGet(get_url, timeout, loopTimes, timeoutDefault);
            levelList = new ArrayList<String>();
            logEntries = driver.manage().logs().get(LogType.BROWSER);
            for (LogEntry entry : logEntries) {
                Object level = entry.getLevel();
                String getLevel = String.valueOf(level);
                if (getLevel.equals("SEVERE")) {
                    levelList.add(entry.getMessage());
                }
            }
            severeLevel.put(get_url, levelList);
        }
        return severeLevel;
    }
}
