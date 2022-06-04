package APIChaining;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	
	Response response;
	String BaseURI = "http://localhost:3000/employees";
	@Test
	public void test1() {
		
		response = GetMethodAll();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response = PostMethod("Lucia", "2000");
		Assert.assertEquals(response.getStatusCode(), 201);
		
		JsonPath Jpath =response.jsonPath();
		int empid = Jpath.get("id");
        System.out.println("id " +empid);
		
        response = PutMethod(empid,"Raj", "2000");
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Jpath =response.jsonPath();
		Assert.assertEquals(Jpath.get("name"), "Raj");
	
		response = DeleteMethod(empid);
		Assert.assertEquals(response.getStatusCode(), 200);
		//String ResponseBody = response.getBody().asString();
        Assert.assertEquals(response.getBody().asString(), "{}"); 
        
        response = GetMethod(empid);
		Assert.assertEquals(response.getStatusCode(), 404);
		//String ResponseBody = response.getBody().asString();
        Assert.assertEquals(response.getBody().asString(), "{}"); 
	}
	
	public Response GetMethodAll() {
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		return response;		
	}
	
	public Response PostMethod(String Name, String Salary) {
		
		RestAssured.baseURI = BaseURI;
		
		JSONObject jobj = new JSONObject();
		jobj.put("name", Name);
		jobj.put("salary", Salary);
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(jobj.toString())
				.post("/create");
	
		return response;
	}
	
	public Response PutMethod(int Empid, String Name, String Salary) {
		
		RestAssured.baseURI = BaseURI;
		
		JSONObject jobj = new JSONObject();
		jobj.put("name", Name);
		jobj.put("salary", Salary);
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(jobj.toString())
				.put("/" + Empid);
	
		return response;
	}
	
	public Response DeleteMethod(int Empid) {
		
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.delete("/" + Empid);
	
		return response;
	}
	
	public Response GetMethod(int Empid) {
		
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/" + Empid);
	
		return response;
	}


}
