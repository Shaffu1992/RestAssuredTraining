package day3;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	@Test
	void testCookies()
	{
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.cookie("AEC","AVcja2elLsswy67u_DrzkaE0ddpaW-xGL-9_CSjSwgG-U9jmOjAnl7BnLVk")
			.log().all();
		// this will always fail because value of cookie is dynamic it will change everytime
	}
	@Test
	void getCookieInfo()
	{
		Response res=given()
				.when()
					.get("https://www.google.com/");//get method is returning response here
		///if i want to capture the response then there will be  no continuation methods that's no then is used
					
		// get single cookie info
		String cookie_value=res.getCookie("AEC");
		System.out.println("Value of cookie is ===> "+cookie_value)	;	
	}
	
	
	@Test
	void getCookiesInfo()
	{
		Response res=given()
				.when()
					.get("https://www.google.com/");//get method is returning response here
		//if i want to capture the response then there will be  no continuation methods that's no then is used
					
		// get single cookie info
		String cookie_value=res.getCookie("AEC");
		System.out.println("Value of cookie is ===> "+cookie_value)	;	
		
		//get all cookies info and store in map
		Map<String,String>cookies_values=res.getCookies();
		System.out.println(cookies_values.keySet());
		
		for(String k:cookies_values.keySet())
		{
			String cookiee_value=res.getCookie(k);///getCookie() method will fetch the value of cookie based upon key
			System.out.println(k+"-------> "+cookiee_value);
		}
		
	}
	
	
}
