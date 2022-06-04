package RestBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteReqBDD {
	
	@Test
	public void test1() {
		
		RestAssured.given()
							.baseUri("http://localhost:3000/employees")
							.when()
							//.get("/1")  //will give the employee with id 1
							.delete("/12")
							.then()
							.log()
							//.all(); will show all logs
							//.status() //will show only status code
							//.statusCode(200); //will check for valid status code
							.body();//will show only body
		
	}
}
