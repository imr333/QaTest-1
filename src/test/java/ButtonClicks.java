import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ButtonClicks {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = ("https://webdriveruniversity.com");
        Configuration.browser = ("Chrome");
        Configuration.browserSize = ("1920x1080");
//        Configuration.holdBrowserOpen = true;
    }

    @Test
    void testButtonClick() {
        open("/Click-Buttons/index.html");

        SelenideElement webClickElement = $("");
        SelenideElement jsClickElement = $("");
        SelenideElement movieAndClick = $("");

    }

}
