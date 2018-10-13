package com.grofers.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SeleniumWebDriverCommonHelper {

    public static List<WebElement> findAllLinks(WebDriver driver)

    {

        List<WebElement> elementList ;
        elementList = driver.findElements(By.tagName("a"));
        elementList.addAll(driver.findElements(By.tagName("img")));
        List finalList = new ArrayList();

        for (WebElement element : elementList)
        {
            if (element.getAttribute("href") != null && !element.getAttribute("href").contentEquals("") )
                finalList.add(element);

        }

        return finalList;

    }
}
