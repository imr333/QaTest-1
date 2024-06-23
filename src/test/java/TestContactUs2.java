import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestContactUs2 {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://webdriveruniversity.com";
        Configuration.browser = ("Chrome");
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void AfterEach() {
        sleep(1000);
        closeWebDriver();
    }

    /*значения для метода селенида seValue*/
    String setFirstName;
    String setLastName;
    String setEmailAdress;
    String setComments;

    /*переменные - локаторы*/
    SelenideElement inputFirstName = $("#contact_form > [placeholder='First Name']");
    SelenideElement inputLastName = $("#contact_form > [placeholder='Last Name']");
    SelenideElement inputEmailAdress = $("#contact_form > [placeholder='Email Address']");
    SelenideElement inputComments = $("#contact_form > [placeholder='Comments']");

    /*метод на заполнение полей, включающий в себя значения полей в виде аргументов*/
    public void fillFormValues(String setFirstName, String setLastName, String setEmailAdress, String setComments) {

        this.setFirstName = setFirstName;
        this.setLastName = setLastName;
        this.setEmailAdress = setEmailAdress;
        this.setComments = setComments;

        inputFirstName.setValue(setFirstName);
        inputLastName.setValue(setLastName);
        inputEmailAdress.setValue(setEmailAdress);
        inputComments.setValue(setComments);
    }

    @Test
    @DisplayName("CONTACT US")
    public void fillForm() {

        open("/Contact-Us/contactus.html");

        /*вызов метода заполнения полей с указанием аргументов*/
        fillFormValues("Lol", "Kek", "Chebureck@mail.ru", "Test");

        /*ресет всех полей с повторным заполнением полей*/
        $("#form_buttons > [value='RESET']").click();
        fillFormValues("Lol", "Kek", "Chebureck@mail.ru", "Test");

        /*подтверждение заполнения полей*/
        $("#form_buttons > [value='SUBMIT']").click();
        $("#contact_reply").shouldHave(text("Thank You for your Message!"));
    }


}



