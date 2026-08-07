package qa.demo.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticFormPage {

    @Step
    public PracticFormPage openPage(){
        open("https://demoqa.com/automation-practice-form");
        return this;
    }

    @Step
    public PracticFormPage setFirstName(String firstName){
        $("input#firstName").setValue(firstName);
        return this;
    }

    @Step
    public PracticFormPage setLastName(String lastName){
        $("input#lastName").setValue(lastName);
        return this;
    }

    @Step
    public PracticFormPage setEmail(String email){
        $("input#userEmail").setValue(email);
        return this;
    }

    @Step
    public PracticFormPage shouseGender(String gender){
        $("input[value='"+gender+"']").click();
        return this;
    }

    @Step
    public PracticFormPage setMobileNumber(String mobileNumber){
        $("input#userNumber").setValue(mobileNumber);
        return this;
    }

    @Step
    public PracticFormPage setDateOfBirth(String year, String month, String day){
        $("input#dateOfBirthInput").click();
        $("select.react-datepicker__year-select option[value='"+year+"']").click();
        $("select.react-datepicker__month-select").selectOption(month);
        $$(".react-datepicker__day").filterBy(text(day)).get(0).click();
        return this;
    }

    @Step
    public PracticFormPage clickSubmit(){
        $("button#submit").shouldBe(Condition.clickable).click();
        return this;
    }

   @Step
   public PracticFormPage checkOpenedModalSubmitting(String text){
       $("div.fade.modal.show").shouldHave(text(text));
       return this;
   }

   @Step
           public PracticFormPage checkStudentName(String firstName, String lastName){
       $$("td").filterBy(text("Student Name")).get(0).sibling(0).shouldHave(text(firstName+" "+lastName));
       return this;
   }


}
