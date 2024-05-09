import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class sunValidation {
	@Test
	public void sumOfCourses() {
		int sum =0;
		JsonPath js = new JsonPath(payload.Courses());
		int count = js.getInt("courses.size()");
		for(int i=0; i<count; i++) {
			int price = js.get("courses["+i+"].price");
			int copies = js.get("courses["+i+"].copies");
			int Amount = price * copies;
			System.out.println(Amount);
			sum= sum+Amount;
		}
		System.out.println(sum);
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmt);
	}
}
