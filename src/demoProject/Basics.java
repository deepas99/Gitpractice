package demoProject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.reUsable;

public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//validation of add place API
		//given, when, then
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(new String(Files.readAllBytes(Paths.get("C:\\Users\\deepas\\Downloads\\payload.json")))).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		//parse JSON
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
//		System.out.println(placeId);
		
		//update place
		String newAddress = "70 Summer walk, USA";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		
		//get place
		
//		 String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", ""+placeId+"").when().get("maps/api/place/get/json").then().assertThat().statusCode(200)
//		.body("accuracy",equalTo("50")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).when().get("maps/api/place/get/json").then().assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println(getResponse);
		
		JsonPath js1 = reUsable.ToJson(getResponse);
		String longitude = js1.getString("location.longitude");
		System.out.println(longitude);
		
		String Address = js1.getString("address");
		System.out.println(Address);
		
		Assert.assertEquals(Address, newAddress);
		
		
		
}
}
