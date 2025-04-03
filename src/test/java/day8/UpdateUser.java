package day8;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import org.json.JSONObject;
import org.testng.ITestContext;

public class UpdateUser {
	@Test
	void test_updateUser(ITestContext context) {
	Faker faker = new Faker();
	
	JSONObject data=new JSONObject();

	 data.put("name", faker.name().fullName());  // Generates a random full name
     data.put("gender", "Female"); // Generates a random address
     data.put("status", "active"); // Generates a random phone number	     
     data.put("email", "shaffu" + faker.number().digits(3) + "@example.com");
   //data.put("email", faker.internet().emailAddress()); // Generates a random email
     
		String bearerToken="2c8a198820636843f742bfc4ef149d1816cc24f04e2507c949582fdc13bf771b";
		//int id=(int) context.getAttribute("user_id");
		int id=(int) context.getSuite().getAttribute("user_id");
	     given()
	     .headers("Authorization", "Bearer " + bearerToken)
	     .contentType("application/json")
	     .body(data.toString())
	     .pathParam("id", id)
	     
	     .when()
	     .put("https://gorest.co.in//public/v2/users/{id}")
	     
	     .then()
	     .log().all();
	}

}
