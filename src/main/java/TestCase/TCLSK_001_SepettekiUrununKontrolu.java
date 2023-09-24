package TestCase;

import base.TestBase;
import config.ConfigReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.qameta.allure.SeverityLevel.NORMAL;

public class TCLSK_001_SepettekiUrununKontrolu extends TestBase {
    protected final String productURL = ConfigReader.readDataInfo().getProperty("productURL");
    public TCLSK_001_SepettekiUrununKontrolu() throws IOException {
    }

    @Test(description = "Urun dogrulama.")
    @Severity(NORMAL)
    @Description("Sepetteki urunun dogruluğunu kontrol etme")
    @Story("Herhangi bir ürünü sepete ekliyoruz ve giriş yapmadan devm ederek sepete gidiyoruz. Sepetteki ürün ile eklediğimiz ürünün doğruluğunu karşılaştırıyoruz.")
    public void TCLSK_001_SepettekiUrununKontrolu() {
        BasicConfigurator.configure();
        homePage().navigate(productURL);
        String productImg = productPage().getProductUrl();
        productPage().addToBasket();
        productPage().continueToBasket();
        basketPage().popUpIfExist(basketPage().advertPopUpInBasket);
        String productBasketImg = productPage().getProductBasketUrl();
        productPage().verifyProduct(productImg,productBasketImg);
    }
}
