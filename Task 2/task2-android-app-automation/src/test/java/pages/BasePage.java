package pages;

import helper.AppLaunch;
import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

public class BasePage {

    public AndroidDriver driver = AppLaunch.getInstance().driver;
    public BasePage() throws IOException, InterruptedException {
    }
}
