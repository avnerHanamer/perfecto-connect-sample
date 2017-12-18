import com.perfecto.connect.sample.server.LocalServer;
import io.appium.java_client.AppiumDriver;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class PerfectoConnectMobile extends PerfectoConnectBase {

    private static LocalServer server;
    private static String message;

    @BeforeClass
    public static void beforeClass() throws Exception {
        server = new LocalServer();
        message = UUID.randomUUID().toString();
        server.start(message);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.stop();
    }

    @Test
    public void sample() throws IOException, InterruptedException, ExecutionException {
        boolean status = runAppiumTest("Android", null, "NA-US-BOS", "^[678].*");

        Assert.assertEquals(true, status);
    }

    private Boolean runAppiumTest(String os, String deviceName,String location, String osVersion) {
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
        } catch (Exception e) {
            System.out.println("e = " + e);
            return false;
        } finally {
            if(driver != null) {
                driver.quit();
            }
        }
    }
}
