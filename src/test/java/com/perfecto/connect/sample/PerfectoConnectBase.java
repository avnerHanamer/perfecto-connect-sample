package com.perfecto.connect.sample;

import com.perfecto.connect.sample.conf.IConfiguration;
import com.perfecto.connect.sample.conf.JenkinsConfiguration;
import com.perfecto.connect.sample.server.LocalServer;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import static io.appium.java_client.remote.MobileCapabilityType.*;

public class PerfectoConnectBase {
    private static final String SECURITY_TOKEN = "securityToken";
    private static final String TUNNEL_ID = "tunnelId";
    protected String message;
    protected LocalServer server;
    protected IConfiguration config;

    public PerfectoConnectBase() {
        this.config = new JenkinsConfiguration();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        try {
            server = new LocalServer();
            message = UUID.randomUUID().toString();
            System.out.println("Starting local server");
            server.start(message);
        } catch (Exception e) {
            System.out.println("Can't start local server");
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        try {
            System.out.println("Stopping local server");
            server.stop();
        } catch (Exception e) {
            System.out.println("Can't stop local server");
        }
    }

    protected AppiumDriver createAppiumDriver(String os, String deviceId,String location, String osVersion) throws MalformedURLException {
        URL url = new URL(config.getCloudURL() + "/nexperience/perfectomobile/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(SECURITY_TOKEN, config.getOfflineToken());
        capabilities.setCapability(PLATFORM_NAME, os);

        if (deviceId!=null) capabilities.setCapability(DEVICE_NAME, deviceId);
        if (location!=null) capabilities.setCapability("location", location);
        if (osVersion!=null) capabilities.setCapability(PLATFORM_VERSION, osVersion);

        capabilities.setCapability(TUNNEL_ID, config.getTunnelId());
        AppiumDriver<WebElement> driver = os.equalsIgnoreCase("Android") ? new AndroidDriver<>(url , capabilities) : new IOSDriver<>(url, capabilities);

        return driver;
    }

    protected AppiumDriver createAppiumDriver(String os) throws MalformedURLException {
        URL url = new URL(config.getCloudURL() + "/nexperience/perfectomobile/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(SECURITY_TOKEN, config.getOfflineToken());
        capabilities.setCapability(PLATFORM_NAME, os);
        capabilities.setCapability(TUNNEL_ID, config.getTunnelId());
        AppiumDriver<WebElement> driver = os.equalsIgnoreCase("Android") ? new AndroidDriver<>(url , capabilities) : new IOSDriver<>(url, capabilities);

        return driver;
    }

    protected RemoteWebDriver createSeleniumDriver(String os, String osVersion, String browserName) throws MalformedURLException {
        String location = System.getProperty("location", "");
        if (location == null || location.trim() == "" || location.contains("US")){
            location = "US East";
        }
        else if (location.contains("EU")){
            location = "EU Frankfurt";
        }
        else{
            location = "AP Sydney";
        }
        URL url = new URL(config.getCloudURL() + "/nexperience/perfectomobile/wd/hub/fast");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(SECURITY_TOKEN, config.getOfflineToken());
        capabilities.setCapability(PLATFORM_NAME, os);
        capabilities.setCapability(PLATFORM_VERSION, osVersion);
        capabilities.setCapability(BROWSER_NAME, browserName);
        capabilities.setCapability("browserVersion", "latest");
        capabilities.setCapability("location", location);

        capabilities.setCapability(TUNNEL_ID, config.getTunnelId());
        RemoteWebDriver driver = new RemoteWebDriver(url, capabilities);

        return driver;
    }
}
