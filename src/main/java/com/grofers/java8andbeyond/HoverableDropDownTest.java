package com.grofers.java8andbeyond;

import com.grofers.helper.InitializeDrivers;
import com.grofers.test.ConfigureConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 30/11/19
 */
public class HoverableDropDownTest {
    Logger logger = LoggerFactory.getLogger(PriceTableAssignment.class);
    WebDriver driver;
    SoftAssert softAssert;
    Actions actions;
    private boolean failedInConfigReading = false;

    @BeforeClass
    public void beforeClass() {
        try {
            logger.info("Inside Before Class");
            driver = InitializeDrivers.getDriver(ConfigureConstant.getConstantFieldsValue("browser"));
            actions = new Actions(driver);
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


    @Test(dataProvider = "linkProvider")
    public void dropdownTest(String path) throws InterruptedException {
        this.driver.get("https://www.bootply.com/render/6FC76YQ4Nh#");
        String[] split = path.split("=>");

        Arrays.stream(split)
                .map(String::trim)
                .map(str -> driver.findElement(By.linkText(str)))
                .map(ele -> actions.moveToElement(ele))
                .forEach(Actions::perform);

        Thread.sleep(5000);

    }


    @DataProvider(name = "linkProvider")
    public Object[][] testdata() {
        return new Object[][]{
                {"Dropdown => Dropdown Link 2"},
                {"Dropdown => Dropdown Link 5 => Dropdown Submenu Link 5.1"},
                {"Dropdown => Dropdown Link 5 => Dropdown Submenu Link 5.4 => Dropdown Submenu Link 5.4.2"}
        };
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
