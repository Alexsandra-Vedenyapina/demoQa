package qa.demo.test;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUsersAPITest {

    @Test
    void testGetUsersReturnStatusCode200(){
        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body();

    }

    @Test
    void testGetUsersReturnValidJsonStructure(){
        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users?page=1")
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
                .get("https://reqres.in/api/users?page=1")
                .then()
                .log().status()
                .log().body()
                .extract().response();

        int perPage = response.path("per_page");
        int dataSize = response.path("data.size()");

        assertEquals(perPage,dataSize, "Количество записей в data("+dataSize+") должно быть равно per_page("+perPage);

    }
}
