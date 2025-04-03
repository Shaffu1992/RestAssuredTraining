package day4;
import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.Map;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
public class ParsingJSONResponseData {
//	@Test(priority=1)
	void testJsonResponse()
	{//without capturing response
		//Approach1
		given()
			.contentType("application/json")
		.when()
			.get("http://localhost:3000/students")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
//			.body("students[1].firstName",equalTo("Sophia"));
			.body("[1].firstName", equalTo("Sophia"));

			}
//	@Test(priority=2)
	void testJsonResponseBody() {
	//capturing response
	//Approach 2
	Response res=
			given()
			.contentType("application/json")
			.when()
				.get("http://localhost:3000/students");
	
	 Assert.assertEquals(res.getHeader("Content-Type"), "application/json");
	 Assert.assertEquals(res.getStatusCode(), 200);
	 
	 String firstname=res.jsonPath().get("[0].firstName").toString();//important
	 
	 Assert.assertEquals(firstname, "Michael");
}
	
	//@Test
	void testJsonResponseBodyData() {
	//capturing response
	//Approach 3
	Response res=
			given()
			.contentType("application/json")
			.when()
				.get("http://localhost:3000/students");
	
	// ---chatgpt Step 3: Extract Response Body
   // String responseBody = res.getBody().asString();

    // Step 4: Parse JSON Response
   // JSONObject jo = new JSONObject(responseBody);-----
	
	 // Step 2: Convert Response Body to JSON Object
    JSONObject jo = new JSONObject(res.getBody().asString()); // ✅ Corrected
    //res.toString() is just a string representation of the response object, not the JSON body.
    // ✅ Fix: Extract the actual JSON response using res.getBody().asString()
    
    // Step 3: Extract "students" JSON Array
    JSONArray studentsArray = jo.getJSONArray("students");
    //studentsArray is a JSON array containing multiple student objects.
    //Each entry in studentsArray is a JSON object
    // Step 4: Loop through JSON Array and extract emails
   
    for (int i = 0; i < studentsArray.length(); i++) {
//        String email = studentsArray.getJSONObject(i).getString("email"); // ✅ Fixed
//        System.out.println("Email: " + email);
        JSONObject student = studentsArray.getJSONObject(i);

        // Step 5: Check if "email" exists before extracting
        if (student.has("email")) {
            String email = student.getString("email");
            System.out.println("Email:----- " + email);
        } else {
            System.out.println("Skipping invalid record: " + student.toString());
    }

 // Assertion to check if response code is 200
    Assert.assertEquals(res.getStatusCode(), 200);
	
}
}
	
	
	@Test///copied from chatgpt
   void testJsoccnResponseBodyData() {
        // Step 1: Send GET Request
        Response res =
            given()
                .contentType("application/json")
            .when()
                .get("http://localhost:3000/students");

        // Step 2: Verify Response Code
        Assert.assertEquals(res.getStatusCode(), 200, "Status code mismatch!");

        // Step 3: Extract Response Body
        String responseBody = res.getBody().asString();
        System.out.println("🔍 Response Body: " + responseBody);

        // Step 4: Check if the response starts with '{' (object) or '[' (array)
        if (responseBody.trim().startsWith("{")) {
            // Case 1: Response is a JSON object (expected format)
            JSONObject jo = new JSONObject(responseBody);
            JSONArray studentsArray = jo.getJSONArray("students");
            extractEmails(studentsArray);
        } else if (responseBody.trim().startsWith("[")) {
            // Case 2: Response is an array (unexpected format)
            JSONArray studentsArray = new JSONArray(responseBody);
            extractEmails(studentsArray);
        } else {
            // Case 3: Response is not JSON
            System.out.println("❌ Error: Unexpected response format. Check API response!");
        }
    }

    void extractEmails(JSONArray studentsArray) {
        System.out.println("📌 Extracted Student Emails:");
        for (int i = 0; i < studentsArray.length(); i++) {
            JSONObject student = studentsArray.getJSONObject(i);

            // Step 5: Check if "email" exists before extracting
            if (student.has("email")) {
                String email = student.getString("email");
                System.out.println("Email: " + email);
            } else {
                System.out.println("Skipping invalid record: " + student.toString());
            }
        }
    }
	
}