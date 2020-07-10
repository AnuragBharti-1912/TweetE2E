/**
 * 
 */
package practice;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
/**
 * @author Anurag Bharti
 *
 */
public class TweetKaro {

	String id;
	String consumerKey="kyxf3BehOHVIQcNxnnQvDLfba";
	String consumerSecret="hC9wwJZFBKErmfCphZgkbVdC0P0Ix5vbuLpcEWtc0v0jg6llbH";
	String accessToken="294456807-rh40c7rPZc9UjF0Ghya5dT6K2njgN8j5pqkdE0MY";
	String secretToken="0ER3NqSXp3Uk9PmxDnim5HNrCm4o5cym5KhiHujOaLiV2";

	@Test(priority=1)
	public void statusUpdate() {



		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";

		Response res=given().queryParam("status", "sone ka time")
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.when()
				.post("/update.json")
				.then()
				.assertThat()
				.and()
				.contentType("application/json")
				.and()
				.extract()
				.response();
		String response = res.asString();
		JsonPath js= new JsonPath(response);
		id= js.get("id")+"";
		System.out.println("******"+response);
		System.out.println(js.get("id"));


	}

	@Test(priority=2)
	public void getUpdate() {
		//statusUpdate();
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";

		Response res=given().param("id", id).auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.when()
				.get("/show.json")
				.then()
				.assertThat()
				.and()
				.contentType("application/json")
				.and()
				.extract()
				.response();
		String response = res.asString();
		JsonPath js= new JsonPath(response);

		System.out.println("#########"+js.get("text"));
	}
	@Test(priority=3)
	public void delUpdate() {
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";

		Response res=given().auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
				.when()
				.post("destroy/"+id+".json")
				.then()
				.assertThat()
				.and()
				.contentType("application/json")
				.and()
				.extract()
				.response();
		String response = res.asString();
		JsonPath js= new JsonPath(response);

		System.out.println("deleted text: "+js.get("text"));
		System.out.println(js.get("truncated"));

	}
}
