package qa.demo.test;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

public class CRUDUserAPITest {
    private String nameUser="Tratila";
    private String jobUser="Cat";
    private String idUSer;
    private String jobNew="Super Cat";
    private String url="https://reqres.in/api/users";

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
}
