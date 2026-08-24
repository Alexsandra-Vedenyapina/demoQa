package qa.demo.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa.demo.pages.PracticFormPage;

@Feature("Тестирование формы через WebSteps")
public class PracticFormWebStepsTest {
    private static String firstName ="Alex";
    private static String lastName = "Peru";
    private static String email = "alex@peru.cr";
    private  static  String gender = "Male";
    private static String mobile = "6666666666";
    private static String  year = "1999";
    private static String month = "March";
    private static String day= "13";
    private static String modalText ="Thanks for submitting the form";

    PracticFormPage practicForm = new PracticFormPage();

    @Test
    @Story("Успешная отправка студента")
    @Owner("AVedenyapina")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("BLOKER")
    @DisplayName("Успешное заполнение формы студента")
    public void testPracticFormWebSteps(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1920x1080";

        practicForm.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .shouseGender(gender)
                .setMobileNumber(mobile)
                .setDateOfBirth(year,month,day)
                .clickSubmit();

        practicForm.checkOpenedModalSubmitting(modalText);
        practicForm.checkStudentName(firstName,lastName);
    }
}
