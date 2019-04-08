package com.grofers.test;

import com.grofers.helper.InitializeDrivers;
import com.grofers.pages.InstagramLoginPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

/**
 * @author : shiv.ashish@grofers.com
 * file created on 2019-04-03
 */
public class TestInstagram {
    Logger logger = LoggerFactory.getLogger(TestGrofersBlogPage.class);
    WebDriver driver;
    InstagramLoginPage instagramLoginPage;
    SoftAssert softAssert;
    private boolean failedInConfigReading = false;


    @BeforeClass
    public void beforeClass() {
        try {
            logger.info("Inside Before Class");
            driver = InitializeDrivers.getDriver(ConfigureConstant.getConstantFieldsValue("browser"));
            instagramLoginPage = new InstagramLoginPage(driver);

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
    public void loginToInstagram() {
        driver.get(instagramLoginPage.getBaseUrl()+ "/"+ instagramLoginPage.getLoginUrl());
        driver.findElement(instagramLoginPage.getUsernameInputElement()).sendKeys(instagramLoginPage.getUsername());
        driver.findElement(instagramLoginPage.getPasswordInputElement()).sendKeys(instagramLoginPage.getPassword());
        driver.findElement(instagramLoginPage.getSubmitElement()).click();
        softAssert.assertAll();
    }


    @Test(dependsOnMethods = "loginToInstagram", priority = 1)
    public void getAllFollowersName() {

        softAssert.assertAll();

    }

    @Test(dependsOnMethods = "loginToInstagram", priority = 2)
    public void getAllFollowingNames() {


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
