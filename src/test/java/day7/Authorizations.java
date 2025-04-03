package day7;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
public class Authorizations {
	
	//@Test(priority=1)
	void testBasicAuthorization1()
	{
		given()
		.auth().basic("shafffuuser","shaffupass")
		.when()
		.get("https://petstore.swagger.io/v2/user/login?username=shafffuuser&password=shaffupass")
		.then()
		.body("code",equalTo(200))
		.body("type", equalTo("unknown"))
		.statusCode(200)
		.log().all();
		
		
	}
	//@Test(priority=2)
	void testBasicAuthorization2()
	{
		given()
		.auth().basic("shaff","123")
		.when()
		.get("https://httpbin.org/basic-auth/shaff/123")
		.then()
		.statusCode(200)
		.body("authenticated",equalTo(true))
		.log().all();
		
	}
	
	//@Test(priority=3)
	void testDigestAuth()
	{
		given()
		.auth().digest("shaff", "pass")
		.when()
		.get("https://httpbin.org/digest-auth/undefined/shaff/pass")
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
		
	}
	//.auth().preemptive().basic("shaff", "pass")--combination of digest and basic
	
	//@Test(priority=4)
	void bearerTokenAuth1()
	{
		String bearer_token="test123a";
		given()
		 .headers("Authorization", "Bearer " + bearer_token)
		
		.when()
		.get("https://httpbin.org/bearer")
		
		.then()
		.log().all()
		.statusCode(200)
		.body("authenticated", equalTo(true));
		
		
	}
	//@Test(priority=5)
	void bearerTokenAuth2()
	{
	    String bearer_token="2c8a198820636843f742bfc4ef149d1816cc24f04e2507c949582fdc13bf771b";  // Replace with a valid token
	    
	    given()
	        .headers("Authorization", "Bearer " + bearer_token)//we are passing bearer token as Authorization header
	    .when()
	        .get("https://gorest.co.in/public/v2/users/7374893")  // GitHub API supports Bearer Tokens
	    .then()
	        .statusCode(200)
	        .log().all();
	}
	
	@Test(priority=6)
		void apiKeyAuthentication()
		{
	    given()
		       .queryParam("appid", "75636901a482c46f58dfc2eca19ada54")//we are passing api key as query params 
		       .pathParam("mypath", "/data/2.5/weather")
		       .queryParam("q", "Delhi")//this query params is in url
		    .when()
		        .get("https://api.openweathermap.org/{mypath}")  // GitHub API supports Bearer Tokens
		        //----https://api.openweathermap.org//data/2.5/weather?q=Delhi
		    .then()
		        .statusCode(200)
		        .log().all();
		}

}
