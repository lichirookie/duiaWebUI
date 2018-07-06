package org.webdriver.duiaui.util;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log
{
    private final Class<?> clazz;
    private  Logger logger;
    private static int count = 0;
    /**
     *
     * @param clazz 获取当前类
     */
    public Log(Class<?> clazz)
    {
        this.clazz=clazz;
        //Logger.getLogger的方法是调用的是LogManager.getLogger()方法，所以这两个方法都是返回logger
        this.logger=Logger.getLogger(this.clazz);
        Log.initlog4j();

    }


    private static  void initlog4j()
    {
        count++;
        //创建Propderties对象
        Properties prop=new Properties();
	   /*Log4j建议只使用四个级别，优先级从高到低分别是ERROR、WARN、INFO、DEBUG
	   这里定义能显示到的最低级别,若定义到INFO级别,则看不到DEBUG级别的信息了~!级别后面是输出端*/
        prop.setProperty("log4j.rootLogger", "INFO,CONSOLE,E,F");
        prop.setProperty("log4j.appender.CONSOLE", "org.apache.log4j.ConsoleAppender");
        prop.setProperty("log4j.appender.CONSOLE.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.CONSOLE.layout.ConversionPattern", "[%d{YYYY-MM-dd HH:mm:ss,SSS}] %-5p %c %m%n");



        //设置httpClient级别
        prop.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        prop.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        prop.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");
        prop.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");



        String src="testoutput/log";
        //设置日期格式
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
        //获取当前日期
        String date=dateFormat.format(new Date()).toString();

        File dir = new File(src+"/"+date);
        if (!dir.exists())
        {dir.mkdirs();}
        String filepath=dir.getAbsolutePath()+"/"+"log_"+date+".log";

        //创建log文件时，将log同时推送到webapps中

        //1、删除存在的文件
        String websrc = "D:\\apache-tomcat-7.0.88\\webapps\\testoutput\\log";
        File webdir = new File(websrc);
        if(!webdir.exists()){
            webdir.mkdir();
            System.out.printf("创建的文件夹为："+webdir.getAbsoluteFile()+"");
        }
        if(count==1){
            DeleteFile deleteFile = new DeleteFile();
            deleteFile.deleteDir(websrc);
        }
        //2、将创建的文件移动到该文件夹
        String appFilepath=webdir.getAbsolutePath()+"/"+"log.html";


        prop.setProperty("log4j.appender.E","org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.E.file",filepath);
        //prop.setProperty("log4j.appender.E.file",appFilepath);
        prop.setProperty("log4j.appender.E.layout","org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.E.layout.ConversionPattern", "[%d{YYYY-MM-dd HH:mm:ss,SSS}] %-5p %c %m%n");
        prop.setProperty("log4j.appender.F","org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.F.encoding","GBK");
        //设置html输出路径
        String filepathHtml=dir.getAbsolutePath()+"/"+"log_"+date+".html";
        prop.setProperty("log4j.appender.F.file",filepathHtml);
        prop.setProperty("log4j.appender.F.file",appFilepath);
        prop.setProperty("log4j.appender.F.layout","org.apache.log4j.HTMLLayout");
        //prop.setProperty("log4j.appender.F.layout.ConversionPattern", "[%d{YYYY-MM-dd HH:mm:ss,SSS}] %-5p %c %m%n");

        PropertyConfigurator.configure(prop);
    }
    public  void info(String message)
    {
        logger.info(message);
    }
    public void warn(String message)
    {
        logger.warn(message);
    }
    public void error(String message)
    {
        logger.error(message);
    }
    public void debug(String message)
    {
        logger.debug(message);
    }

}

