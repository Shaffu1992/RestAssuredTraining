package day6;
import static io.restassured.RestAssured.given;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
public class JsonSchemaValidationTest {
	//json-server students.json.json   to initiate the server
    //@Test
    void testJsonSchemaValidation() {
    	String response = given()
            .when()
                .get("http://localhost:3000/students")  // API Endpoint
            .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema.json"))

                .statusCode(200) // Optional: Validate status code
                //.log().all(); // Print response for debugging
                .log().body()
                .extract().asString(); 

        System.out.println("API Response: " + response);
    }
    
    @Test
    void testJsonSchemaValidation1() {
    given()
            .when()
                .get("http://localhost:3000/students")  // API Endpoint
            .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema.json"))
                .log().body();

                

    }
}


			
	

