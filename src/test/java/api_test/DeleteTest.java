package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DeleteTest {
    private static final Logger LOGGER = LogManager.getLogger(DeleteTest.class);

    @Test
    public void deleteSingleUser(){
        LOGGER.info("----Api Test: Delete method-----");

        //base URL of RestApi
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the request Specification to send to the server
        RequestSpecification httpRequest = RestAssured.given();

        //Request to the server and server will send back the request
        String id = "2";
        Response response = httpRequest.request(Method.DELETE, id);

        //Validating the response code
        Assert.assertEquals(response.getStatusCode(), 204);

        LOGGER.info("------End of Delete Method---------");

    }
}
