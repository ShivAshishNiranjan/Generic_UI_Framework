package com.grofers.test;

import com.grofers.helper.InitializeDrivers;
import com.grofers.pages.GrophersBlog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


public class TestGrophersBlogPage {


    Logger logger = LoggerFactory.getLogger(TestGrophersBlogPage.class);
    WebDriver driver;
    GrophersBlog grophersBlog;
    SoftAssert softAssert;
    private boolean failedInConfigReading = false;


    private String configFilePath ;
    private String configFileName ;



    @BeforeClass
    public void beforeClass(){
        try {
            logger.info("Inside Before Class");
            //configFilePath =ConfigureConstant.getConfigFilesValue("paytmconfigFilePath");
            //configFileName = ConfigureConstant.getConfigFilesValue("paytmconfigFileName");
            driver = InitializeDrivers.getDriver(ConfigureConstant.getConstantFieldsValue("browser"));
            grophersBlog = new GrophersBlog(driver);


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
    public void validatePageTitle() {
        driver.get(grophersBlog.getPageUrl());
        String title = driver.getTitle();
        logger.info("Page Tile is : [{}]",title);
    }



    @AfterMethod

    public void afterMethod() {
        logger.info("Inside After Method");
        softAssert.assertAll();
    }


    @AfterClass
    public void afterClass() {
        logger.info("Inside After Class");
        driver.quit();
    }


}
