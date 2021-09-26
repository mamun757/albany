package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NegativeTest {
    private static final Logger LOGGER = LogManager.getLogger(NegativeTest.class);

    @Test
    public void testWithInvalidId(){
        LOGGER.info("----Api Test:negative test of users-----");

        //base URL of RestApi
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the request Specification to send to the server
        RequestSpecification httpRequest = RestAssured.given();

        //Request to the server and server will send back the request
        String invalidId = "23";
        Response response = httpRequest.request(Method.GET, invalidId);
        LOGGER.debug(response.getBody().asPrettyString());


        //Validating the response code
        Assert.assertEquals(response.getStatusCode(), 404);

        //Validating the response has empty body
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get().toString().length();
        Assert.assertEquals(jsonPath.get().toString(), "{}");

        //below code also working
       //boolean emptyBody = response.getBody().asString().length() > 0;
       //Assert.assertTrue(emptyBody);
        LOGGER.info("------end of negative test---------");

    }
}
