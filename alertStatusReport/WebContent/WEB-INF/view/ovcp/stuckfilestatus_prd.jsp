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
<title>Stuckfiles Report</title>

<link rel="stylesheet" href="<%=ctx %>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=ctx %>/css/dashboard.css" />

</head>
<body>
	
	<h2 class="sub-header">Stuckfiles Report</h2>
	<h4>su025v236</h4>
	<div class="table-responsive">
	  <table id="su025v236_stuckfile_table" class="table table-striped">
	    <thead>
	      <tr>
	        <th>#</th>
	        <th>Stuckfile</th>
	        <th>Number</th>
	      </tr>
	    </thead>
	    <tbody>
	      
	    </tbody>
	  </table>
	</div>
	
	<h4>su025v252</h4>
	<div class="table-responsive">
	  <table id="su025v252_stuckfile_table" class="table table-striped">
	    <thead>
	      <tr>
	        <th>#</th>
	        <th>Stuckfile</th>
	        <th>Number</th>
	      </tr>
	    </thead>
	    <tbody>
	                     
	    </tbody>
	  </table>
	</div>

<script language="javascript" type="text/javascript">
	$(document).ready(function(){
		$.getJSON("<%=ctx %>/alert/statusjson/prd/lastest", function(json){

			processStuckfile(json, "su025v236");
			processStuckfile(json, "su025v252");
						
		});
	});
	
	function processStuckfile(json, node){

		if(json["Root"][node]!=null){
			var stuckfiles = json["Root"][node]["Alert"][0]["STUCKFILES"];
			
			for(var i=0;i<stuckfiles.length;i++){
				for(var item in stuckfiles[i]){
					var sf_name = item;
					var sf_num = stuckfiles[i][item];
					$("#"+node+"_stuckfile_table").children("tbody").append("<tr><td>"+(i+1)+"</td> <td>"+sf_name+"</td><td>"+sf_num+"</td></tr>");
				}
				
			}
		}
	}
	
	
	
</script>
</body>
</html>