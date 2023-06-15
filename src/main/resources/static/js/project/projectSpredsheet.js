//jspreadsheet.setLicense('YjQwMzlhYTEyMDFkYWM4MjU1OTlkY2UxMzM2ZDM0NjRkMTBhMmM2NjRhMDJkZDNiZDg5ZjgxZjEwMWZhOTBmM2RhYTQwYmQ3ZjJiNzRjYzk2NDc2YTVmNzhjYTMzOWY0YmE1ZGU4ZjgzNzNjOTg0MDY0NzRjYTAxNDEzOTQ0MGMsZXlKdVlXMWxJam9pU25Od2NtVmhaSE5vWldWMElpd2laR0YwWlNJNk1UWTJPVFF5T0RZNE1pd2laRzl0WVdsdUlqcGJJbXB6Y0hKbFlXUnphR1ZsZEM1amIyMGlMQ0pqYjJSbGMyRnVaR0p2ZUM1cGJ5SXNJbXB6YUdWc2JDNXVaWFFpTENKamMySXVZWEJ3SWl3aWJHOWpZV3hvYjNOMElsMHNJbkJzWVc0aU9pSXpJaXdpYzJOdmNHVWlPbHNpZGpjaUxDSjJPQ0lzSW5ZNUlpd2lZMmhoY25Seklpd2labTl5YlhNaUxDSm1iM0p0ZFd4aElpd2ljR0Z5YzJWeUlpd2ljbVZ1WkdWeUlpd2lZMjl0YldWdWRITWlMQ0pwYlhCdmNuUWlMQ0ppWVhJaUxDSjJZV3hwWkdGMGFXOXVjeUlzSW5ObFlYSmphQ0pkTENKa1pXMXZJanAwY25WbGZRPT0=');

// Create the spreadsheet
var worksheets = jspreadsheet(document.getElementById('projectSpredsheet'), {
	tabs: true,
	toolbar: {
		items: [
			{
				tooltip: 'getProjectMenber',
            	content: 'groups',
            	onclick:  getMenber
			}
		]
		
	},
	worksheets: [
		{
			data: JSON.parse($('#projectJson').val()),
			worksheetId: 'Project',
			worksheetName: 'Project',
	        columns: projectColums
		}
	],
});