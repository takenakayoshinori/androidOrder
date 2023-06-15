
var estimateSpredsheets = jspreadsheet(document.getElementById('estimateSpredsheet'), {
   toolbar:[
        {
            type: 'i',
            content: 'undo',
            onclick: function() {
                estimateSpredsheets.undo();
            }
        },
        {
            type: 'i',
            content: 'redo',
            onclick: function() {
                estimateSpredsheets.redo();
            }
        },
        {
            type: 'i',
            content: 'save',
            onclick: function () {
                estimateSpredsheets.download();
            }
        },
        {
            type: 'select',
            k: 'font-family',
            v: ['Arial','Verdana']
        },
        {
            type: 'select',
            k: 'font-size',
            v: ['9px','10px','11px','12px','13px','14px','15px','16px','17px','18px','19px','20px']
        },
        {
            type: 'i',
            content: 'format_align_left',
            k: 'text-align',
            v: 'left'
        },
        {
            type:'i',
            content:'format_align_center',
            k:'text-align',
            v:'center'
        },
        {
            type: 'i',
            content: 'format_align_right', 
            k: 'text-align',
            v: 'right'
        },
        {
            type: 'i',
            content: 'format_bold',
            k: 'font-weight',
            v: 'bold'
        },
        {
            type: 'color',
            content: 'format_color_text',
            k: 'color'
        },
        {
            type: 'color',
            content: 'format_color_fill',
            k: 'background-color'
        },
    ],
	filters: true,
	data: JSON.parse($('#estimes').val()),
	worksheetId: 'estimate',
	worksheetName: '予定',
    columns: masterColums

});