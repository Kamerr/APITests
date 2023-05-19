package Day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class UserGetRequest {
    String bookStoreBaseUrl = "https://bookstore.toolsqa.com/swagger/#/";
    String exlabBaseUrl="https://www.krafttechexlab.com/sw/api";
    /** Class Task
     *  Given accept type is JSON
     *  When user send GET request to /Books
     *  Then verify that response status code is 200
     *  and body is JSON format
     *  and response body contains "Richard E. Silverman"
     */

    @Test
    public void test1(){
        Response response = RestAssured
                .given()
                .when()
                .get(bookStoreBaseUrl+"/BookStore/v1/Books");

        assertEquals( response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertTrue(response.body().asString().contains("Richard E. Silverman"));

    }
    @Test
    public void test2(){
        /** Class Tasl
         * Get All Users
         */
        Response response = RestAssured
                .given().accept(ContentType.JSON)
            .queryParam("pagesize",20)
            .queryParam("page",1)
            .and()
            .when()
            .get(exlabBaseUrl+"v1/allusers/alluser");
        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();
    }
    @Test
    public void test3(){
        Response response = RestAssured
                .given().accept(ContentType.JSON)
                .pathParam("id", 24)
                .when()
                .get(exlabBaseUrl + "/allusers/getbyid/{id}");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");

        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Content-TypeContent-Type\") = " + response.header("Content-Type"));
        System.out.println("response.header(\"DateDate\") = " + response.header("Date"));

        //verify the header
        assertEquals(response.header("Content-Length"),"968");
        assertEquals(response.header("Content-Type"),"application/json; charset=UTF-8");

        assertTrue(response.headers().hasHeaderWithName("Date"));
        //verify the body
        assertTrue(response.body().asString().contains("mike@gmail.com"));

    }
}