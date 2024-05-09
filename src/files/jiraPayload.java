package files;

public class jiraPayload {

	public static String createIssue() {
		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\":\r\n"
				+ "        {\r\n"
				+ "            \"key\":\"RSA\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\":\"Credit card defect\",\r\n"
				+ "        \"description\":\"creating my first bug\",\r\n"
				+ "        \"issuetype\":{\r\n"
				+ "            \"name\":\"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}";		
		
	}
	
	public static String addComment() {
		
		
	return "{\r\n"
			+ "    \"body\": \"This is my first comment\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}";
	}
	public static String addProj() {
		return "{\r\n"
				+ "    \"key\": \"EX\",\r\n"
				+ "    \"name\": \"Example\",\r\n"
				+ "    \"projectTypeKey\": \"business\",\r\n"
				+ "    \"projectTemplateKey\": \"com.atlassian.jira-core-project-templates:jira-core-project-management\",\r\n"
				+ "    \"description\": \"Example Project description\",\r\n"
				+ "    \"lead\": \"Dpa\",\r\n"
				+ "    \"url\": \"http://atlassian.com\",\r\n"
				+ "    \"assigneeType\": \"PROJECT_LEAD\",\r\n"
				+ "    \"avatarId\": 10200,\r\n"
				+ "    \"issueSecurityScheme\": 10001,\r\n"
				+ "    \"permissionScheme\": 10011,\r\n"
				+ "    \"notificationScheme\": 10021,\r\n"
				+ "    \"workflowSchemeId\": 10031,\r\n"
				+ "    \"categoryId\": 10120\r\n"
				+ "}";
	}
}
