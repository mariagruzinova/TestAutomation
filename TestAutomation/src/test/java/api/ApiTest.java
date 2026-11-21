package api;

import io.restassured.http.ContentType;
import models.Post;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @Test
    public void getPostTest() {

        given()
        .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
        .then()
                .statusCode(200)
                .body("id",equalTo(1))
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }

    @Test
    public void createPostTest() {

        given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                        "title": "Test1",
                        "body": "Created Post",
                        "userId": 1
                    }
                """)
          .when()
                .post("https://jsonplaceholder.typicode.com/posts")
          .then()
                .statusCode(201)
                .body("body",equalTo("Created Post"))
                .body("title", equalTo("Test1"))
                .body("userId", equalTo(1));
    }

    @Test
    public void createPostMapTest() {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "Test1");
        requestBody.put("body", "Created Post");
        requestBody.put("userId", 1);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .body("body",equalTo("Created Post"))
                .body("title", equalTo("Test1"))
                .body("userId", equalTo(1));
    }

    @Test
    public void createPostPojoTest(){
        Post requestBody = new Post("Test1", "Created Post", 1);
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("https://jsonplaceholder.typicode.com/posts")
        .then()
                .statusCode(201)
                .body("body",equalTo("Created Post"))
                .body("title", equalTo("Test1"))
                .body("userId", equalTo(1));
    }

    @Test
    public void getPostPojoTest() {

        Post response =
                given()
                .when()
                        .get("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                        .statusCode(200)
                        .log().body()
                        .extract()
                        .as(Post.class);
    }
}