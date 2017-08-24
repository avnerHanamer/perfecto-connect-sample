
import conf.IConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;

public class PerfectoConnectBase {
    private static final String SECURITY_TOKEN = "securityToken";
    private static final String TUNNEL_ID = "tunnelId";
    protected IConfiguration config;

    public PerfectoConnectBase(IConfiguration config) {
        this.config = config;
    }

    protected AppiumDriver createAppiumDriver(String os, String deviceId,String location, String osVersion) throws MalformedURLException {
        String baseURL = "http://" + config.getCloudURL();
        URL url = new URL(baseURL + "/nexperience/perfectomobile/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(SECURITY_TOKEN, config.getOfflineToken());
        capabilities.setCapability(PLATFORM_NAME, os);

        if (deviceId!=null) capabilities.setCapability(DEVICE_NAME, deviceId);
        if (location!=null) capabilities.setCapability("location", location);
        if (osVersion!=null) capabilities.setCapability(PLATFORM_VERSION, osVersion);

        capabilities.setCapability(TUNNEL_ID, config.getTunnelId());
        AppiumDriver<WebElement> driver = os.equalsIgnoreCase("Android") ? new AndroidDriver<>(url , capabilities) : new IOSDriver<>(url, capabilities);

        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return driver;
    }

    protected AppiumDriver createAppiumDriver(String os) throws MalformedURLException {
        String baseURL = "http://" + config.getCloudURL();
        URL url = new URL(baseURL + "/nexperience/perfectomobile/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(SECURITY_TOKEN, config.getOfflineToken());
        capabilities.setCapability(PLATFORM_NAME, os);
        capabilities.setCapability(TUNNEL_ID, config.getTunnelId());
        AppiumDriver<WebElement> driver = os.equalsIgnoreCase("Android") ? new AndroidDriver<>(url , capabilities) : new IOSDriver<>(url, capabilities);

        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return driver;
    }
}
