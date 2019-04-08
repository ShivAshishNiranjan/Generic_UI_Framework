package com.grofers.pages;

import com.grofers.test.ConfigureConstant;
import com.grofers.utility.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : shiv.ashish@grofers.com
 * file created on 2019-04-09
 */
public class InstagramLoginPage {

    Logger logger = LoggerFactory.getLogger(InstagramLoginPage.class);

    WebDriver driver;
    String baseUrl;
    String loginUrl;
    String username;
    String password;
    By usernameInputElement = By.cssSelector("input[name=username]");
    By passwordInputElement = By.cssSelector("input[name=password]");
    By submitElement = By.cssSelector("button[type=submit]");

    public InstagramLoginPage(WebDriver driver) {
        this.driver = driver;
        String configFilePath = ConfigureConstant.getConfigFilesValue("instagramconfigfilepath");
        String configFileName = ConfigureConstant.getConfigFilesValue("instagramfilename");

        baseUrl = ConfigReader.getValueFromConfigFile(configFilePath, configFileName, "default", "baseurl");
        loginUrl = ConfigReader.getValueFromConfigFile(configFilePath, configFileName, "default", "loginurl");
        username = ConfigReader.getValueFromConfigFile(configFilePath, configFileName, "default", "username");
        password = ConfigReader.getValueFromConfigFile(configFilePath, configFileName, "default", "password");

    }

    public By getSubmitElement() {
        return submitElement;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public By getUsernameInputElement() {
        return usernameInputElement;
    }

    public By getPasswordInputElement() {
        return passwordInputElement;
    }


}
