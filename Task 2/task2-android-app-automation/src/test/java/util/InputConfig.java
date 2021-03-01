package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class InputConfig {

    InputStream inputStream;
    String deviceName = null;
    String platformName = null;
    String appPackage = null;
    String appActivity = null;
    String url = null;

    public static Properties prop;

    public String[] getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property value and print it out
            deviceName = prop.getProperty("deviceName");
            //GiveMerchantMobileForReg=prop.getProperty("GiveMerchantMobileForReg");
            platformName = prop.getProperty("platformName");
            appPackage = prop.getProperty("appPackage");
            appActivity = prop.getProperty("appActivity");
            url = prop.getProperty("appiumUrl");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return new String[]{deviceName, platformName, appPackage, appActivity, url};
    }

}
