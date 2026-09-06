package qa.demo.test;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
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
    void testSuccessUserCreation(){
        String body="{\n" +
                "  \"name\": \""+nameUser+"\",\n" +
                "  \"job\": \""+jobUser+"\"\n" +
                "}";
        given()
                .log().all()
                .body(body)
                .contentType(JSON)
                .when()
                .post(url)
                .then()
                .log().all()
                .statusCode(201)
                .body("name",is(nameUser))
                .body("job", is(jobUser));

    }

    @Test
    void testSuccessUserUpdate(){
        String body="{\n" +
                "  \"name\": \""+nameUser+"\",\n" +
                "  \"job\": \""+jobNew+"\"\n" +
                "}";

        given()
                .log().all()
                .body(body)
                .contentType(JSON)
                .when()
                .put(url+"/7")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job",is(jobNew))
                .body("name", is(nameUser));
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

