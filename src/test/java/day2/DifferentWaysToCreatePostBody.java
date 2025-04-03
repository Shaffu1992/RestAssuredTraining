package day2;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
public class DifferentWaysToCreatePostBody {
	//adding student data
//	@Test(priority=1)
	void testPostUsingHashMap()
	{ 	// to create api
		///json-server C:\Users\mehta\Documents\Shaffu\students.json.json --port 3000
		HashMap<String,Object>data=new HashMap<String,Object>();
		 data.put("firstName", "Sheena");
	     data.put("lastName", "dhamijha");
		 data.put("age", "27");
		 data.put("grade", "12");
		 data.put("email", "sheena@gmail.com");
//		 List<String> courseSubjects = Arrays.asList("Mathematics", "Science", "English");
		 String courseSubjects[]= {"Mathematics","Science","English"};
		 data.put("enrolledSubjects", courseSubjects);
		 
		 given()
		 	.contentType("application/json")
		 	.body(data)
		 .when()
		 	.post("http://localhost:3000/students")
		 .then()
		 	.statusCode(201)
		 	.body("firstName", equalTo("Sheena"))
		 	.body("lastName", equalTo("dhamijha"))
		 	.body("age", equalTo("27"))
		 	.body("grade", equalTo("12"))
		 	.body("enrolledSubjects[0]", equalTo("Mathematics"))
		 	.body("enrolledSubjects[1]", equalTo("Science"))
		 	.body("enrolledSubjects[2]", equalTo("English"))
		 	.header("Content-Type", "application/json")
		 	.log().all();
		
	     
	}
	//deleting student record
//	@Test(priority=2)
	void testDelete()
	{
		given()
		.when()
			.delete("http://localhost:3000/students/e7ec")
		.then()
			.statusCode(200);
		
	}
	
//	@Test(priority=2)
	void testPostUsingJsonLibrary()
	{   
		JSONObject data =new JSONObject();
		data.put("firstName", "Sheena");
	     data.put("lastName", "dhamijha");
		 data.put("age", "27");
		 data.put("grade", "12");
		 data.put("email", "sheena@gmail.com");
		 String courseSubjects[]= {"Mathematics","Science","English"};
		 data.put("enrolledSubjects", courseSubjects);
		 given()
		 	.contentType("application/json")
		 	.body(data.toString())
		 .when()
		 	.post("http://localhost:3000/students")
		 .then()
		 	.statusCode(201)
		 	.body("firstName", equalTo("Sheena"))
		 	.body("lastName", equalTo("dhamijha"))
		 	.body("age", equalTo("27"))
		 	.body("grade", equalTo("12"))
		 	.body("enrolledSubjects[0]", equalTo("Mathematics"))
		 	.body("enrolledSubjects[1]", equalTo("Science"))
		 	.body("enrolledSubjects[2]", equalTo("English"))
		 	.header("Content-Type", "application/json")
		 	.log().all();
	}
	
//	@Test
	void testPostUsingPOJO()
	{   RestAssured.defaultParser = Parser.JSON;
		Pojo_PostRequest data=new Pojo_PostRequest();
		data.setFirstName("Sheena12");
		data.setLastName("dhamijha");
		data.setAge("20");
		data.setGrade("12");
		data.setEmail("sheena@gmail.com");
		String courseSubjects[]= {"Mathematics","Science","English"};
		data.setCourseSubjects(courseSubjects);
		
		 given()
	 		.contentType("application/json")
		 	.body(data)
		 .when()
		 	.post("http://localhost:3000/students")
		 .then()
		 	.statusCode(201)
		 	.body("firstName", equalTo("Sheena12"))
		 	.body("lastName", equalTo("dhamijha"))
		 	.body("age", equalTo("20"))
		 	.body("grade", equalTo("12"))
		 	.body("courseSubjects[0]", equalTo("Mathematics"))
		 	.body("courseSubjects[1]", equalTo("Science"))
		 	.body("courseSubjects[2]", equalTo("English"))
		 	.header("Content-Type", "application/json")
		 	.log().all();
	}

	
	@Test
	void testPostUsingExternalJsonFile() throws FileNotFoundException
	{
		 // Provide the path of your JSON file
        File jsonFile = new File("C:\\Workplace\\workplace 6dec 2024\\6th dec 2024\\RestAssured\\Body.json");
		FileReader fr=new FileReader(jsonFile);	
		JSONTokener jt=new JSONTokener(fr);
		JSONObject data =new JSONObject(jt);// Convert JSON file content to JSONObject
		
		 given()
	 		.contentType("application/json")

            .body(data.toString())  // Convert JSONObject to String
		 .when()
		 	.post("http://localhost:3000/students")
		 .then()
		 	.statusCode(201)
		 	.body("firstName", equalTo("Heena"))
		 	.body("lastName", equalTo("khan"))
		 	.body("age", equalTo("15"))
		 	.body("grade", equalTo("9th"))
		 	.body("enrolledSubjects[0]", equalTo("Physical Education"))
		 	.body("enrolledSubjects[1]", equalTo("Math"))
		 	.header("Content-Type", "application/json")
		 	.log().all();
	}
}
