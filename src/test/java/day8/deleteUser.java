package day8;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;
public class deleteUser {
	@Test
	void testDelete_user(ITestContext context) {
		String bearerToken="2c8a198820636843f742bfc4ef149d1816cc24f04e2507c949582fdc13bf771b";
		//int id=(int) context.getAttribute("user_id");
		int id=(int) context.getSuite().getAttribute("user_id");
		given()
	     .headers("Authorization", "Bearer " + bearerToken)
	     .pathParam("id", id)
	     
	     .when()
	     .delete("https://gorest.co.in/public/v2/users/{id}")
	     
	     .then()
	     .statusCode(204)
	     .log().all();
	    

	
	
	
	}


}
