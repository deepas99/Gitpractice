package files;

import io.restassured.path.json.JsonPath;

public class reUsable {

	public static JsonPath ToJson(String resp) {
		JsonPath js1 = new JsonPath(resp);
		return js1;
	}
}
