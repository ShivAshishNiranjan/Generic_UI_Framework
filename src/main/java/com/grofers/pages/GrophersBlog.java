package com.grofers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrophersBlog {

    Logger logger = LoggerFactory.getLogger(GrophersBlog.class);
    WebDriver driver;

    public String getPageUrl() {
        return pageUrl;
    }

    String pageUrl = "https://grofers.com/blog/";

    public GrophersBlog(WebDriver driver) {
        this.driver = driver;
    }




}

