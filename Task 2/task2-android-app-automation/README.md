Android App Automation || POM Structure || Singleton design pattern || Java || TestNG || Allure

# Prerequisites
* JDK `8` or higher
* Gradle use `4.0.1`
* Appium `V1.8.0` or higher

### How to run test and check the test report
1. Start Appium server
2. Connect your device with your machine thru USB cable
3. After cloning the project, go to the directory `".\task2-android-app-automation`
4. Change needed configuration in project directory `".\task2-android-app-automation\src\test\resources\config.properties`
4. Build the automation project with gradle.

### Building with Gradle
```sh
$ gradle clean test
```

### Building report with Allure

```sh
$ gradle allureserve
```
For first time report generation with allure, Need to run in the following command:
```sh
$ gradle allurereport allureserve
```
##### NB. 
If you want to share the allure report link to others, make sure that your PC is turned on. Also you and the shared user must be in same network
In the allure report, please go to suites menu where you can see the test report details
