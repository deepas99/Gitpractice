import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		JsonPath js = new JsonPath(payload.Courses());
		//Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//Print Title of the first course
		String firstTitle = js.getString("courses[0].title");
		System.out.println(firstTitle);
		
		//Print All course titles and their respective Prices
		
		for(int i=0; i<count; i++) 
		{
			String AllTitles = js.get("courses["+i+"].title");
			System.out.println(AllTitles);
			System.out.println(js.get("courses["+i+"].price").toString());
		}
		
		//Print no of copies sold by RPA Course
		
		System.out.println("----------------");
		for(int i=0; i<count; i++) {
			String AllTitles = js.get("courses["+i+"].title");
//			String title = "RPA";
			if(AllTitles.equalsIgnoreCase("RPA")) {
				int NoOfCopies = js.getInt("courses["+i+"].copies");
				System.out.println(NoOfCopies);
			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		
	}

}
