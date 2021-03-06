package org.webdriver.duiaui.pageObjectConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.webdriver.duiaui.util.Log;

//通过解析xml文件自动生成对象库代码
public class PageObjectAutoCode {
	Log log=new Log(this.getClass());
	static  String path="src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml";
	public static void autoCode() throws Exception {
		File file = new File(path);

		if (!file.exists()) {
			throw new IOException("Can't find " + path);
		}
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		//对象库xml文件根节点
		Element root = document.getRootElement();

		List<Element> elements = document.selectNodes("//page");
		for(Element element:elements){
			String pageName = element.attribute(0).getValue();
			storeValue(pageName,element);
		}


		//遍历根节点下的第一个节点（page节点）
/*		for (Iterator<?> k = root.elementIterator(); k.hasNext(); ) {
			//根节点  src
			Element firstNode =  (Element)k.next();
			String firstNodeName = firstNode.getName();
			//获取page节点的name属性值
			String firstNodeAttributeValue = firstNode.attribute(0).getValue();
			if ("page".equals(firstNodeName)) {
					System.out.println(firstNodeAttributeValue);
				page = firstNode;
				pagename = firstNodeAttributeValue;
				storeValue(pagename,page);
			} else {
				for (Iterator<Element> j = firstNode.elementIterator(); j.hasNext(); ) {
					firstNode = j.next();
					firstNodeAttributeValue = firstNode.attribute(0).getValue();
					if ("page".equals(firstNodeName)) {
						System.out.println(firstNodeAttributeValue);
						page = firstNode;
						pagename = firstNodeAttributeValue;
						storeValue(pagename,page);
					}
				}
			}
		}*/
	}


	public static void  storeValue(String pagename,Element page){
		//将pageName存储为数组
		String[] pageNameArray=pagename.split("\\.");
		System.out.println(pageNameArray);
		System.out.println(pageNameArray[0]);
		String packageName = "";
		//获取要写入的page所属的类名
		String pageClassName=pageNameArray[pageNameArray.length-1].toString();
		//将pageNameArray组装，获取对象库报名
		for(int i = 0;i<pageNameArray.length-1;i++){
			if(i<pageNameArray.length-2){
				packageName = packageName+pageNameArray[i]+".";
			}else{
				packageName = packageName+pageNameArray[i];
			}
		}

		//--自动编写对象库代码（XXPage.java）开始--
		StringBuffer sb=new StringBuffer("package "+packageName+";\n");
		sb.append("import java.io.IOException;\n");
		sb.append("import java.io.InputStream;\n");
		sb.append("import org.webdriver.duiaui.util.BaseAction;\n");
		sb.append("import org.webdriver.duiaui.util.Locator;\n");
		sb.append("import org.webdriver.duiaui.pageObjectConfig.PageObjectAutoCode;");
		sb.append("//"+page.attribute(2).getValue()+"_对象库类\n");
		sb.append("public class "+ pageClassName+" extends BaseAction {\n");
		sb.append("//用于eclipse工程内运行查找对象库文件路径\n");
		sb.append("private String path=\"src/main/java/org/webdriver/duiaui/pageObjectConfig/UILibrary.xml\";\n");
		//sb.append("//用户打包成jar后查找对象库文件路径\n");
		//sb.append("private InputStream pathInputStream=PageObjectAutoCode.class.getClassLoader().getResourceAsStream(\"net/hk515/pageObjectConfig/UILibrary.xml\");	\n");
		sb.append(" public   "
				+ pageClassName
				+ "() {\n");
		sb.append("//工程内读取对象库文件\n	");
		sb.append("setXmlObjectPath(path);\n");
		sb.append("getLocatorMap();");
		sb.append("\n}");
		//sb.append("\n private String path=PageObjectAutoCode.class.getClassLoader().getResource(\"net/hk515/pageObjectConfig/UILibrary.xml\").getPath();");
		//遍历Page节点下的Locator节点
		for(Iterator<?> j=page.elementIterator();j.hasNext();)
		{
			//获取locaror节点
			Element locator =(Element)j.next();
			String locatorName=locator.getTextTrim();
			if(locator.attributeCount()>3)
			{sb.append("\n/***\n"
					+ "* "+locator.attribute(3).getValue()+"\n"
					+ "* @return\n"
					+ "* @throws IOException\n"
					+ "*/\n");
			}
			else
			{
				sb.append("\n");
			}
			sb.append("public Locator "+locatorName+"() throws IOException\n {\n");
			//sb.append("   setXmlObjectPath(path);\n");
			sb.append("   Locator locator=getLocator(\""+locatorName+"\");\n");
			sb.append("   return locator;\n }\n");
		}
		sb.append("}");
		//将自动生成的PageObject代码写入文件
		/**
		 * 之后需要做成可配置项
		 * */
		File pageObjectFile=new File("src/main/java/org/webdriver/duiaui/pageObject/front/pc/"+pageClassName+".java");
		if(pageObjectFile.exists())
		{
			pageObjectFile.delete();
		}
		try {
			FileWriter fileWriter=new FileWriter(pageObjectFile, false);
			BufferedWriter output = new BufferedWriter(fileWriter);
			output.write(sb.toString());
			output.flush();
			output.close();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		System.out.println(sb);
		Log log=new Log(PageObjectAutoCode.class);
		log.info("自动生成对象库java代码成功");
	}



	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		PageObjectAutoCode.autoCode();
	}

}
