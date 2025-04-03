package day3;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;
public class PathAndQueryParameters {
//	@Test
	 void testQueryAndPathParameters()
	 {
		 given()
		 	.pathParam("mypath","users")
		 	.queryParam("page", 2)
		 	.queryParam("id", 3)
		 .when()
		 	.get("https://reqres.in/api/{mypath}")// you can see here query params it will take automaticaly no 
		 	//need to mention here but yes path param you have to mention as its variable
		 	//-----https://reqres.in//api/users?page=2&id=3
		 	
		 .then()
		 .statusCode(200)
		 .log().all();
	 }


}
