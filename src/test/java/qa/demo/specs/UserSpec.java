package qa.demo.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.hamcrest.Matcher;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.google.common.base.CharMatcher.is;
import static io.restassured.RestAssured.*;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static qa.demo.helpers.CustomAllureListener.withCustomTemplates;

public class UserSpec {
    private static String BASE_URI = "https://reqres.in";
    private static String BASE_PATH ="/api/users";


    public static RequestSpecification userRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON)
            .baseUri(BASE_URI)
            .basePath(BASE_PATH);

    public static ResponseSpecification userResponseSpec= new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(anyOf(is(200), is(201), is(204)))
            .build();

    public static ResponseSpecification userResponseSpec404 = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(404)
            .build();



}
