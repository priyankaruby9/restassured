package RestApi;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutRequest {
	
	@Test
	public void test1() {
		
		RestAssured.baseURI = "http://localhost:3000/employees";
		
		Map<String,Object> Mapobj = new HashMap<String,Object>();
		
		Mapobj.put("name", "Alex");
		Mapobj.put("salary", "9000");
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(Mapobj)
				.put("/5");              //5 is the id 
		
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 200);
		
	}

}
