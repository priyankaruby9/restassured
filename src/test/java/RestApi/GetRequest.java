package RestApi;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {
	
	@Test
	public void test1() {
		
		RestAssured.baseURI = "http://localhost:3000/employees";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.get();
		
		String Responsebody = response.getBody().asString();
		
		System.out.println(Responsebody);
		
		int ResponseCode = response.getStatusCode();
		
		Assert.assertEquals(ResponseCode, 200);
		
	}

}
