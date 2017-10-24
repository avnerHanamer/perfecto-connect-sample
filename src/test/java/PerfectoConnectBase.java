
import conf.IConfiguration;
import conf.JenkinsConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;

public class PerfectoConnectBase {
    private static final String SECURITY_TOKEN = "securityToken";
    private static final String TUNNEL_ID = "tunnelId";
    protected IConfiguration config;

    public PerfectoConnectBase() {
        this.config = new JenkinsConfiguration();
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
        URL url = new URL(config.getCloudURL() + "/nexperience/perfectomobile/wd/hub/fast");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(SECURITY_TOKEN, config.getOfflineToken());
        capabilities.setCapability(PLATFORM_NAME, os);
        capabilities.setCapability(PLATFORM_VERSION, osVersion);
        capabilities.setCapability(BROWSER_NAME, browserName);
        capabilities.setCapability("browserVersion", "latest");

        capabilities.setCapability(TUNNEL_ID, config.getTunnelId());
        RemoteWebDriver driver = new RemoteWebDriver(url, capabilities);

        return driver;
    }
}
