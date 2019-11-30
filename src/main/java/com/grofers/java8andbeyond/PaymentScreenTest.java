package com.grofers.java8andbeyond;

import com.grofers.helper.InitializeDrivers;
import com.grofers.java8andbeyond.pages.PaymentScreenPage;
import com.grofers.test.ConfigureConstant;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.function.Consumer;

import static com.grofers.java8andbeyond.PaymentScreenActions.*;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 30/11/19
 */
public class PaymentScreenTest {

    Logger logger = LoggerFactory.getLogger(PriceTableAssignment.class);
    WebDriver driver;
    PaymentScreenPage paymentScreenPage;
    SoftAssert softAssert;
    private boolean failedInConfigReading = false;

    @BeforeClass
    public void beforeClass() {
        try {
            logger.info("Inside Before Class");
            driver = InitializeDrivers.getDriver(ConfigureConstant.getConstantFieldsValue("browser"));
            paymentScreenPage = new PaymentScreenPage(driver);
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


    @Test(dataProvider = "consumerProvider")
    public void testPaymentScreen(Consumer<PaymentScreenPage> consumer) throws InterruptedException {
        paymentScreenPage.goTo();
        consumer.accept(paymentScreenPage);
        Thread.sleep(5000);
    }


    @DataProvider(name = "consumerProvider")
    public Object[][] testdata() {
        return new Object[][]{
                {validCC.andThen(buy).andThen(successfulPurchase)},
                {discountedCoupon.andThen(validCC).andThen(buy).andThen(successfulPurchase)},
                {freeCoupon.andThen(buy).andThen(successfulPurchase)},
                {discountedCoupon.andThen(invalidCC).andThen(buy).andThen(failedPurchase)},
                {invalidCC.andThen(buy).andThen(failedPurchase)},
                {buy.andThen(failedPurchase)}
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
