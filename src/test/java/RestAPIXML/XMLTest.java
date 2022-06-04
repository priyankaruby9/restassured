package RestAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {
	
	@Test
	public void test1() {
		RestAssured.given()
		.baseUri("https://chercher.tech/sample/api/books.xml")
		.get()
		.then()
		.log()
		.body();				
	}
	
	@Test
	public void test2() {
		
		Response response = RestAssured.given()
									.baseUri("https://chercher.tech/sample/api/books.xml")
									.when()
									.get();
		NodeChildrenImpl books = response.then().extract().path("bookstore.book.title");
		
		System.out.println("all the books " + books.toString());	
		System.out.println("first books " + books.get(0).toString());
		System.out.println("second books " + books.get(1).toString());
		
		NodeChildrenImpl booksauthor = response.then().extract().path("bookstore.book.author");
		System.out.println("all the books author " + booksauthor.toString());
		
		System.out.println("language of fisrt book " + books.get(0).getAttribute("lang"));//to get attribute for title
		
		//List<String> names = jpath.get("name");
		/*for (String b: books) {
			System.out.println(b.toString());
		}
		*/
		for (int i = 0; i<books.size();i++) {
			System.out.println(books.get(i).toString());
		}
		
		NodeChildrenImpl prices = response.then().extract().path("bookstore.book.price");
		System.out.println("all the prices" + prices.toString());
		System.out.println("first price:paperback is " + prices.get(0).children().get("paperback"));
		System.out.println("price of second book is :" + prices.get(0).toString());
		
	}

}
