package day3;
import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class LoggingDemo {
	
	@Test
	void testLogs()
	{
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
//			.log().cookies()
			.log().headers();
//			.log().body()
	}

}
