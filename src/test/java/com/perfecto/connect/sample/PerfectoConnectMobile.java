package com.perfecto.connect.sample;

import com.perfecto.connect.sample.retry.Retry;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PerfectoConnectMobile extends PerfectoConnectBase {

    private static final int RETRIES = 7;

    @Test(groups = {"all", "mobile", "Android"})
    public void sampleAndroid() throws Exception {
        Retry.perform(() -> {
            runAppiumTest("Android", null, null, ".*");
            return (Void) null;
        }, RETRIES, Duration.ofSeconds(10));
    }

    @Test(groups = {"all", "mobile", "IOS"})
    public void sampleIos() throws Exception {
        Retry.perform(() -> {
            runAppiumTest("ios", null, null, ".*");
            return (Void) null;
        }, RETRIES, Duration.ofSeconds(10));
    }

    private Boolean runAppiumTest(String os, String deviceName, String location, String osVersion) throws Exception {
        AppiumDriver driver = null;
        try {
            driver = createAppiumDriver(os, deviceName, location, osVersion);
            String host = server.getHost();
            Object name = driver.getCapabilities().getCapability("deviceName");
            System.out.println("navigate to " + host + " on " + os + " device " + name);
            driver.get(host);
//            driver.navigate().refresh();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement element = driver.findElement(By.xpath("/html/body/pre"));
            Assert.assertEquals(message, element.getText());

//            WebElement element = driver.findElement(By.xpath("/html/body/h1"));
//            Assert.assertEquals("Hi from Nashat", element.getText());
            return true;
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
