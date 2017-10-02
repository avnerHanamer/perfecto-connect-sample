import com.perfecto.connect.sample.server.server.LocalServer;
import conf.JenkinsConfiguration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class PerfectoConnectWeb extends PerfectoConnectBase {

    private static LocalServer server;
    private static String message;

    public PerfectoConnectWeb() {
        super(new JenkinsConfiguration());
    }

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

//    @Test
    public void sample() throws IOException, InterruptedException, ExecutionException {
        boolean status = runSeleniumTest("Windows", "10","Chrome");

        Assert.assertEquals(true, status);
    }

    private Boolean runSeleniumTest(String os, String osVersion, String browserName) {
        RemoteWebDriver driver = null;
        try {
            driver = createSeleniumDriver(os, osVersion, browserName);
            String host = server.getHost();
            System.out.println("navigate to " + host);
            driver.get(host);

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
}
