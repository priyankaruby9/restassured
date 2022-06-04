package RestBDD;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetReqBDD {
	
	@Test
	public void test1() {
		
		RestAssured.given()
							.baseUri("http://localhost:3000/employees")
							.when()
							//.get("/1")  //will give the employee with id 1
							.get()
							.then()
							.log()
							//.all(); will show all logs
							//.status() //will show only status code
							//.statusCode(200); //will check for valid status code
							.body();//will show only body
		
	}
	
	@Test
	public void test2() {
		
		RestAssured.given()
							.baseUri("http://localhost:3000/employees")
							.queryParam("id", "1") //will give paramaeters
							//.queryParam("name", "Pankaj")//will give particular name 
							.when()
							.get()
							.then()
							.log()
							.body()
							.body("[0].name", Matchers.equalTo("Pankaj"));//out of multiple same name verify for 1st name
		
	}
	
	@Test  //to mix java and BDD use the response
	public void test3() {
		
		Response response = RestAssured.given()
											.baseUri("http://localhost:3000/employees")
											.queryParam("id", "1") //will give paramaeters
											.when()
											.get();
		
		System.out.println(response.getBody().asString());
		JsonPath jpath = response.jsonPath();
		List<String> names = jpath.get("name");
		
		System.out.println(names.get(0));
		Assert.assertEquals(names.get(0), "Pankaj");
		
		String Header = response.getHeader("Content-Type");
		System.out.println(Header);
	}

}
