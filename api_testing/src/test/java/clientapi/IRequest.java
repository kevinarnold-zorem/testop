package clientapi;

import io.restassured.response.Response;

public interface IRequest {
    Response send(String url,String payload);
}
