package qa.demo.test;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import qa.demo.models.lombok.UserBodyLombokModel;
import qa.demo.models.lombok.UsersResponseLombokModel;
import qa.demo.models.pojo.UserBodyModelPojo;
import qa.demo.models.pojo.UserResponseModelPojo;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInAPITest {
    private String nameUser="Tratila";
    private String jobUser="Cat";
    private String idUSer;
    private String jobNew="Super Cat";
    private String url="https://reqres.in/api/users";

    @Test
    void testGetUsersReturnStatusCode200(){
        given()
                .log().all()
                .when()
                .get(url+"?page=1")
                .then()
                .log().status()
                .log().body();

    }

    @Test
    void testGetUsersReturnValidJsonStructure(){
        given()
                .log().all()
                .when()
                .get(url+"?page=1")
                .then()
                .log().status()
                .log().body()
                .extract().path("page", "per_page","total","total_pages", "data");

    }

    @Test
    void testDataSizeEquelsPerPageValue(){
        var response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(url+"?page=1")
                .then()
                .log().status()
                .log().body()
                .extract().response();

        int perPage = response.path("per_page");
        int dataSize = response.path("data.size()");

        assertEquals(perPage,dataSize, "Количество записей в data("+dataSize+") должно быть равно per_page("+perPage);

    }

    @Test
    void testSuccessGetUser(){
        given()
                .log().uri()
                .when()
                .get(url+"/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2));
    }

    @Test
    void testSuccessUserCreationPojo(){
        UserBodyModelPojo userBody= new UserBodyModelPojo();
        userBody.setName(nameUser);
        userBody.setJob(jobUser);

        UserResponseModelPojo userResponse= given()
                .log().uri()
                .log().body()
                .body(userBody)
                .contentType(JSON)
                .when()
                .post(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(UserResponseModelPojo.class);

        assertEquals(nameUser,userResponse.getName());
        assertThat(userResponse.getJob()).isEqualTo(jobUser);
    }

    @Test
    void testSuccessUserUpdateLombok(){

        UserBodyLombokModel userBody = new UserBodyLombokModel();
        userBody.setName(nameUser);
        userBody.setJob(jobNew);

        UsersResponseLombokModel response = given()
                .log().uri()
                .log().body()
                .body(userBody)
                .contentType(JSON)
                .when()
                .put(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(UsersResponseLombokModel.class);

        assertThat(response.getName()).isEqualTo(nameUser);
        assertThat(response.getJob()).isEqualTo(jobNew);
    }

    @Test
    void testFailUserUpdateeNotFound(){
        String body="{\n" +
                "  \"name\": \""+nameUser+"\",\n" +
                "  \"job\": \""+jobNew+"\"\n" +
                "}";
        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .put(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void testSuccessUserDeleted(){
        given()
                .log().uri()
                .when()
                .delete(url+"/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);

    }
}

