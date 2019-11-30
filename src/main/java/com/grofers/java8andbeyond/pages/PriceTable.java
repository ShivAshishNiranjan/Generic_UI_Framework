package com.grofers.java8andbeyond.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 01/11/19
 */
public class PriceTable {
    Logger logger = LoggerFactory.getLogger(PriceTable.class);

    WebDriver driver;
    @FindBy(css = "table#prods tr")
    private List<WebElement> rows;
    @FindBy(id = "result")
    private WebElement verifyButton;
    @FindBy(id = "status")
    private WebElement status;

    public PriceTable(WebDriver driver) {
        this.driver = driver;
    /*    logger.info("Initializing the Web Element/Elements");
        PageFactory.initElements(driver, this);
        logger.info("Done: Initializing the Web Element/Elements");*/
    }

    public void goTo() {
        driver.get("https://vins-udemy.s3.amazonaws.com/java/html/java8-stream-table-price.html");
        PageFactory.initElements(driver, this);
    }

    public void selectMinPriceRow() {
        Optional<List<WebElement>> rowHavingMinRow = rows.stream()
                .skip(1)
                .map(tr -> tr.findElements(By.tagName("td")))
                .min(Comparator.comparing(tdlist -> Integer.parseInt(tdlist.get(2).getText())));
        if (rowHavingMinRow.isPresent()) {
            List<WebElement> cells = rowHavingMinRow.get();
            cells.get(3).findElement(By.tagName("input")).click();
        }
        this.verifyButton.click();
    }

    public String getStatus() {
        return this.status.getText().trim();
    }


}
