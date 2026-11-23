package api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

public class ApiTest extends BaseTest {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {

        requestSpec = given()
                .contentType(ContentType.JSON);
        responseSpec = expect()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @DataProvider(name = "postIds")
    public Object[][] postIdsProvider() {
        return new Object[][]{
                {1},
                {2},
                {3},
                {4},
                {5}
        };
    }

    @Test
    public void getPostTest() {

        given()
        .when()
                .get("/posts/1")
        .then()
                .statusCode(200)
                .body("id",equalTo(1))
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }

    @Test
    public void createPostTest() {

        given()
                .spec(requestSpec)
                .body("""
                    {
                        "title": "Test1",
                        "body": "Created Post",
                        "userId": 1
                    }
                """)
          .when()
                .post("/posts")
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
                .spec(requestSpec)
                .body(requestBody)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                .body("body",equalTo("Created Post"))
                .body("title", equalTo("Test1"))
                .body("userId", equalTo(1));
    }

    @Test
    public void createPostPojoTest(){
        Post requestBody = new Post("Test1", "Created Post", 1, 0);
        given()
                .spec(requestSpec)
                .body(requestBody)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                .body("body",equalTo("Created Post"))
                .body("title", equalTo("Test1"))
                .body("userId", equalTo(1));
    }

    @Test(dataProvider = "postIds")
    public void getPostPojoTest(int postId) {

        Post response =
                given()
                .when()
                        .get("/posts/" + postId)
                .then()
                        .spec(responseSpec)
                        .log().body()
                        .extract()
                        .as(Post.class);
        Assert.assertEquals(response.getId(), postId);
    }

    @Test
    public void getPostsByUserIdTest() {
        given()
                .queryParam("userId", 3)
        .when()
                .get("/posts")
        .then()
                .spec(responseSpec)
                .log().body()
                .body("userId", everyItem(equalTo(3)));
    }

    @Test
    public void getPostsByUserIAndVerifyResponseUsingStreamTest() {
      List<Post> posts =
              given()
                      .queryParam("userId", 3)
              .when()
                      .get("/posts")
              .then()
                      .spec(responseSpec)
                      .extract()
                      .jsonPath()
                      .getList(".", Post.class);
        Assert.assertTrue(posts.stream().allMatch(p -> p.getUserId() == 3));
    }

    @Test
    public void createAndUpdatePostTest(){
        Post requestBody = new Post("Test1", "Created Post", 3091, 0);
        Post createdPost =
        given()
                .spec(requestSpec)
                .body(requestBody)
       .when()
                .post("/posts")
       .then()
                .spec(responseSpec)
                .log().body()
                .extract()
                .as(Post.class);

        // Update created Post
        Post updateBody = new Post("Test2", "Updated Post", createdPost.getUserId(), createdPost.getId());
        given()
                .spec(requestSpec)
                .body(updateBody)
        .when()
                .put("/posts/"+ createdPost.getId())
        .then()
                .spec(responseSpec)
                .body("id", equalTo(1))
                .body("title", equalTo("Test2"))
                .body("body", equalTo("Updated Post"));
    }
}