package day5;




import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class JSONResponse {
    String code,message;
    /**
     *
     * Home Work
     * <p>
     * <p>
     * * given accept type is JSON
     * * When user send a get request to  url  https://demoqa.com/Account/v1/User/11
     * * than status code is 401
     * * de-serialize => json to java collection
     * * verify that message is User not authorized!
     * * verify that code is 1200
     */
    @BeforeClass
    public void beforeClass() {
        //baseURI = "https://demoqa.com/Account/v1/User/11";
    }

    @Test
    public void test() {

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("https://demoqa.com/Account/v1/User/11");
        assertEquals(response.statusCode(), 401);
        JSONResponse responseBody = response.as(JSONResponse.class);
        Assert.assertEquals("User not authorized!", responseBody.message);
        Assert.assertEquals("1200", responseBody.code);
    }

}