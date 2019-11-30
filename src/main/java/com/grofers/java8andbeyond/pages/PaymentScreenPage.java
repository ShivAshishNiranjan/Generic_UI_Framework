package com.grofers.java8andbeyond.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 30/11/19
 */
public class PaymentScreenPage {
    private final WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(PriceTable.class);

    @FindBy(id = "coupon")
    private WebElement coupon;

    @FindBy(id = "couponbtn")
    private WebElement couponbtn;

    @FindBy(id = "cc")
    private WebElement cc;

    @FindBy(id = "year")
    private WebElement year;

    @FindBy(id = "cvv")
    private WebElement cvv;

    @FindBy(id = "buy")
    private WebElement buy;

    @FindBy(id = "status")
    private WebElement status;


    public PaymentScreenPage(WebDriver driver) {
        this.driver = driver;
        logger.info("Initializing the Web Element/Elements");
        PageFactory.initElements(driver, this);
        logger.info("Done: Initializing the Web Element/Elements");
    }

    public void goTo() {
        this.driver.get("https://vins-udemy.s3.amazonaws.com/java/html/java8-payment-screen.html");
    }

    public void applyPromoCode(String promoCode) {
        this.coupon.sendKeys(promoCode);
        this.couponbtn.click();
    }

    public void enterCC(String cc, String year, String cvv) {
        this.cc.sendKeys(cc);
        this.year.sendKeys(year);
        this.cvv.sendKeys(cvv);
    }

    public void buyProduct() {
        this.buy.click();
    }

    public String getStatus() {
        return  this.status.getText().trim();
    }


}
