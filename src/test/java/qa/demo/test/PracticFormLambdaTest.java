package qa.demo.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PracticFormLambdaTest {

    @Test
    @Story("Successful submission")
    @Owner("AVedenyapina")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("BLOKER")
    @DisplayName("Успешное заполнение формы студента. Тест через Лямба")
    public void successfulFillingFormStudentTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1920x1080";

        step("Открыть страницу тестовой формы добавления студента",()->{
            open("https://demoqa.com/automation-practice-form");
        });

        step("Ввести Имя",()->{
            $("input#firstName").setValue("Ivan");
        });

        step("Ввести Фамилию",()->{
            $("input#lastName").setValue("Pupochkin");
        });

        step("Ввести электронную почту",()->{
            $("input#userEmail").setValue("testik@test.te");
        });

        step("Выбрать пол",()->{
            $("input[value='Female']").click();
        });

        step("Ввести номер телефона",()->{
            $("input#userNumber").setValue("4444444444");
        });

        step("Ввести дату рождения",()->{
            $("input#dateOfBirthInput").click();
            $("select.react-datepicker__year-select option[value='2005']").click();
            $("select.react-datepicker__month-select").selectOption("March");
            $$(".react-datepicker__day").filterBy(Condition.text("17")).get(0).click();
        });

        step("Нажать кнопку Submit",()->{
            $("button#submit").shouldBe(Condition.clickable).click();
        });

        step("Проверка отображения модалки отправленной формы клиента",()->{
            $("div.fade.modal.show").shouldHave(text("Thanks for submitting the form"));
        });

        step("Проверка отображения фамилии и имени студента",()->{
            $$("td").filterBy(text("Student Name")).get(0).sibling(0).shouldHave(text("Ivan Pupochkin8"));
        });

    }

}
