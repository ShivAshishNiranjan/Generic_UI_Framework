package com.grofers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 01/11/19
 */
public class TableDemoPage {


    Logger logger = LoggerFactory.getLogger(GrofersBlog.class);

    WebDriver driver;

    public TableDemoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get("https://vins-udemy.s3.amazonaws.com/java/html/java8-stream-table.html");
    }

    public void selectRows(Predicate<List<WebElement>> predicate) {
        driver.findElements(By.tagName("tr"))
                .stream()
                .skip(1)
                .map(tr -> tr.findElements(By.tagName("td")))
                .filter(predicate)
                .map(tdList -> tdList.get(3))
                .map(tdList -> tdList.findElement(By.tagName("input")))
                .forEach(WebElement::click);
    }


}
