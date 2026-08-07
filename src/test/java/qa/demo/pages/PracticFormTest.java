package qa.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;
import static com.codeborne.selenide.impl.Html.text;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

@Feature(("Student Registration Form"))
public class PracticFormTest {

    @Test
    @Story("Successful submission")
    @Owner("AVedenyapina")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("BLOKER")
    @DisplayName("Успешное заполнение формы студента")
    public  void testStudentRegistrationFormSuccessfulSubmission(){
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
