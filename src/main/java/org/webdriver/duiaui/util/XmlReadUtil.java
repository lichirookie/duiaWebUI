package org.webdriver.duiaui.util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import org.dom4j.*;
import org.testng.annotations.Test;
import org.webdriver.duiaui.util.Locator.*;

import org.dom4j.io.SAXReader;
//包含导入了自定义的ByType枚举类型

/**
 * xml读取工具类
 * @author lichiguan
 *
 */
public class XmlReadUtil {
	//获取定位方式
	public  HashMap<String, Locator> readXMLDocument(String path,String pageName) {
		Log log=new Log(this.getClass());
		//log.info("----------开始解析UILibrary.xml对象库-----------");
		//log.info("开始读取："+pageName+"页信息");
		HashMap<String, Locator> locatorMap = new HashMap<String, Locator>();
		locatorMap.clear();
/*		String[] srcs = pageName.split("\\.");
		//要求对包的命名标准，倒数第二层为来源,通过包名来做dom分配
		String src = srcs[srcs.length-2];*/
		try {
			File file = new File(path);
			if (!file.exists()) {
				throw new IOException("Can't find " + path);
			}
			SAXReader reader = new SAXReader();
			Document document = reader.read(file);
			Element root = document.getRootElement();
			//需要先用root来判断，直接跳转到相应的节点下
			//root中有三个子节点<src>
			for (Iterator<?> i = root.elementIterator(); i.hasNext();)
			{
				Element srcNode = (Element) i.next();
				for(Iterator<?> j = srcNode.elementIterator();j.hasNext();){
					Element page = (Element) j.next();
					//Element page = src.elements().get(j);
					if (page.attribute(0).getValue().equalsIgnoreCase(pageName))
					{
						log.info("成功读取页名:" + pageName);
						for (Iterator<?> l = page.elementIterator(); l.hasNext();)
						{
							String type = null;
							String timeOut = "3";
							String value = null;
							String locatorName = null;
							Element locator = (Element) l.next();
							String returenType = "";
							//获取元素名
							locatorName = locator.getText();
							//log.info("开始读取"+locatorName+"定位信息");
							for (Iterator<?> k = locator.attributeIterator(); k.hasNext();)
							{
								Attribute attribute = (Attribute) k.next();
								if ("type".equals(attribute.getName()))
								{
									type = attribute.getValue();
									//log.info("读取定位方式： " + type);
								} else if ("timeout".equals(attribute.getName()))
								{
									timeOut = attribute.getValue();
									//log.info("读取元素等待时间 ：" + timeOut);
								} else if ("value".equals(attribute.getName()))
								{
									value = attribute.getValue();
									//log.info("读取定位内容：" + value);
								}else if ("returntype".equals(attribute.getName())){
									returenType = attribute.getValue();
								}
							}
							//trim()去除字符串前后空格
							/**
							 * 将xml中解析的各字段值封装到Locator对象中
							 * */
							Locator temp = new Locator(value.trim(),Integer.parseInt(timeOut), getByType(type),locatorName.trim(),returenType.trim());
							//log.info("成功读取 " + locatorName+"元素信息！");
							/**
							 * 将locatorName作为key,Locator temp作为value存储到locatorMap中，最后返回这个map
							 * */
							locatorMap.put(locatorName.trim(), temp);
						}
						continue;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		//log.info("----------解析UILibrary.xml对象库完毕-----------\n");
		/**
		 * 返回locatorMap
		 * */
		return locatorMap;
	}

	@Test
	public void testReadXmlDocument(){
		readXMLDocument("src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml","org.webdriver.duiaui.pageObject.front.m.LoginPage");
	}

	public  HashMap<String, Locator> readXMLDocument(InputStream path,String pageName) {
		Log log=new Log(this.getClass());
		//log.info("----------开始解析UILibrary.xml对象库-----------");
		//log.info("开始读取："+pageName+"页信息");
		HashMap<String, Locator> locatorMap = new HashMap<String, Locator>();
		locatorMap.clear();
		try {
			InputStreamReader inputStreamReader=new InputStreamReader(path,"UTF-8");
			SAXReader reader = new SAXReader();
			Document document=reader.read(inputStreamReader);
			Element root = document.getRootElement();
			for (Iterator<?> i = root.elementIterator(); i.hasNext();)
			{
				Element page = (Element) i.next();
				if (page.attribute(0).getValue().equalsIgnoreCase(pageName))
				{
					//log.info("成功读取页名:" + pageName);
					for (Iterator<?> l = page.elementIterator(); l.hasNext();)
					{
						String type = null;
						String timeOut = "3";
						String value = null;
						String locatorName = null;
						Element locator = (Element) l.next();
						//获取元素名
						locatorName = locator.getText();
						//log.info("开始读取"+locatorName+"定位信息");
						for (Iterator<?> j = locator.attributeIterator(); j.hasNext();)
						{
							Attribute attribute = (Attribute) j.next();
							if (attribute.getName().equals("type"))
							{
								type = attribute.getValue();
								//log.info("读取定位方式： " + type);
							} else if (attribute.getName().equals("timeout"))
							{
								timeOut = attribute.getValue();
								//log.info("读取元素等待时间 ：" + timeOut);
							} else if (attribute.getName().equals("value"))
							{
								value = attribute.getValue();
								//log.info("读取定位内容：" + value);
							}
						}
						//trim()去除字符串前后空格
						Locator temp = new Locator(value.trim(),Integer.parseInt(timeOut), getByType(type),locatorName.trim());
						//log.info("成功读取 " + locatorName+"元素信息！");
						locatorMap.put(locatorName.trim(), temp);
					}
					continue;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		//log.info("----------解析UILibrary.xml对象库完毕-----------\n");
		return locatorMap;
	}
	/**
	 * @param type
	 */
	public static ByType getByType(String type) {
		ByType byType = ByType.xpath;
		if (type == null || type.equalsIgnoreCase("xpath")) {
			byType = ByType.xpath;
		} else if (type.equalsIgnoreCase("id")) {
			byType = ByType.id;
		} else if (type.equalsIgnoreCase("linkText")) {
			byType = ByType.linkText;
		} else if (type.equalsIgnoreCase("name")) {
			byType = ByType.name;
		} else if (type.equalsIgnoreCase("className")) {
			byType = ByType.className;
		} else if (type.equalsIgnoreCase("cssSelector")) {
			byType = ByType.cssSelector;
		} else if (type.equalsIgnoreCase("partialLinkText")) {
			byType = ByType.partialLinkText;
		} else if (type.equalsIgnoreCase("tagName")) {
			byType = ByType.tagName;
		}
		return byType;
	}

	public static String getXmlPageURL(InputStream path ,String pageName)
	{
		//System.out.print(pageName);
		String URL=null;
		try {
			InputStreamReader inputStreamReader=new InputStreamReader(path,"UTF-8");
			SAXReader reader = new SAXReader();
			Document document=reader.read(inputStreamReader);
			System.out.println("文档内容"+document.asXML());
			//获取xml文档的根节点
			Element rootElement=document.getRootElement();
			//遍历pom.xml根节点下的page节点
			for(Iterator<?> i=rootElement.elementIterator();i.hasNext();)
			{
				Element page=(Element)i.next();
				/**
				 * 判断page节点的第一个属性pagename是否跟输入的pageName一致
				 */
				if(page.attribute(0).getValue().equals(pageName))
				{
					//System.out.println("page Info is:" + pageName);
					//遍历page节点下的元素
					for(Iterator<?>n=page.attributeIterator();n.hasNext();)
					{
						Attribute attribute=(Attribute)n.next();
						if(attribute.getName().equals("value"))
						{
							URL=attribute.getValue().trim();
						}

					}

					continue;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


		return URL;

	}

	public static String getXmlPageURL(String path,String pageName)
	{
		//System.out.print(pageName);

		String URL=null;
		File file =new File(path);
		try {
			if(!file.exists())
			{
				throw new IOException("can not find xmldomcument"+path);
			}
			SAXReader saxReader=new SAXReader();
			//读取xml文档
			Document document=saxReader.read(file);
			//获取xml文档的根节点
			Element rootElement=document.getRootElement();
			//遍历pom.xml根节点下的page节点
			for(Iterator<?> i=rootElement.elementIterator();i.hasNext();)
			{
				Element page=(Element)i.next();
				/**
				 * 判断page节点的第一个属性pagename是否跟输入的pageName一致
				 */
				if(page.attribute(0).getValue().equals(pageName))
				{
					//System.out.println("page Info is:" + pageName);
					//遍历page节点下的元素
					for(Iterator<?>n=page.attributeIterator();n.hasNext();)
					{
						Attribute attribute=(Attribute)n.next();
						if(attribute.getName().equals("value"))
						{
							URL=attribute.getValue().trim();
						}

					}

					continue;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return URL;


	}
	public static  String getTestngParametersValue(String path,String ParametersName) throws DocumentException, IOException
	{
		File file = new File(path);
		if (!file.exists()) {
			throw new IOException("Can't find " + path);

		}
		String value=null;
		SAXReader reader = new SAXReader();
		Document  document = reader.read(file);
		Element root = document.getRootElement();
		for (Iterator<?> i = root.elementIterator(); i.hasNext();)
		{
			Element page = (Element) i.next();
			if(page.attributeCount()>0)
			{
				if (page.attribute(0).getValue().equalsIgnoreCase(ParametersName))
				{
					value=page.attribute(1).getValue();
					//System.out.println(page.attribute(1).getValue());
				}
				continue;
			}
			//continue;
		}
		return value;

	}

}
