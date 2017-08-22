
import com.perfecto.connect.sample.server.server.LocalServer;
import conf.IConfiguration;
import conf.JenkinsConfiguration;
import conf.LocalConfiguration;
import io.appium.java_client.AppiumDriver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.UUID;

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
    public void sample() throws IOException, InterruptedException {
        AppiumDriver driver = createAppiumDriver("Android", "DBBABA96");

        try {
            driver.get(server.getHost());
            driver.navigate().refresh();

            WebElement element = driver.findElement(By.xpath("/html/body/pre"));
            Assert.assertEquals(message, element.getText());

        } finally {
            driver.quit();
        }
    }

    @AfterClass
    public static void afterClass() throws Exception{
        server.stop();
    }
}
