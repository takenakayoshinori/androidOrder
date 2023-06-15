$('#masterbutton').on('click', function() {
  $('#master').toggle();
});

const getMenber = function() {
	projectId = window.prompt("プロジェクトIDを入力してください", "");
	axios.get('/menber/' + projectId)
		.then(response => {
			var menberSheetPosition = worksheets[0].parent.getWorksheet('Member');
			if (menberSheetPosition) {
				worksheets[menberSheetPosition].loadData(response.data);
			} else {
			    jspreadsheet(document.getElementById('projectSpredsheet'), {
			    	worksheets: [ {
						data: response.data,
						worksheetName: 'Member',
						worksheetId: 'Member',
						columns: menberColums
				 } ]
			    });
			}
		})
		.catch(error => {
			alert('プロジェクトIDが不正です');
		});
}