package com.grofers.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class InitializeDrivers {

    private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

    public static WebDriver getDriver(String browserName) {

        WebDriver driver = null;
        setGekoDriverBasedOnOS(browserName);
        if (browserName.toLowerCase().contains("chrome")) {
            driver = drivers.get("Chrome");
            if (driver == null) {
                driver = new ChromeDriver();
                drivers.put("Chrome", driver);
            }

        } else if (browserName.toLowerCase().contains("firefox")) {

            driver = drivers.get("Firefox");
            if (driver == null) {
                driver = new FirefoxDriver();
                drivers.put("Firefox", driver);
            }

        }

        return driver;
    }


    static void setGekoDriverBasedOnOS(String browserName) {

        String os = System.getProperty("os.name").toLowerCase();
        String driverName = "geckodriver"; //default value
        String propertyName = "webdriver.gecko.driver";//default value
        String commonPath = "/src/main/resources/GekoDriver/";

        if (browserName.contains("chrome")) {
            driverName = "chromedriver";
            propertyName = "webdriver.chrome.driver";
        }


        if (os.contains("mac")) {
            System.setProperty(propertyName, System.getProperty("user.dir") + commonPath + browserName + "/Mac/" + driverName);
        }
        if (os.contains("linux")) {
            System.setProperty(propertyName, System.getProperty("user.dir") + commonPath + browserName + "/Linux/" + driverName);
        }
        if (os.contains("windows")) {
            System.setProperty(propertyName, System.getProperty("user.dir") + commonPath + browserName + "/Windows/" + driverName + ".exe");
        }


    }

    public static void closeAllDriver() {
        for (String key : drivers.keySet()) {
            drivers.get(key).close();
            drivers.get(key).quit();
        }
    }


}
