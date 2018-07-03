package org.webdriver.duiaui.util;

/**
 * This is for element library
 * 
 * @author zhengshuheng
 *
 */
public class Locator {
  private String element;
  private int waitSec;
  private String locatorName;

  public String getReturnType() {
    return returnType;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }

  private String returnType;

  /**
   * create a enum variable for By
   * 
   * @author zhengshuheng
   *
   */
  public enum ByType {
    xpath, id, linkText, name, className, cssSelector, partialLinkText, tagName
  }
  private ByType byType;
  public Locator() {
  }
  /**
   * defaut Locator ,use Xpath
   * 
   * @author zhengshuheng
   * @param element
   */
  public Locator(String element) {
    this.element = element;
    this.waitSec = 3;
    this.byType = ByType.xpath;
  }
  public Locator(String element, int waitSec) {
    this.waitSec = waitSec;
    this.element = element;
    this.byType = ByType.xpath;
  }
  public Locator(String element, int waitSec, ByType byType) {
    this.waitSec = waitSec;
    this.element = element;
    this.byType = byType;
  }
  public Locator(String element, int waitSec, ByType byType,String locatorName) {
	    this.waitSec = waitSec;
	    this.element = element;
	    this.byType = byType;
	    this.locatorName=locatorName;
	  }

  public Locator(String element, int waitSec, ByType byType,String locatorName,String returnType) {
    this.waitSec = waitSec;
    this.element = element;
    this.byType = byType;
    this.locatorName=locatorName;
    this.returnType = returnType;
  }

  public String getElement() {
    return element;
  }


  public int getWaitSec() {
    return waitSec;
  }
  public ByType getBy() {
    return byType;
  }
  public void setBy(ByType byType) {
    this.byType = byType;
  }
  public String getLocalorName()
  {
	  return locatorName;
  }


}