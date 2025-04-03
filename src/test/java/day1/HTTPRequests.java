package day1;
//import org.testng.annotations.Test;
//import io.restassured.RestAssured.*;
//import io.restassured.matcher.RestAssuredMatchers.*;
//import org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;
public class HTTPRequests {
	int id;
	@Test(priority=0)
	void getUsers()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
		////shaffu change noooohhh9999
	}

	@Test(priority=2)
	void createData()
	{
		 // ✅ Using Generics for Type Safety
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Shaffu");
        data.put("job", "trainer");
//        / ✅ Correcting ID Extraction
        id = given()
            .contentType("application/json")
            .body(data)
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .log().all()
            .extract().jsonPath().getInt("id"); // ✅ Corrected ID extraction--extracting id from the response
        System.out.println("the id is----->"+id);
	}
	
	@Test(priority=3)
	void updateUser()
	{
		HashMap<String,String>data =new HashMap();
		data.put("name", "john");
		data.put("job", "engineer");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/"+id)
			//+id passing id so that this particular id you have to update
			
		.then()
			.statusCode(200)
			//.body("name", equalTo("john"))
			.log().all();
		
		
		
	}
	
}
