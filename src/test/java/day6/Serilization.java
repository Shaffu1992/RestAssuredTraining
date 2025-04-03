package day6;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Serilization {
	@Test(priority=1)
	void convertPojo2Jason() throws JsonProcessingException {
		studentpojo stupojo=new studentpojo();
		stupojo.setAge("27");
		stupojo.setEmail("sss@gmail.com");
		stupojo.setFirstName("Shaffu");
		stupojo.setGrade("A");
		stupojo.setLastName("Mehta");
		String courses[]= {"Java","Selenium","C++"};
		stupojo.setCourseSubjects(courses);
		//Convert java object that is pojo here into json object--serilization
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonData=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);//see data type of json object is string here
		
		System.out.println(jsonData);
		
	}
	@Test(priority=2)
	void convertJson2POJO() throws JsonMappingException, JsonProcessingException
	{
		String json="{\r\n"
				+ "  \"firstName\" : \"Shaffu\",\r\n"
				+ "  \"lastName\" : \"Mehta\",\r\n"
				+ "  \"age\" : \"27\",\r\n"
				+ "  \"grade\" : \"A\",\r\n"
				+ "  \"email\" : \"sss@gmail.com\",\r\n"
				+ "  \"courseSubjects\" : [ \"Java\", \"Selenium\", \"C++\" ]\r\n"
				+ "}";
		// convert json data --> pojo object
		ObjectMapper objectMapper=new ObjectMapper();
		studentpojo stup=objectMapper.readValue(json, studentpojo.class);//conerting json to pojo
		System.out.println("First Name is--->"+stup.getFirstName());
		System.out.println("email is--->"+stup.getEmail());
		System.out.println("Course is--->"+stup.getCourseSubjects()[1]);
		System.out.println("Grades--->"+stup.getGrade());
		//note --serilization and deserilization is automatically done internally 
		//see we use content type --application /json in header remember
		//in given() ---->.contentType("application/json")
		//hashmap is alsoobject but pojo is purely java object
	}

}
