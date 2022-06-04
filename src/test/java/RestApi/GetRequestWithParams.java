package RestApi;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithParams {
	
	@Test
	public void test1() {
		
		RestAssured.baseURI = "http://localhost:3000/employees";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.param("id","1").get();
		//Response response = request.get();
		String Responsebody = response.getBody().asString();
		
		System.out.println(Responsebody);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 200);
		
		Assert.assertTrue(Responsebody.contains("Pankaj"));
		
		JsonPath jpath = response.jsonPath();
		List<String> names = jpath.get("name");
		
		System.out.println(names.get(0));
		Assert.assertEquals(names.get(0), "Pankaj");
		
		/*List<String> names = jpath.get("name");
		for (String name: names) {
			System.out.println(name);
		}*/
		
		String Header = response.getHeader("Content-Type");
		System.out.println(Header);
		
	}

}
