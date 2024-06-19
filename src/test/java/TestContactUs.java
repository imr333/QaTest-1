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

    //В поля класса вынесены элементы селенида, чтобы можно было обращаться к ним из любых методов
    SelenideElement firstName = $("input[name='first_name']");
    SelenideElement lastName = $("input[name='last_name']");
    SelenideElement email = $("input[name='email']");
    SelenideElement comments = $("textarea[placeholder='Comments']");

    void fillForm() {
        //Заполнение формы
        firstName.setValue("Suggar");
        lastName.setValue("Daddy");
        email.setValue("keks@mail.com");
        comments.setValue("Gena - crocodile");
    }

    @Test
    void testForm() {

        open("/Contact-Us/contactus.html");

        //Проверка названия формы для заполнения
        $(".section_header").shouldHave(text("CONTACT US"));

        //Ресет полей
        $("#form_buttons > input[type='reset']").click();

        //Убедиться что после ресета полей - значния полей пустые
        firstName.shouldNotHave(text("Suggar"));
        lastName.shouldNotHave(text("Daddy"));
        email.shouldNotHave(text("keks@mail.com"));
        comments.shouldNotHave(text("Gena - crocodile"));

        //Повторное заполнение формы
        fillForm();

        //Подтвердить заполнение
        $("#form_buttons > input[type='submit']").click();

        //Переход на страницу подтверждения
        $("#contact_reply").shouldHave(text("Thank You for your Message!"));

    }

    @AfterAll
    static void afterAll() {
        sleep(5000);
    }
}