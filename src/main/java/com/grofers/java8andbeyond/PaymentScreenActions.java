package com.grofers.java8andbeyond;

import com.grofers.java8andbeyond.pages.PaymentScreenPage;

import org.testng.Assert;

import java.util.function.Consumer;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 30/11/19
 */
public class PaymentScreenActions {

    public static final Consumer<PaymentScreenPage> freeCoupon = p -> p.applyPromoCode("FREEUDEMY");
    public static final Consumer<PaymentScreenPage> discountedCoupon = p -> p.applyPromoCode("PARTIALUDEMY");
    public static final Consumer<PaymentScreenPage> validCC = p -> p.enterCC("4111111111111111","2022","123");
    public static final Consumer<PaymentScreenPage> invalidCC = p -> p.enterCC("4111111111111112","2022","123");
    public static final Consumer<PaymentScreenPage> buy = PaymentScreenPage::buyProduct;

    public static final Consumer<PaymentScreenPage> successfulPurchase = p -> Assert.assertEquals(p.getStatus(),"PASS");
    public static final Consumer<PaymentScreenPage> failedPurchase = p -> Assert.assertEquals(p.getStatus(),"FAIL");


}
