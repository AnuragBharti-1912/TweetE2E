package test;

//import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class PostStatusUpdate {

	String consumerKey="t8bJWbRPfrXkol55Ph3ARyZxJ";
	String consumerSecret="bj0m0ZNa4yIMpj21aumQcOYh5gp1jb8u87g1xwAYBgmuziQ1u6";
	String accessToken="294456807-TPeRFdIMKPF2JX8fSzcHf2ayBVgr47sYisZHkenN";
	String tokenSecret="SFpoEHaN06PIboTQ66k9q3SCzVadJxIAKJQ4it7k9HVAH";

	@Test
	public void createTweet() 
	{

		RestAssured.baseURI= "https://api.twitter.com/1.1/statuses";

		Response resp=	given().auth().oauth(consumerKey, consumerSecret, accessToken, tokenSecret)
				.queryParam("status", "How are you")
				.when()
				.post("/update.json")
				.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.extract().response();

		String response1=resp.asString();
		System.out.println(response1);
		JsonPath js = new JsonPath(response1);
		System.out.println(js.get("text"));

	}


}
