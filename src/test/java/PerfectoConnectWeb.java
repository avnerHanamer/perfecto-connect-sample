import com.perfecto.connect.sample.retry.Retry;
import com.perfecto.connect.sample.server.LocalServer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class PerfectoConnectWeb extends PerfectoConnectBase {

    private static LocalServer server;
    private static String message;

    @BeforeClass
    public void beforeClass() throws Exception {
        server = new LocalServer();
        message = UUID.randomUUID().toString();
        server.start(message);
    }

    @AfterClass
    public void afterClass() throws Exception {
        server.stop();
    }

    @Test(groups = {"all", "web"})
    public void sample() throws IOException, InterruptedException, ExecutionException {
        Retry.perform(() -> {
            runSeleniumTest("Windows", "10", "Chrome");
            return (Void) null;
        }, 5, Duration.ofSeconds(2));
    }

    private void runSeleniumTest(String os, String osVersion, String browserName) throws MalformedURLException {
        RemoteWebDriver driver = null;
        try {
            driver = createSeleniumDriver(os, osVersion, browserName);
            String host = server.getHost();
            System.out.println("navigate to " + host);
            driver.get(host);

            WebElement element = driver.findElement(By.xpath("/html/body/pre"));
            Assert.assertEquals(message, element.getText());

            String reportURL = (String) driver.getCapabilities().getCapability("testGridReportUrl");
            System.out.println("Report URL: " + reportURL);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
