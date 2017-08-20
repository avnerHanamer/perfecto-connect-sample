
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

public class PerfectoConnect extends PerfectoConnectBase {
    private static final String SECURITY_TOKEN = "securityToken";
    private static final String TUNNEL_ID = "tunnelId";

    private String buildNumber = System.getProperty("b");

    public PerfectoConnect() {
        super(System.getProperty("c"), System.getProperty("s"));
    }


    @Test
    public void sample() throws IOException, InterruptedException {
        String tunnelId = getTunnelId();
        AppiumDriver driver = createAppiumDriver("Android", "FA6BR0304511", tunnelId);

        try {
            for (int i = 0; i < 10; i++) {
                driver.get("http://localhost/");
                driver.navigate().refresh();
                Thread.sleep(1000);
            }

            WebElement element = driver.findElement(By.xpath("(//h1)[1]"));
            String expectedText = "Hi from Nashat!";
            Assert.assertEquals(expectedText, element.getText());

            WebElement buildNumberElement = driver.findElement(By.xpath("(//h1)[2]"));
            Assert.assertEquals(buildNumber, buildNumberElement.getText());


        } finally {
            driver.quit();
        }
    }
}
