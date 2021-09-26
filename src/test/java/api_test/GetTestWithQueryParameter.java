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

import java.util.List;

public class GetTestWithQueryParameter {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithQueryParameter.class);

    @Test
    public void getAllWithQueryParaMeter(){
        LOGGER.info("----Api Test: Read(Get)Query Parameter All users-----");

        //base URL of RestApi
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the request Specification to send to the server
        RequestSpecification httpRequest = RestAssured.given();

        //Request to the server and server will send back the request
        Response response = httpRequest.queryParam("page", 2).request(Method.GET);
        LOGGER.debug(response.getBody().asPrettyString());


        //Validating the response code
        Assert.assertEquals(response.getStatusCode(), 200);

        //jsonPath object
        JsonPath jsonPath = response.jsonPath();
        List<String> list = jsonPath.get("data.email");

        //jsonPath.getString("data[0].email");
        //validating the email id
        String emailId = "michael.lawson@reqres.in";
        boolean nameExists = list.contains(emailId);
        Assert.assertTrue(nameExists, emailId + "does not exists");
        LOGGER.info("------End of Read(Get) Query Parameter of All Users---------");
        System.out.println(list);

    }

}
