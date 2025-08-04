package base;

import Data.Data;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseMethods <T> extends Data {

    public static WebDriver driver;

    @BeforeMethod
    public void OpenBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(registerUrl);
    }

    @AfterMethod
    public void CloseBrowser() {
        driver.quit();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("{millis} saniye beklenir.")
    public T sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (T) this;
    }

    @Step("Karşılaştırma sağlanır.")
    public BaseMethods assertEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected);
        return this;
    }
}

