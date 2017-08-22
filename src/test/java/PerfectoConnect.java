
import com.perfecto.connect.sample.server.server.LocalServer;
import conf.IConfiguration;
import conf.LocalConfiguration;
import io.appium.java_client.AppiumDriver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.*;

public class PerfectoConnect extends PerfectoConnectBase {

    private static LocalServer server;


    public PerfectoConnect() {
        super(new LocalConfiguration());
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        server = new LocalServer();
        server.start(LocalConfiguration.getMessage());
    }

    @Test
    public void sample() throws IOException, InterruptedException {
        AppiumDriver driver = createAppiumDriver("Android", "DBBABA96");

        try {
            driver.get("http://localhost:" + LocalServer.getPort());
            driver.navigate().refresh();

            WebElement element = driver.findElement(By.xpath("/html/body/pre"));
            Assert.assertEquals(LocalConfiguration.getMessage(), element.getText());

        } finally {
            driver.quit();
        }
    }

    @AfterClass
    public static void afterClass() throws Exception{
        server.stop();
    }
}
