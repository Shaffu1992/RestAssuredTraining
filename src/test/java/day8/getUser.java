package day8;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import org.testng.ITestContext;

public class getUser {
	@Test
	void getUser(ITestContext context )
	{
		//int id=(int) context.getAttribute("user_id");
		int id=(int) context.getSuite().getAttribute("user_id");
		String bearerToken="2c8a198820636843f742bfc4ef149d1816cc24f04e2507c949582fdc13bf771b";
	     
	     given()
	     .headers("Authorization", "Bearer " + bearerToken)
	     .pathParam("id", id)
	     
	    .when()
	    .get("https://gorest.co.in/public/v2/users/{id}")
	    
	    .then()
	    .statusCode(200)
	    .log().all();

	}

}
