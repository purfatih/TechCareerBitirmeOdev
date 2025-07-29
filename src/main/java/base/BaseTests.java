package base;
import Data.Data;
import io.qameta.allure.Step;
import org.testng.Assert;

public class BaseTests extends Data {
    @Step("{millis} saniye beklenir.")
    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Karşılaştırma sağlanır.")
    public void assertEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }
}
