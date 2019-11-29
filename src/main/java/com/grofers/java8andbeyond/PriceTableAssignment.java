package com.grofers.java8andbeyond;

import com.grofers.helper.InitializeDrivers;
import com.grofers.java8andbeyond.pages.PriceTable;
import com.grofers.test.ConfigureConstant;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 29/11/19
 */


public class PriceTableAssignment {

    Logger logger = LoggerFactory.getLogger(PriceTableAssignment.class);
    WebDriver driver;
    PriceTable priceTable;
    SoftAssert softAssert;
    private boolean failedInConfigReading = false;

    @BeforeClass
    public void beforeClass() {
        try {
            logger.info("Inside Before Class");
            driver = InitializeDrivers.getDriver(ConfigureConstant.getConstantFieldsValue("browser"));
            priceTable = new PriceTable(driver);
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
        priceTable.goTo();
        priceTable.selectMinPriceRow();
        String status = priceTable.getStatus();
        System.out.println("Status is " + status);
        Assert.assertTrue(status.toLowerCase().contentEquals("pass"), "Failed in Selecting Checkbox with Price");
        Thread.sleep(1000);
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

