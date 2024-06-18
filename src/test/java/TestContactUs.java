import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TestContactUs {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = ("https://webdriveruniversity.com");
        Configuration.browser = ("Chrome");
        Configuration.browserSize = ("1920x1080");
//        Configuration.holdBrowserOpen = true;

    }

    @Test
    void testForm() {
        open("/Contact-Us/contactus.html");

        SelenideElement FirstName = $("input[name*='first_name']");
        SelenideElement LastName = $("input[name*='last_name']");
        SelenideElement Email = $("input[name*='email']");
        SelenideElement Comments = $("textarea[placeholder='Comments']");

        //Проверка названия формы для заполнения
        $(".section_header").shouldHave(text("CONTACT US"));

        //Заполнение формы
        FirstName.setValue("Suggar");
        LastName.setValue("Daddy");
        Email.setValue("keks@mail.com");
        Comments.setValue("Gena - crocodile");

        //Ресет полей
        $("#form_buttons > input[type*='reset']").click();

        //Убедиться что после ресета полей - значния полей пустые
        FirstName.shouldNotHave(text("Suggar"));
        LastName.shouldNotHave(text("Daddy"));
        Email.shouldNotHave(text("keks@mail.com"));
        Comments.shouldNotHave(text("Gena - crocodile"));

        //Повторное заполнение формы
        FirstName.setValue("Suggar");
        LastName.setValue("Daddy");
        Email.setValue("keks@mail.com");
        Comments.setValue("Gena - crocodile");

        //Подтвердить заполнение
        $("#form_buttons > input[type*='submit']").click();

        //Переход на страницу подтверждения
        $("#contact_reply").shouldHave(text("Thank You for your Message!"));
    }

    @AfterAll
    static void afterAll() {
        sleep(5000);
    }
}