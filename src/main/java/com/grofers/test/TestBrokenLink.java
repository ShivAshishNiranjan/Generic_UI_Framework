package com.grofers.test;

import com.grofers.helper.InitializeDrivers;
import com.grofers.helper.ValidateBrokenLinkHelper;
import com.grofers.pages.TableDemoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 02/11/19
 */
public class TestBrokenLink {


    Logger logger = LoggerFactory.getLogger(TestGrofersBlogPage.class);
    WebDriver driver;
    TableDemoPage tableDemoPage;
    SoftAssert softAssert;
    private boolean failedInConfigReading = false;

    @BeforeClass
    public void beforeClass() {
        try {
            logger.info("Inside Before Class");
            driver = InitializeDrivers.getDriver(ConfigureConstant.getConstantFieldsValue("browser"));
            tableDemoPage = new TableDemoPage(driver);
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


    @Test
    public void testCheckboxSelectionTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/broken_images");
        List<String> brokenLinks = driver.findElements(By.xpath("//*[@src]"))
                .stream()
                .map(e -> e.getAttribute("src"))
                .filter(src ->  ValidateBrokenLinkHelper.getResponseCode(src) != 200)
                .collect(Collectors.toList());

        Assert.assertEquals(brokenLinks.size(),0,brokenLinks.toString());
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
