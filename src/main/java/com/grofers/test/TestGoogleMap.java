package com.grofers.test;

import com.grofers.helper.InitializeDrivers;
import com.grofers.pages.InstagramLoginPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : shiv.ashish@grofers.com
 * Contant me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 2019-04-26
 */
public class TestGoogleMap {
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
    public void openTheImage() throws InterruptedException {
        List<String> urls = new ArrayList<>();
        urls.add("https://www.google.com/maps/contrib/115033290295310928908/place/ChIJc3EyPna7eTkRo0PZTmymJP8/@24.6884646,78.3998178,3a,75y,90t/data=!3m7!1e2!3m5!1sAF1QipMcZFjB7ntsfi-pYmn26xlcPVRMMtDkwRX59J4o!2e10!6shttps:%2F%2Flh5.googleusercontent.com%2Fp%2FAF1QipMcZFjB7ntsfi-pYmn26xlcPVRMMtDkwRX59J4o%3Dw365-h273-k-no!7i4032!8i3024!4m6!1m5!8m4!1e2!2s115033290295310928908!3m1!1e1");
        urls.add("https://www.google.com/maps/contrib/115033290295310928908/place/ChIJc3EyPna7eTkRo0PZTmymJP8/@24.6884646,78.3998178,3a,75y,90t/data=!3m7!1e2!3m5!1sAF1QipMzQ19YzSpD4qR7s1nFoN1MhOqqQis6vVfPAV5M!2e10!6shttps:%2F%2Flh5.googleusercontent.com%2Fp%2FAF1QipMzQ19YzSpD4qR7s1nFoN1MhOqqQis6vVfPAV5M%3Dw365-h273-k-no!7i3264!8i2448!4m6!1m5!8m4!1e2!2s115033290295310928908!3m1!1e1");
        urls.add("https://www.google.com/maps/contrib/115033290295310928908/place/ChIJc3EyPna7eTkRo0PZTmymJP8/@24.6884646,78.3998178,3a,75y,90t/data=!3m7!1e2!3m5!1sAF1QipPJpXcZ3rUFYZeyvUjCUb2XM_oYXuZZzxcORBn8!2e10!6shttps:%2F%2Flh5.googleusercontent.com%2Fp%2FAF1QipPJpXcZ3rUFYZeyvUjCUb2XM_oYXuZZzxcORBn8%3Dw365-h273-k-no!7i3264!8i2448!4m6!1m5!8m4!1e2!2s115033290295310928908!3m1!1e1");
        urls.add("https://www.google.com/maps/contrib/115033290295310928908/place/ChIJc3EyPna7eTkRo0PZTmymJP8/@24.6884646,78.3998178,3a,75y,90t/data=!3m7!1e2!3m5!1sAF1QipPRh8lOI8Qi3xM2-UXRmceNnPmfv62m45SALRFu!2e10!6shttps:%2F%2Flh5.googleusercontent.com%2Fp%2FAF1QipPRh8lOI8Qi3xM2-UXRmceNnPmfv62m45SALRFu%3Dw393-h260-k-no!7i4032!8i2664!4m6!1m5!8m4!1e2!2s115033290295310928908!3m1!1e1");

        for (int i = 0; i < 100; i++) {
            for (String url : urls) {

                driver.get(url);
                Thread.sleep(10000);
                driver.close();
            }
        }

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
