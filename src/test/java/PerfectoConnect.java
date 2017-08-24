
import com.perfecto.connect.sample.server.server.LocalServer;
import conf.JenkinsConfiguration;
import io.appium.java_client.AppiumDriver;
import org.apache.xpath.operations.Bool;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class PerfectoConnect extends PerfectoConnectBase {

    private static LocalServer server;
    private static String message;

    public PerfectoConnect() {
        super(new JenkinsConfiguration());
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        server = new LocalServer();
        message = UUID.randomUUID().toString();
        server.start(message);
    }

    @Test
    public void sample() throws IOException, InterruptedException, ExecutionException {
        boolean status = runAppiumTest("Android", null,"NA-US-BOS","^[678].*");

        Assert.assertEquals(true, status);
    }

    private Boolean runAppiumTest(String os, String deviceName,String location, String osVersion) {
        AppiumDriver driver = null;
        try {
            driver = createAppiumDriver(os, deviceName,location, osVersion);
            String host = server.getHost();
            System.out.println("navigate to " + host + " on " + os + " device " + deviceName);
            driver.get(host);
            driver.navigate().refresh();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement element = driver.findElement(By.xpath("/html/body/pre"));
            Assert.assertEquals(message, element.getText());
            return true;
        } catch (MalformedURLException e) {
            return false;
        } finally {
            if(driver != null) {
                driver.quit();
            }
        }
    }


    @AfterClass
    public static void afterClass() throws Exception{
        server.stop();
    }
}
