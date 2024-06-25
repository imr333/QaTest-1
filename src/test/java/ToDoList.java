import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ToDoList {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://webdriveruniversity.com";
        Configuration.browser = "Chrome";
        Configuration.browserSize = "1920x1080";
    }

    @AfterAll /*указан именно метод AfterAll, вместо AfterEach, поскольку тест всего один, при этом он должен быть static*/
    static void afterAll() {
        sleep(6000);
        closeWebDriver();
    }

    /*метод ввода значения в поле с дальнейшим скрытием строки*/
    public void inputTextAndHide(String text) {
        $("input[type='text']").setValue(text).pressEnter();
        $("#plus-icon").click();
        $("input[placeholder='Add new todo']").shouldNotBe(visible);
    }

    /*думал сделать метод, где буду по тексту находить элемент и совершать с ним действие:
       void clickOnPoint(String text) {rawWithText.shouldHave(oneOfTexts(text)).click();}, но не прокатило, погуглил и пришлось делать отдельно через xpath*/

    public void clickOnPoint(String text) {
        /*выбран наиоблее удобный вариант клика по тегу li, нежели чем xpath*/
        $(byText(text)).click();
    /*  $x("//li[text()=' Go to potion class']").click();
        $x("//li[text()=' Buy new robes']").click();
        $x("//li[text()=' Practice magic']").click();
        $x("//li[text()=' TEST']").click(); */
    }

    /*создал метод удаления одной строки*/
    public void removePoint(String textValue) {
        /* изначально сделал метод без проверки на текст элемента, было решено его видоизменить и добавить проверку
        $(".fa-trash").hover().click();  sleep(1000); */
        $(byText(textValue)).hover();
        $(".fa-trash").click();
        $(byText(textValue)).shouldNotBe(visible);
    }

    /*затем метод с циклом, для удаления строк, кол-во которых указано в аргументах метода
    но метод оказался неактуальным, т.к. правильнее выполнить удаление строк с помощью списка.
    void removeAllPoints(int number) {
        for (int i = 1; i <= number; i++) {removePoint();}} */

    /*метод удаления всех строк с помощью коллекции*/
    public void removeAllPoints() {
        $$("#container > ul > li").forEach( element -> {
            $(".fa-trash").hover().click();
            sleep(1000);
        });

        $$("#container > ul > li").shouldHave(size(0));
    }


    @Test
    void addAndRemovePoints() {
        open("/To-Do-List/index.html");

        inputTextAndHide("TEST");
        clickOnPoint("Go to potion class");
        clickOnPoint("Buy new robes");
        clickOnPoint("Practice magic");
        clickOnPoint("TEST");
        removePoint("Go to potion class");
        removeAllPoints();
    }
}