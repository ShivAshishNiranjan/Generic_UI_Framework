package com.grofers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrofersBlog {

    Logger logger = LoggerFactory.getLogger(GrofersBlog.class);
    WebDriver driver;
	By pageFooter = By.xpath("//h5[contains(text(),'Grofers India Pvt. Ltd. 2016-2017')]");

    public String getPageUrl() {
        return pageUrl;
    }

    String pageUrl = "https://grofers.com/blog/";

    public GrofersBlog(WebDriver driver) {
        this.driver = driver;
    }

    public boolean validatePageFooter()
    {
	    WebElement element = driver.findElement(pageFooter);
	    if(element!=null)
	    	return true;
	    else
	    	return false;
    }




}

