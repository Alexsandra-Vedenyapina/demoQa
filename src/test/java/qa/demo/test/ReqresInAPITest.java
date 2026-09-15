package qa.demo.test;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
//import qa.demo.models.lombok.UserBodyLombokModel;
//import qa.demo.models.lombok.UsersResponseLombokModel;
import qa.demo.models.pojo.SinglUserResponse;
import qa.demo.models.pojo.UserBodyModelPojo;
import qa.demo.models.pojo.UserResponseModelPojo;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.FieldsOrPropertiesExtractor.extract;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static qa.demo.specs.UserSpec.*;

public class ReqresInAPITest {
    private String nameUser="Tratila";
    private String jobUser="Cat";
    private Integer idUSER=3;
    private String jobNew="Super Cat";


    @Test
    @Story("Тесты для REST API")
    @Owner("AVedenyapina")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка списка кол-ва записе в data равно per_page")
    void testDataSizeEquelsPerPageValue(){
        Integer numberPage=2;

        var response = step("Выполнить запрос на получение списка пользователей", () -> given(userRequestSpec)
                .when()
                .get("?page=" + numberPage)
                .then()
                .spec(userResponseSpec)
                .extract().response());

        int perPage = response.path("per_page");
        int dataSize = response.path("data.size()");

        step("Проверка, что количество записей в data равно per_page", ()->
                assertEquals(perPage,dataSize, "Количество записей в data("+dataSize+") должно быть равно per_page("+perPage));

    }

    @Test
    @Story("Тесты для REST API")
    @Owner("AVedenyapina")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Успешное получение Юзера")
    void testSuccessGetUser(){
        Integer idUser = 2;

        SinglUserResponse userResponse = step("Выполнить запрос на получение пользователя", ()->
                given(userRequestSpec)
                .when()
                .get("/"+idUSER)
                .then()
                .extract().as(SinglUserResponse.class));

        step("Проверка, полученный пользователь имеет id: " + idUser, () -> assertEquals(idUser,userResponse.getData().getId()));
    }

    @Test
    @Story("Тесты для REST API")
    @Owner("AVedenyapina")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Успешное создание Юзера")
    void testSuccessUserCreationPojo(){
        UserBodyModelPojo userBody= new UserBodyModelPojo();
        userBody.setName(nameUser);
        userBody.setJob(jobUser);

        UserResponseModelPojo userResponse= step("Выполнить запрос на создание пользователя" , ()->
                given(userRequestSpec)
                .body(userBody)
                .when()
                .post()
                .then()
                .spec(userResponseSpec)
                .extract().as(UserResponseModelPojo.class));

        step("Проверка, созданный пользователь имеет имя: " + nameUser, () -> assertEquals(nameUser,userResponse.getName()) );
        step("Проверка, созданный пользователь имеет работу: "+jobUser, () ->assertThat(userResponse.getJob()).isEqualTo(jobUser));
    }

    @Test
    @Story("Тесты для REST API")
    @Owner("AVedenyapina")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Успешное изменение данных Юзера")
    void testSuccessUserUpdateLombok(){

        UserBodyModelPojo userBody = new UserBodyModelPojo();
        userBody.setName(nameUser);
        userBody.setJob(jobNew);

        UserResponseModelPojo response = step("Выполнить запрос на обновление данных пользователя", ()->
                given(userRequestSpec)
                .body(userBody)
                .when()
                .put()
                .then()
                .spec(userResponseSpec)
                .statusCode(200)
                .extract().as(UserResponseModelPojo.class));

        step("Проверка, имя пользователя равно: " + nameUser, ()-> assertThat(response.getName()).isEqualTo(nameUser));
        step("Проверка, работа пользователя равно: " + jobNew, ()-> assertThat(response.getJob()).isEqualTo(jobNew));

    }

    @Test
    @Story("Тесты для REST API")
    @Owner("AVedenyapina")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Ошибка 404 на изменение данных Юзера")
    void testFailUserUpdateeNotFound() {
        UserBodyModelPojo userBody = new UserBodyModelPojo();
        userBody.setName(nameUser);
        userBody.setJob(jobNew);

       step("Выполнить запрос на обновление данных пользователя", () ->
                given(userRequestSpec)
                        .body(userBody)
                        .when()
                        .put()
                        .then()
                        .spec(userResponseSpec404));

    }

    @Test
    @Story("Тесты для REST API")
    @Owner("AVedenyapina")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Успешное удаление Юзера")
    void testSuccessUserDeleted(){
        //Integer idUser = 4;
        step("Выполнить запрос на удаление пользователя", () ->
        given(userRequestSpec)
                .when()
                .delete("/"+idUSER)
                .then()
                .spec(userResponseSpec));

    }
}

