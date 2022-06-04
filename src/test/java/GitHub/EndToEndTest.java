package GitHub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	@Test
	public void test1() throws InterruptedException {
		

		RestAssured.baseURI = "https://api.github.com/users/priyankaruby9/repos";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.accept(ContentType.JSON).get();
		
		//Thread.sleep(5000);
		String Responsebody = response.getBody().asString();
		
		System.out.println(Responsebody);
		
		int ResponseCode = response.getStatusCode();
		
		Assert.assertEquals(ResponseCode, 200);
		
	}
	
	@Test
	public void test2() throws IOException {
		RestAssured.baseURI = "https://api.github.com/user/repos";
		
		byte[] dataBytes= Files.readAllBytes(Paths.get("datafile.json"));
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.auth()
				.oauth2("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")
				.body(dataBytes)
				.post();
		
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 201);
		
		//JsonPath Jpath =response.jsonPath();
        //System.out.println("id"+Jpath.get("id"));
	}

}
