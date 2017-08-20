
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

import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_NAME;

public class PerfectoConnectBase {
    private static final String SECURITY_TOKEN = "securityToken";
    private static final String TUNNEL_ID = "tunnelId";
    private String cloudURL;
    private String securityToken;

    public PerfectoConnectBase(String cloudURL, String securityToken) {
        this.cloudURL = cloudURL;
        this.securityToken = securityToken;
    }

    protected AppiumDriver createAppiumDriver(String os, String deviceId, String tunnelId) throws MalformedURLException {
        String baseURL = "http://" + cloudURL;
        URL url = new URL(baseURL + "/nexperience/perfectomobile/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(SECURITY_TOKEN, securityToken);
        capabilities.setCapability(PLATFORM_NAME, os);
        capabilities.setCapability(DEVICE_NAME, deviceId);
        capabilities.setCapability(TUNNEL_ID, tunnelId);
        AppiumDriver<WebElement> driver = os.equalsIgnoreCase("Android") ? new AndroidDriver<>(url , capabilities) : new IOSDriver<>(url, capabilities);

        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return driver;
    }

    protected String getTunnelId() throws IOException {
        URL url = this.getClass().getResource("/tunnelId");
        File tunnelIdFile = new File(url.getFile());

        String tunnelId;
        try(BufferedReader reader = new BufferedReader(new FileReader(tunnelIdFile))){
            tunnelId = reader.readLine();
        }

        return tunnelId;
    }
}
