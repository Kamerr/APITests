package Day06;

import APIPOJOTemplates.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class JsonToJavaDeserilization_POJO_User {
    //TASK
    //base url = https://gorest.co.in/
    //end point = /public/v2/users
    //path parameter = {id} --> 1736288
    //send a get request with the above credentials
    //parse to Json object to pojo (custom java class)
    //verify that the body below
    /*
    {
        "id": 1736264,
        "name": "Vasanti Guneta",
        "email": "vasanti_guneta@blick.example",
        "gender": "female",
        "status": "active"
    }
]
     */
    @Test
    public void test() {
        Response response = RestAssured
                .given()
                .when()
                .get("https://gorest.co.in/public/v2/users/1736264");

        //assert status code
        Assert.assertEquals(response.statusCode(),200);

        //Map<String,Object> map = response.as(Map.class);
        //de-serilization
        //Json to known Java Collection (map, list)
        User user = response.as(User.class);

        //get id
        System.out.println(user.getId());
        int actualId = user.getId();
        int expectedId = 1736264;
        Assert.assertEquals(actualId,expectedId);

        //get name
        System.out.println(user.getName());
        String actualName = user.getName();
        String expectedName = "Vasanti Guneta";
        Assert.assertEquals(actualName,expectedName);

        //get email
        System.out.println(user.getEmail());
        String actualEmail = user.getEmail();
        String expectedEmail = "vasanti_guneta@blick.example";
        Assert.assertEquals(actualEmail,expectedEmail);

        //get gender
        System.out.println(user.getGender());
        String actualGender = user.getGender();
        String expectedGender = "female";
        Assert.assertEquals(actualGender,expectedGender);

        //get status
        System.out.println(user.getStatus());
        String actualStatus = user.getStatus();
        String expectedStatus = "active";
        Assert.assertEquals(actualStatus,expectedStatus);
    }
}


