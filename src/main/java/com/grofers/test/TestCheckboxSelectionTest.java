package com.grofers.test;

import com.grofers.helper.InitializeDrivers;
import com.grofers.pages.TableDemoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 31/10/19
 */
public class TestCheckboxSelectionTest {

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


    @Test(dataProvider = "criteriaProvider")
    public void testCheckboxSelectionTest(Predicate<List<WebElement>> searchCriteria) throws InterruptedException {
        tableDemoPage.goTo();
        tableDemoPage.selectRows(searchCriteria);

        Thread.sleep(1000);
        softAssert.assertAll();

    }

    @DataProvider(name = "criteriaProvider")
    public Object[][] testdata() {
        Predicate<List<WebElement>> allMales = e -> e.get(1).getText().equalsIgnoreCase("male");
        Predicate<List<WebElement>> allFemales = e -> e.get(1).getText().equalsIgnoreCase("female");
        Predicate<List<WebElement>> allGender = allMales.or(allFemales);
        Predicate<List<WebElement>> allAU = e -> e.get(2).getText().equalsIgnoreCase("AU");
        Predicate<List<WebElement>> allFemaleAU = allFemales.and(allAU);
        return new Object[][]{
                {allFemales},
                {allMales},
                {allGender},
                {allAU},
                {allFemaleAU}
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
