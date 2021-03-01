package helper;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class AppLaunch {
    public static AndroidDriver driver;
    public static AppLaunch instance;

    private AppLaunch() {
        setUp();
    }

    public static AppLaunch getInstance() throws IOException, InterruptedException {
        if (instance == null) {
            instance = new AppLaunch();
        }
        return instance;
    }

    public void setUp() {
        // Created object of DesiredCapabilities class.
        try {
            Util util = new Util();
            Properties prop = util.readPropertiesData();

            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("deviceName", prop.getProperty("deviceName"));
            cap.setCapability("platformName", prop.getProperty("platformName"));
            cap.setCapability("appPackage", prop.getProperty("appPackage"));
            cap.setCapability("appActivity", prop.getProperty("appActivity"));
            cap.setCapability("automationName", "UiAutomator1");
            cap.setCapability("autoGrantPermissions", true);
            cap.setCapability("noReset", true);
            cap.setCapability("fullReset", false);

            driver = new AndroidDriver(new URL(prop.getProperty("appiumUrl")), cap);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
