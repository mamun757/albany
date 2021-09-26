package api_test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PutTest {
    private static final Logger LOGGER = LogManager.getLogger(PutTest.class);
    @Test
    public void updateUserFields(){
        LOGGER.info("----Api Test: Put Method-----");

        //base URL of RestApi
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the request Specification to send to the server
        RequestSpecification httpRequest = RestAssured.given();

        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        LOGGER.debug("New user name: " + fullName);

        String position = faker.job().position();
        LOGGER.debug("New user Job Title: " + position);

        JSONObject object = new JSONObject();
        object.put("name", fullName);
        object.put("job", position);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(object.toJSONString());

        String id = "2";

        Response response = httpRequest.request(Method.PUT, id);
        LOGGER.debug(response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("name");
        Assert.assertEquals(actualName, fullName);


        LOGGER.info("------End of Put Method ---------");

    }
}
