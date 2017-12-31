package com.perfecto.connect.sample;

import com.perfecto.connect.sample.retry.Retry;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PerfectoConnectMobile extends PerfectoConnectBase {

    @Test(groups = {"all", "mobile"})
    public void sample() throws Exception {
        Retry.perform(() -> {
            runAppiumTest("Android", null, null, "^[678].*");
            return (Void) null;
        }, 5, Duration.ofMinutes(10));
    }

    private Boolean runAppiumTest(String os, String deviceName, String location, String osVersion) throws Exception {
        AppiumDriver driver = null;
        try {
            driver = createAppiumDriver(os, deviceName,location, osVersion);
            String host = server.getHost();
            System.out.println("navigate to " + host + " on " + os + " device ");
            driver.get(host);
            driver.navigate().refresh();

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
            if(driver != null) {
                driver.quit();
            }
        }
    }
}
