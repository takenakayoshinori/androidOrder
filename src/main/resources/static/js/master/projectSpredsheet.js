//jspreadsheet.setLicense('NWUyODQ0YjI5MTIyYzJmZmI2ZjNmMzAwNWE1OTA4ODgwM2ZiOTE4OTUwNDQyOGRmNjRmN2M1NjE2YWI3OGI0ZDJkY2M1ZGJiYmMyYTlkMWExMzNkYmRmYWMxY2U1MWFiOTM3MDc3MGNjMjk5NGFmZTgyY2Y4ZGNhMTBhY2JmZmYsZXlKdVlXMWxJam9pU25Od2NtVmhaSE5vWldWMElpd2laR0YwWlNJNk1UWTJPRFkyTkRrMU1Dd2laRzl0WVdsdUlqcGJJbXB6Y0hKbFlXUnphR1ZsZEM1amIyMGlMQ0pqYjJSbGMyRnVaR0p2ZUM1cGJ5SXNJbXB6YUdWc2JDNXVaWFFpTENKamMySXVZWEJ3SWl3aWJHOWpZV3hvYjNOMElsMHNJbkJzWVc0aU9pSXpJaXdpYzJOdmNHVWlPbHNpZGpjaUxDSjJPQ0lzSW5ZNUlpd2lZMmhoY25Seklpd2labTl5YlhNaUxDSm1iM0p0ZFd4aElpd2ljR0Z5YzJWeUlpd2ljbVZ1WkdWeUlpd2lZMjl0YldWdWRITWlMQ0pwYlhCdmNuUWlMQ0ppWVhJaUxDSjJZV3hwWkdGMGFXOXVjeUlzSW5ObFlYSmphQ0pkTENKa1pXMXZJanAwY25WbGZRPT0=');

// Create the spreadsheet
var masterWorksheets = jspreadsheet(document.getElementById('masterSpredsheet'), {
	tabs: false,
	worksheets: [
		{
			data: JSON.parse($('#projectJson').val()),
			worksheetId: 'Master',
			worksheetName: 'Master',
	        columns: projectColums
		}
	],
});