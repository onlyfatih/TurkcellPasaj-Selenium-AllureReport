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

public class TCLRD_001_UrunRenginiDegistirme extends TestBase {
    protected final String productURL = ConfigReader.readDataInfo().getProperty("productURL");
    public TCLRD_001_UrunRenginiDegistirme() throws IOException {
    }

    @Test(description = "Degisikligi kontrol etme.")
    @Severity(NORMAL)
    @Description("Urun renginin degisikliğini kontrol etme.")
    @Story("Urunun olduğu URL'e git. Urunun rengini değistir ve degistigini kontrol et.")
    public void TCLRD_001_UrunRenginiDegistirme() {
        BasicConfigurator.configure();
        homePage().navigate(productURL);
        productPage().verifyChangeColor();
    }
}

