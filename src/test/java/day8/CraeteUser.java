package day8;
import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
public class CraeteUser {
	@Test
	void test_CreateUser(ITestContext context)
	{//api chaining concept we have started
		// Create Faker instance
	    Faker faker = new Faker();
		
		JSONObject data=new JSONObject();
	
		 data.put("name", faker.name().fullName());  // Generates a random full name
	     data.put("gender", "Male"); // Generates a random address
	     data.put("status", "inactive"); // Generates a random phone number	
	     //data.put("email", faker.internet().emailAddress()); // Generates a random email
	     data.put("email", "shaffu" + faker.number().digits(3) + "@example.com");
	     
	     String bearerToken="2c8a198820636843f742bfc4ef149d1816cc24f04e2507c949582fdc13bf771b";
	     
	     int id=given()
	     .headers("Authorization", "Bearer " + bearerToken)
	     .contentType("application/json")
	     .body(data.toString())
	     
	     .when()
	     .post("https://gorest.co.in/public/v2/users")
	     
	     .then()
	     .log().all()  // Logs response details
         .statusCode(201)  // Ensures user is created successfully
         .extract().jsonPath().getInt("id");

     // Print and store the generated user ID
     System.out.println("Generated ID --> " + id);
    // context.setAttribute("user_id", id);//this is at test level
     context.getSuite().setAttribute("user_id", id); //this is at suite level
     
     //ITestContext is an interface in TestNG that allows sharing data between test methods within the same
     	//test suite or class.

 //It helps pass dynamic values (like user IDs, tokens, etc.) from one test case to another without 
		//using global variables.
	}
	
	
     
			

}
