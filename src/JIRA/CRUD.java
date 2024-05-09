package JIRA;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.jiraPayload;
import files.reUsable;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class CRUD {
@Test
public void CRUDInJira(){
	String expectedComment = "This is my first comment";
	RestAssured.baseURI = "http://localhost:8080";
	
	
	SessionFilter sf = new SessionFilter();
	given().log().all().header("Content-Type","application/json").body("{ \"username\": \"dpadeeps\", \"password\": \"jira12345\"}").filter(sf).when()
	.post("/rest/auth/1/session").then().log().all().assertThat().statusCode(200).extract().response().asString();
//	System.out.println(response);
//	
//	JsonPath js = reUsable.ToJson(response);
//	String sessionName = js.getString("session.name");
//	String sessionID = js.getString("session.value");
//	String actualId = sessionName+"="+sessionID;
//	System.out.println(actualId);
	
	
	//create Issue
	
	String issueResponse = given().log().all().header("Content-Type","application/json").body(jiraPayload.createIssue()).filter(sf).when().post("/rest/api/2/issue")
	.then().log().all().assertThat().statusCode(201).extract().response().asString();
	JsonPath js1 = reUsable.ToJson(issueResponse);
	String issueID = js1.get("id");
	System.out.println(issueID);

//add comment
	String addCommentResponse = given().log().all().header("Content-Type","application/json").pathParam("key","10004").body(jiraPayload.addComment()).filter(sf).when().post("/rest/api/2/issue/{key}/comment")
	.then().log().all().assertThat().statusCode(201).extract().response().asString();
	JsonPath js = new JsonPath(addCommentResponse);
	String commentId = js.getString("id");
	
	//add attachment
	given().header("X-Atlassian-Token","no-check").filter(sf).pathParam("issueID","10004").header("Content-Type","multipart/form-data").multiPart("file", new File("Test.txt"))
	.post("/rest/api/2/issue/{issueID}/attachments").then().log().all().assertThat().statusCode(200);
	
	//get issue
	String issueDetails = given().log().all().pathParam("key","10004").queryParam("fields","comment").filter(sf).when().get("/rest/api/2/issue/{key}")
	.then().log().all().assertThat().statusCode(200).extract().response().asString();
	System.out.println(issueDetails);
	
	JsonPath js4 = new JsonPath(issueDetails);
	int commentsCount = js4.getInt("fields.comment.comments.size()");
	for(int i=0; i< commentsCount; i++) 
	{
	String commentIdIssue = js4.get("fields.comment.comments["+i+"].id").toString();
	if(commentIdIssue.equalsIgnoreCase(commentId))
	{
		String bodyComment = js4.get("fields.comment.comments["+i+"].body").toString();
		System.out.println(bodyComment);
		Assert.assertEquals(bodyComment, expectedComment);
	}
	}
		
	
}
}