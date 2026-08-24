package qa.demo.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Feature(("Форма регистрации студента обычные шаги"))
public class PracticFormTest {

    @Test
    @Story("Успешная отправка студента")
    @Owner("AVedenyapina")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("BLOKER")
    @DisplayName("Успешное заполнение формы студента")
    public  void testStudentRegistrationFormSuccessfulSubmission(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1920x1080";

        open("https://demoqa.com/automation-practice-form");
        $("input#firstName").setValue("Ivan");
        $("input#lastName").setValue("Pupochkin");
        $("input#userEmail").setValue("testik@test.te");
        $("input[value='Female']").click();
        $("input#userNumber").setValue("4444444444");
        $("input#dateOfBirthInput").click();
        $("select.react-datepicker__year-select option[value='2005']").click();
        $("select.react-datepicker__month-select").selectOption("March");
        $$(".react-datepicker__day").filterBy(Condition.text("17")).get(0).click();
        $("button#submit").shouldBe(Condition.clickable).click();

        $("div.fade.modal.show").shouldHave(text("Thanks for submitting the form"));
        $$("td").filterBy(text("Student Name")).get(0).sibling(0).shouldHave(text("Ivan Pupochkin"));

    }
}
