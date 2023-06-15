const masterColums = [
	{
		type: "hidden",
		name: 'seqNo ',
		title: "seq",
		readOnly: true
	},
	{ 
		type: "hidden", 
		name: 'projectId', 
		title: "projectId", 
		readOnly: true
	},
	{ 
		type: "hidden",
		name: 'number',
		title: "No",
		readOnly: true
	},
	{ 
		type: "text",
		name: 'theme',
		title: "テーマ",
		align: 'left',
		width: '270px'
	},
	{ 
		type: "text",
		name: 'function',
		title: "対象機能",
		align: 'left',
		width: '270px'
	},
	{
		type: "dropdown",
		name: 'charge',
		title: "担当者",
		align: 'left',
		url:'/menber/' + $('#projectId').val(),
		autocomplete:true,
		width: '200px'
		
	},
	{
		type: "text",
		name: 'design',
		title: "設計",
		mask: '0.00',
		align: 'right',
		width: '70px'
	},
	{
		type: "text",
		name: 'implementation',
		title: "開発",
		mask: '0.00',
		align: 'right',
		width: '70px'
	},
	{
		type: "text",
		name: 'unit',
		title: "単体",
		mask: '0.00',
		align: 'right',
		width: '70px'
	},
	{
		type: "text",
		name: 'binding',
		title: "結合",
		mask: '0.00',
		align: 'right',
		width: '70px'
	}
]