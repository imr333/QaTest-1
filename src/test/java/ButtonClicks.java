import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class ButtonClicks {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = ("https://webdriveruniversity.com");
        Configuration.browser = ("Chrome");
        Configuration.browserSize = ("1920x1080");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void testButtonClick() {
        open("/Click-Buttons/index.html");

        //Клик по кнопке №1 с помощью xpath селектора, проверка модального окна и его закрытие
        $x("//*[@id='button1']").click();
        $x("//*[@class='modal-title']").shouldHave(text("Congratulations!"));
        $x("//*[@data-dismiss='modal']").click();

        //Клик по кнопке №2 с помощью JS с дальнейшим закрытием модального окна
        SelenideElement button2 = $("#button2");
        Selenide.executeJavaScript("arguments[0].click()", button2);
        SelenideElement close = $("#myModalJSClick > .modal-dialog > .modal-content > .modal-footer > .btn");
        $(close).click();
//      Selenide.executeJavaScript("arguments[0].click()", close); //хз почему, с помощью JS кликнуть на кнопку не получается

        //Клик по кнопке №3 с предварительным наведением
        SelenideElement button3 = $("#button3");
        actions().moveToElement(button3,10,10).click().perform(); //хелп, не могу тыкнуть по кнопке... (как только не пробовал)



    }

}
