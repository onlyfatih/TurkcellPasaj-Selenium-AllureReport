package pages;

import driver.PageGenarator;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProductPage extends PageGenarator {
    private static final Logger LOG = LogManager.getLogger(ProductPage.class);

    @FindBy(css = ".select2-color")
    private WebElement selectColor;

    @FindBy(xpath = "//section[@id=\"product-detail\"]//span[@id=\"select2--container\"]")
    private WebElement currentColor;

    @FindBy(xpath = "(//ul[@class=\"select2-results__options\"]/li[not(contains(@aria-selected,'true'))])[1]")
    private WebElement newFirstColor;

    @FindBy(xpath = "//button[@id='p-1050019']")
    private WebElement addBasket;

    @FindBy(xpath = "//a[@id='none-login-sale-button']")
    private WebElement continueWithoutLogin;

    @FindBy(xpath = "//span[@class='a-price-val']")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='swiper-slide swiper-slide-active']//img[@alt='Samsung Galaxy A23 128 GB 4 GB Ram']")
    private WebElement productUrl;

    @FindBy(xpath = "//img[@alt='Samsung Galaxy A23 128 GB 4 GB Ram']")
    private WebElement productBasketUrl;

    @Step("Urun renginin degistirilmesi kontrol ediliyor.")
    public void verifyChangeColor() {
        try {
            wait(2);
            findScrollElementCenter(selectColor);
            WaitUntilTheElementAppears(selectColor);
            selectColor.click();

            String beforeColor = currentColor.getAttribute("title");
            WaitUntilTheElementAppears(newFirstColor);
            newFirstColor.click();

            String afterColor = currentColor.getAttribute("title");

            Assert.assertNotEquals(afterColor,beforeColor,"Color degistirilmesine ragmen degismemistir.");
        }
        catch (Exception e) {
            Assert.fail("Verifychangecolor yapılırken problem olustu");
        }
    }
    @Step("Urunu sepete ekleniyor.")
    public void addToBasket () {
        try {
            wait(2);
            findScrollElementCenter(addBasket);
            wait(2);
            WaitUntilTheElementAppears(addBasket);
            addBasket.click();
        }
        catch (Exception e) {
            Assert.fail("Sepete ekleme yapilirken hata olsutu");
        }
    }
    @Step("Giris yapmadan devam et diyerek sepete gidilir.")
    public void continueToBasket () {
        wait(2);
        //WaitUntilTheElementAppears(continueWithoutLogin);
        continueWithoutLogin.click();
    }

    @Step("Sepete eklenen urun ile sepetteki urun kontrol edilir.")
    public void verifyProduct(String productUrl,String basketUrl) {
        try {
            Assert.assertEquals(productUrl, basketUrl, "Resimler eslesmiyor.");
            return;
        }
        catch (Exception e) {
            Assert.fail("Karşılaştırma yapılırken hata verdi.");
        }
    }

    public String getProductUrl() {
        String productSrc = productUrl.getAttribute("src").substring(0,83);
        return productSrc;
    }

    public String getProductBasketUrl() {
        String productBasketSrc = productBasketUrl.getAttribute("src").substring(0,83);
        return productBasketSrc;
    }

    @Step("Urun sayfasinda urunun fiyatı kayit altina alinir.")
    public String getProductPrice() {
        String priceInProductPage = productPrice.getText();
        return priceInProductPage;
    }
}
