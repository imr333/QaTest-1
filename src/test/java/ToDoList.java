import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ToDoList {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://webdriveruniversity.com";
        Configuration.browser = "Chrome";
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void afterEach() {
        sleep(6000);
        closeWebDriver();
    }

    /*метод ввода значения в поле с дальнейшим скрытием строки*/
    void inputTextAndHidePoint(String text) {
        $("input[type='text']").setValue(text).pressEnter();
        $("#plus-icon").click();
        $("input[placeholder='Add new todo']").shouldNotBe(visible);
    }

    /*думал сделать метод, где буду по тексту находить элемент и совершать с ним действие:
       void clickOnRaw(String text) {rawWithText.shouldHave(oneOfTexts(text)).click();}, но не прокатило, погуглил и пришлось делать отдельно через xpath*/

    void clickOnPoint() {
        $x("//li[text()=' Go to potion class']").click();
        $x("//li[text()=' Buy new robes']").click();
        $x("//li[text()=' Practice magic']").click();
        $x("//li[text()=' TEST']").click();
    }

    /*создал метод удаления одной строки*/
    void removePoint() {
        $(".fa-trash").hover().click();
        sleep(1000);
    }

    /*затем метод с циклом, для удаления строк, кол-во которых указано в аргументах метода*/
    void removeAllPoints(int number) {
        for (int i = 1; i <= number; i++) {
            removePoint();
        }
    }

    /*пока что тест норм не пашет. возможно потому что необходимо добавить таймаут после метода клика в методе removePoint (мб это как-то делается через clickOption*/
    @Test
    void addAndRemovePoints() {
        open("/To-Do-List/index.html");

        inputTextAndHidePoint("TEST");
        clickOnPoint();
        removeAllPoints(4);
    }
}