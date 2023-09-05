package clientapi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Put implements IRequest{

    @Override
    public Response send(String url,String payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .put(url);
        return response;
    }
}
