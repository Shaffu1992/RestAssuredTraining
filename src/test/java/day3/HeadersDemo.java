package day3;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
public class HeadersDemo {
	@Test(priority=-1)
	void testHeaders()
	{
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws");
	
	}
	@Test(priority=-2)
	void getHeader()
	{
		Response res=given()
				.when()	
					.get("https://www.google.com/");
		System.out.println("header is there");
		//get single header
		String headervalue=res.getHeader("Content-Type");
		System.out.println("The value of Content-type header is :"+headervalue);
		
				
	}
	@Test(priority=0)
	void getHeaders() {
		Response res=given()
				.when()	
					.get("https://www.google.com/");//you have to stop there with ; because i wanna fetch response
		//get single header
//				String headervalue=res.getHeader("Content-Type");
//				System.out.println("The value of Content-type header is :"+headervalue);
		
		//get all headers and store in headers where as cookies store in map
		Headers myheaders=res.getHeaders();
		System.out.println("headers are there");
		for(Header hd : myheaders) 
		{
			System.out.println(hd.getName()+" ------>         "+hd.getValue());
		}
	}

}
