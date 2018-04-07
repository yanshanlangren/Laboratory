<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="none" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String ctx = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<title>Data Tree</title>

<script src="<%=ctx %>/js/jquery-3.1.1.js"></script>
<script src="<%=ctx %>/js/jstree/jstree.min.js"></script>
<link rel="stylesheet" href="<%=ctx %>/js/jstree/themes/default/style.min.css" />
<script type="text/javascript">

	$(document).ready(function(){
		var to = false;
		$('#search').keyup(function () {
			if(to) { clearTimeout(to); }
			to = setTimeout(function () {
				var v = $('#search').val();
				$('#jstree_div').jstree(true).search(v);
			}, 250);
		});
		
	});


	
	function getDate(type){
		$($('#jstree_div').jstree(true)).trigger("destroy");
		$('#jstree_div').jstree(
				{ 'core' : {
				    'data' : {
				    	"url":"<%=ctx %>/alert/statustree/prd/"+type,
				    	"dataType" : "json"
				    }
			 },
				"plugins" : [
				    "contextmenu", "dnd", "search",
				    "state", "types", "wholerow"
				  ]}
			 );
		//
	}
</script>
</head>
<body>
<c:set var="abc" value="abc"/>
	<input type="button" value="Get Lastest" onclick="javascript:getDate('lastest');"/>
	<input type="button" value="Get All" onclick="javascript:getDate('all');"/>
	<input type="text" id="search" width="10" placeholder="search"/>
	<div id="jstree_div"></div>
</body>
</html>