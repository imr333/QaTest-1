import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class ButtonClicks {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = ("https://webdriveruniversity.com");
        Configuration.browser = ("Chrome");
        Configuration.browserSize = ("1920x1080");
    }

    /*по-хорошему нужно использовать две переменные на закрытие модалки и проверку хедера,
    но задача попробовать несколько вариантов взаимодействия с элементами*/
    private final SelenideElement closeBtn = $(".fade.in.show .btn-default");
    private final SelenideElement modalTitle = $(".modal.fade.in.show .modal-header");


    @BeforeEach
    public void openBrowser() {
        open("/Click-Buttons/index.html");

    }

    @Test
    @DisplayName("WebElement Click")
    public void clickViaWebDriver() {
        /*Клик по кнопке №1 с помощью xpath селектора, проверка модального окна и его закрытие*/
        $x("//*[@id='button1']").shouldBe(visible).click();
        $x("//*[@class='modal-title']").shouldHave(text("Congratulations!"));
        $x("//*[@data-dismiss='modal']").shouldBe(visible).click();
    }

    @Test
    @DisplayName("JavaScript Click")
    public void clickViaJS() {
        /*Клик по кнопке №2 с помощью JS с дальнейшим закрытием модального окна*/
        SelenideElement button2 = $("#button2");
        button2.click(ClickOptions.usingJavaScript()); // Selenide.executeJavaScript("arguments[0].click()", button2);
        modalTitle.shouldHave(text("Well I think it is....."));
        SelenideElement close = $("#myModalJSClick .modal-footer > .btn");
        close.click(ClickOptions.usingJavaScript()); // Selenide.executeJavaScript("arguments[0].click()", close);
    }

    @Test
    @DisplayName("Action Move & Click")
    public void clickViaActions() {
        /*Клик по кнопке №3 с помощью метода actions()*/
        SelenideElement button3 = $("#button3");
//        button3.click(ClickOptions.usingJavaScript());
        actions().moveToElement(button3).click(button3).perform();
        modalTitle.shouldHave(text("Action Move & Click"));
        actions().moveToElement(closeBtn).click(closeBtn).perform();
//        closeBtn.shouldBe(visible).click(ClickOptions.usingJavaScript());
    }

    @AfterEach
    public void tearDown() {
        sleep(3000);
        closeWebDriver();
    }

}
