package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static Response getRequest(String endpoint) {


        return given().header(
                "Accept", ContentType.JSON
        ).when().get(endpoint);

    }


    public static <T> Response postRequest( String endpoint, T T) {


        return given().headers(
                        "Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON
                ).
                body(T).when().post(endpoint);


    }

    public static <T> Response putRequest(String user, String language, String endpoint, T T) {


        return given()
                .headers(
                        "Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON)
                .body(T)
                .when()
                .post(endpoint);

    }

    public static Response deleteRequest(String user, String language, String endpoint) {


        return given()
                .headers(
                        "Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON)
                .when()
                .post(endpoint);

    }

}