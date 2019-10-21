package com.grofers.test;

import com.grofers.helper.InitializeDrivers;
import com.grofers.helper.PredicateRules;
import com.grofers.helper.SeleniumWebDriverCommonHelper;
import com.grofers.helper.ValidateBrokenLinkHelper;
import com.grofers.pages.GrofersBlog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class TestGrofersBlogPage {


	Logger logger = LoggerFactory.getLogger(TestGrofersBlogPage.class);
	WebDriver driver;
	GrofersBlog grofersBlog;
	SoftAssert softAssert;
	private boolean failedInConfigReading = false;


	@BeforeClass
	public void beforeClass() {
		try {
			logger.info("Inside Before Class");
			driver = InitializeDrivers.getDriver(ConfigureConstant.getConstantFieldsValue("browser"));
			grofersBlog = new GrofersBlog(driver);
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
	public void validatePageURL() {
		String validationMessage = ValidateBrokenLinkHelper.isLinkBroken(grofersBlog.getPageUrl());

		if (validationMessage.toLowerCase().contains("ok")) {
			driver.get(grofersBlog.getPageUrl());
			softAssert.assertTrue(grofersBlog.validatePageFooter(), "Error : Footer Message is not there ");
		} else {
			logger.error("validation Message is [{}]", validationMessage);
			softAssert.assertTrue(false, "Error : Page Url has been broken");
		}

		softAssert.assertAll();

	}


	@Test(dependsOnMethods = "validatePageURL", priority = 1)
	public void validateAllLinksInPage() {
		driver.get(grofersBlog.getPageUrl());
		List<WebElement> allLinks = SeleniumWebDriverCommonHelper.findAllLinks(driver);
		logger.info("Total number of elements found " + allLinks.size());

		// removing all BlankLinks
		PredicateRules.getRules().forEach(allLinks::removeIf);

		logger.info("Total number of elements remained " + allLinks.size());


		for (WebElement element : allLinks) {

			if (element.getAttribute("href").startsWith(grofersBlog.getBaseUrl())) {
				String validationMessage = ValidateBrokenLinkHelper.isLinkBroken(element.getAttribute("href"));
				logger.info("URL: " + element.getAttribute("href") + " returned " + validationMessage);

				if (!validationMessage.toLowerCase().contains("ok")) {
					logger.error("validation Message is [{}] for Url [{}]", validationMessage, element.getAttribute("href"));
					softAssert.assertTrue(false, "Error : " + element.getAttribute("href") + " Url has been broken" + " Response Message is : " + validationMessage + "\n");
				}
			}
		}


		softAssert.assertAll();

	}

	@Test(dependsOnMethods = "validatePageURL", priority = 2)
	public void validateSocialMediaInteractionButtons() {

		driver.get(grofersBlog.getPageUrl());
		List<WebElement> allButtons = grofersBlog.getAllSocialMediaInteractionButtons();
		logger.info("Total number of elements found " + allButtons.size());

		if (allButtons.size() != 0 && allButtons.size() == grofersBlog.getTotalSocialMediaButton()) {
			for (WebElement button : allButtons) {

				if (button.getAttribute("class").toLowerCase().contains("facebook")) {
					softAssert.assertTrue(button.getAttribute("href").contains(grofersBlog.getFacebookPageUrl()), "Facebook : Interaction URL is incorrect");
				} else if (button.getAttribute("class").toLowerCase().contains("instagram")) {
					softAssert.assertTrue(button.getAttribute("href").contains(grofersBlog.getInstagramPageUrl()), "Instagram : Interaction URL is incorrect");
				} else if (button.getAttribute("class").toLowerCase().contains("twitter")) {
					softAssert.assertTrue(button.getAttribute("href").contains(grofersBlog.getTwitterPageUrl()), "Twitter : Interaction URL is incorrect");
				} else if (button.getAttribute("class").toLowerCase().contains("linkedin")) {
					softAssert.assertTrue(button.getAttribute("href").contains(grofersBlog.getLinkedinPageUrl()), "LinkedIn : Interaction URL is incorrect");
				}
			}
		} else {
			softAssert.assertFalse(false, "Any Or All Social Media Interaction Buttons not there in Current page");
		}
		softAssert.assertAll();
	}


	@Test(dependsOnMethods = "validatePageURL", priority = 3)
	public void validateAllMandatoryLinks() {
		driver.get(grofersBlog.getPageUrl());
		List<String> allMandatoryLinksText = grofersBlog.getLinksToValidate();

		for (String linkText : allMandatoryLinksText) {
			softAssert.assertTrue(grofersBlog.validateLinkExistance(linkText), linkText + " : Link is missing from page  ");
		}


		softAssert.assertAll();

	}

	//@Test(dependsOnMethods = "validatePageURL", priority = 4)
	public void validateLikeButtonFunctionality() throws InterruptedException {

		driver.get(grofersBlog.getPageUrl());
		LinkedHashMap<WebElement, Integer> allLikeButtonsWithCountersCount = grofersBlog.getAllLikeArticlesButtonsWithCounterCounts();

		if (allLikeButtonsWithCountersCount.size() > 0) {
			List<Integer> previousCounts = new ArrayList<Integer>();
			for (WebElement likebutton : allLikeButtonsWithCountersCount.keySet()) {
				previousCounts.add(allLikeButtonsWithCountersCount.get(likebutton));
				likebutton.click(); // Click on the Like Button
				Thread.sleep(2000); // although it should not be used , used for testing purpose
			}

			driver.get(driver.getCurrentUrl()); // refreshing the page

			LinkedHashMap<WebElement, Integer> allUpdatedLikeButtonsWithCountersCount = grofersBlog.getAllLikeArticlesButtonsWithCounterCounts();
			List<Integer> newCounts = new ArrayList<Integer>(allUpdatedLikeButtonsWithCountersCount.values());

			if (previousCounts.size() == newCounts.size()) {

				for (int i = 0; i < previousCounts.size(); i++) {
					if (newCounts.get(i) != (previousCounts.get(i) + 1)) {
						softAssert.assertTrue(false, "Error : Click On Like Button Functionality is not working : Count is not properly Getting Updated for " + i + "th Like Button");


					}
				}

			} else {
				logger.debug("Error :Size of List having Counters Counts is not equal : Skipping the validation ");
				throw new SkipException("Error :Size of List having Counters Counts is not equal : Skipping the validation ");
			}

		} else {
			logger.error("Error : Article Having Like Button Not Found ");
			throw new SkipException("Error : Article Having Like Button Not Found  ");
		}


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
