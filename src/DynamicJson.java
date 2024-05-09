import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import files.payload;
import files.reUsable;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
@Test(dataProvider="TestData")
public void addBook(String isbn, String aisle) {
	RestAssured.baseURI="http://216.10.245.166";
	
	String response = given().log().all().header("Content-Type","application/json")
	.body(payload.AddBook(isbn,aisle))
	.when().post("/Library/Addbook.php")
	.then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js = reUsable.ToJson(response);
	String id = js.get("ID");
	System.out.println(id);
	
	
	String delResponse = given().log().all().body(payload.DeleteBook(id)).when().post("/Library/DeleteBook.php").
			then().log().all().assertThat().statusCode(200).body("msg",equalTo("book is successfully deleted")).extract().response().asString();
	System.out.println(delResponse);
	
}

@DataProvider(name="TestData")
public Object[][] getData()
{
	return new Object[][] {{"sdjhd","345443"},{"ewrqwerf","34545"},{"qwererg","5798678"}};
}



//delete book


}

