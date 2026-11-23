package api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    private static final String BASE_URL =
            "https://jsonplaceholder.typicode.com";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}
