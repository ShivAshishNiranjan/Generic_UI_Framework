package com.grofers.utility;


import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class ConfigReader {

    private final static Logger logger = LoggerFactory.getLogger(ConfigReader.class);


    public static String getValueFromConfigFile(String filePath, String fileName, String sectionName, String propertyName) {
        try {
            String columnName = null;
            propertyName = propertyName.trim();
            Configurations configs = new Configurations();
            INIConfiguration config = configs.ini(filePath + "//" + fileName);

            if (sectionName != null && config.getProperty(sectionName.toLowerCase() + "." + propertyName.toLowerCase()) != null)
                columnName = config.getProperty(sectionName.toLowerCase() + "." + propertyName.toLowerCase()).toString();

            else if (config.getProperty(propertyName.toLowerCase()) != null)
                columnName = config.getProperty(propertyName.toLowerCase()).toString();

            return columnName;
        } catch (Exception e) {
            Assert.fail("Error: Reading in Config File: " + fileName + ", Section Name: " + sectionName
                    + " ,Key Name: " + propertyName + ", Error Message: " + e.getMessage());
            return null;
        }
    }


    public static Map<String, String> getAllConstantProperties(String filePath, String fileName, String sectionName) throws ConfigurationException {
        Map<String, String> allDefaultProperties = new LinkedHashMap<String, String>();
        Configurations configs = new Configurations();
        INIConfiguration config = configs.ini(filePath + "//" + fileName);
        Iterator<String> keys = null;
        if (sectionName == null) {
            keys = config.getKeys();
            while (keys.hasNext()) {
                String nextKey = keys.next();
                // Split keyname because they are section.keyname
                String keyName = nextKey;
                String keyValue = config.getString(nextKey);
                allDefaultProperties.put(keyName.toLowerCase(), keyValue);
            }
        } else {
            keys = config.getKeys(sectionName.toLowerCase());
            while (keys.hasNext()) {
                String nextKey = keys.next();
                // Split keyname because they are section.keyname
                String keyName = nextKey.split("\\.")[1];
                String keyValue = config.getString(nextKey);
                allDefaultProperties.put(keyName.toLowerCase(), keyValue);
            }
        }
        return allDefaultProperties;
    }


    public static Map<String, String> getAllDefaultProperties(String filePath, String fileName) throws ConfigurationException {
        return ConfigReader.getAllConstantProperties(filePath, fileName, "default");
    }


}
