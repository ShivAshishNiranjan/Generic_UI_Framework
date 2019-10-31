package com.grofers.test;

import com.grofers.helper.InitializeDrivers;
import com.grofers.helper.PredicateRules;
import com.grofers.helper.SeleniumWebDriverCommonHelper;
import com.grofers.helper.ValidateBrokenLinkHelper;
import com.grofers.pages.GrofersBlog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 31/10/19
 */
public class TestCheckboxSelectionTest {

    Logger logger = LoggerFactory.getLogger(TestGrofersBlogPage.class);
    WebDriver driver;
    GrofersBlog grofersBlog;
    SoftAssert softAssert;
    private boolean failedInConfigReading = false;


    @BeforeClass
    public void beforeClass() {
        try {
            logger.info("Inside Before Class");
            driver = InitializeDrivers.getDriver(ConfigureConstant.getConstantFieldsValue("browser"));
            grofersBlog = new GrofersBlog(driver);
        } catch (Exception e) {
            logger.error("Some Exception at Before Class Level : [{}]", e.getMessage());
            failedInConfigReading = true;
        }
    }


    @BeforeMethod
    public void beforeMethod() {
        if (!failedInConfigReading) {
            logger.info("Inside Before Method");
            if (driver == null) {
                logger.error("Failed in Initializing Web Driver so skipping the test");
                throw new SkipException("Failed in Initializing Web Driver so skipping the test");
            }
            softAssert = new SoftAssert();
        } else {
            throw new SkipException("Skipping all the tests");
        }
    }


    @Test(priority = -1)
    public void testCheckboxSelectionTest() throws InterruptedException {
        driver.get("https://vins-udemy.s3.amazonaws.com/java/html/java8-stream-table.html");
        String genderInput = "male";

        driver.findElements(By.tagName("tr"))
                .stream()
                .skip(1)
                .map(tr -> tr.findElements(By.tagName("td")))
                .filter(tdList->tdList.get(1).getText().equalsIgnoreCase(genderInput))
                .map(tdList -> tdList.get(3))
                .map(tdList ->  tdList.findElement(By.tagName("input")))
                .forEach(WebElement::click);

        Thread.sleep(10000);
        softAssert.assertAll();

    }

    @AfterMethod
    public void afterMethod() {
        logger.info("Inside After Method");

    }


    @AfterClass
    public void afterClass() {
        logger.info("Inside After Class");
        driver.quit();
    }


}
