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

public class GetTestWithPathVariable {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithPathVariable.class);

    @Test
    public void getASingleUser(){
        LOGGER.info("----Api Test: Read(Get) a Single user-----");

        //base URL of RestApi
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the request Specification to send to the server
        RequestSpecification httpRequest = RestAssured.given();

        //Request to the server and server will send back the request
        String id = "2";
        Response response = httpRequest.request(Method.GET, id);
        LOGGER.debug(response.getBody().asPrettyString());


        //Validating the response code
        Assert.assertEquals(response.getStatusCode(), 200);

        //jsonPath object
        JsonPath jsonPath = response.jsonPath();


        //validating the email id
        String expectedEmail = "janet.weaver@reqres.in";
        String actualEmail = jsonPath.getString("data.email");
        Assert.assertEquals(actualEmail,expectedEmail);
        LOGGER.info("------End of Read(Get) of A single User---------");

    }
}
