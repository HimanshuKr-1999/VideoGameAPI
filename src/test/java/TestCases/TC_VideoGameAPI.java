package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;


public class TC_VideoGameAPI {
	@Test(priority=1)
	public void TC_GetAllVideoGames() {	
		

//        	Response response =get("https://reqres.in/api/users?page=2");
//		
//		
//		System.out.println(response.getStatusCode());
//		System.out.println(response.getTime());
//		System.out.println(response.getBody().asPrettyString());
//		System.out.println(response.getHeader("content-type"));
//		
//		int statusCode = response.getStatusCode();
//		Assert.assertEquals(statusCode,200);
Response Res=
          given()
     .when()
         .get("http://localhost:8080/app/videogames")
     .then()
         .statusCode(200)
         
         .extract().response();
String ResponseHeader= Res.asPrettyString();
int statusCodeCheck = Res.getStatusCode();
System.out.println(statusCodeCheck);
Assert.assertEquals(statusCodeCheck, 200);
System.out.println("Respomse Header:"+ ResponseHeader);

    }
	
	
	
	
	
	
	
    @Test(priority=2)
    public void TC_addNewVideoGame() {
    	
    	
        // Your test code for POST request
    	
JSONObject data = new JSONObject();
		
data.put("id", "159");
data.put("name","spider-man2");
data.put("releaseDate", "2023-10-01T00:00:00.000Z");
data.put("reviewScore", "5");
data.put("category", "adventure");
data.put("rating", "universal");

Response res=
given()
.contentType("Application/json")
.body(data.toJSONString())
.when()
  .post("http://localhost:8080/app/videogames")
.then()
  .statusCode(200)
  .log().body()
  .extract().response();

int statusCodeCheck = res.getStatusCode();
System.out.println(statusCodeCheck);

String ResponseBody= res.asString();
System.out.println("Response Body :"+ ResponseBody +"\n");
System.out.println(res.getBody());

Assert.assertEquals(ResponseBody.contains("Record Added Successfully"), true);



 
		
		
		
    	
    	
    	
    	
    	
    	
    	
    	
//        HashMap<String, Object> data = new HashMap();
//        data.put("id", "100");
//        data.put("name","spider-man");
//        data.put("releaseDate", "2023-10-01T00:00:00.000Z");
//        data.put("reviewScore", "5");
//        data.put("category", "adventure");
//        data.put("rating", "universal");
//
//        Response res= 
//
//        given()
//            .contentType("Application/json")
//            .body(data)
//            .when()
//                .post("http://localhost:8080/app/videogames")
//            .then()
//                .statusCode(200)
//                .log().body()
//                .extract().response();
//
//
//        String responseBody = res.asString();
//        System.out.println("Response Body: " + responseBody);
//        //Assert.assertEquals(responseBody.contains("Record Added Successfully"), true);


        
    }

    @Test(priority=3)
    public void TC_GetAVideoGame() {
    	
    	
    	baseURI="http://localhost:8080/app/videogames";
    	Response res=
    	given()
    		.when()
    			.get("/1")
    		.then()
    			.statusCode(200)
    			.log().body()
    			.body("videoGame.id", equalTo("1"))
    			.extract().response();
    		
//    	String Response =res.toString();
//    	System.out.println("Response :"+res.body());
//    	
    }
    @Test(priority=4)
    public void TC_DeleteAVideoGame() {
    	
    	
    	baseURI= "http://localhost:8080/app/videogames";
    //	Response res=
    	given()
    	.when()
    		.delete("/7")
    	.then()
    		.statusCode(200)
    		.log().body()
    		.extract().response();
    	
    	//String response= res.toString();
    //	Assert.assertEquals(response.contains("Record Deleted Successfully"),true );
    }
	

}
