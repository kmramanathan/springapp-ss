var printAll = false;

function verifyCheckBoxesForPrint () {
    alert("printAll: " + printAll);
	if (printAll) {
    	return true;
    }

    // loop through checkboxes and make sure at least one is checked
	//alert("size: " + document.forms[0].elements.length);

	var s = "";
    for (var i = 0; i < document.forms[0].elements.length; i++) {
    	s += document.forms[0].elements[i].name + ":\n";
        if (document.forms[0].elements[i].name == 'resultsToPrint' && document.forms[0].elements[i].checked) {   
            return true;
        }
    }
    //alert(s);
    alert('Please check the results you wish to print.');
    return false;
}

function setPageSize (pageSize) {
	location.search = "pageSize=" + pageSize;
	//window.location=location.href + "&pageSize=" + pageSize;
	//window.location.reload();
	//var oldhref = location.href;
	//var query = location.search.substring(1);
	//var pairs = query.split(",");	
}
