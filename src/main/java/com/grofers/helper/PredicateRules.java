package com.grofers.helper;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 22/10/19
 */
public class PredicateRules {
    private static final Predicate<WebElement> isBlank = e -> e.getText().trim().length() == 0;

    public static List<Predicate> getRules() {
        List<Predicate> rules = new ArrayList<>();
        rules.add(isBlank);
        return rules;
    }


}
