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
	<div id="endpoint_ovcp_acc_ams" class="col-xs-6 col-sm-6 placeholder">
	<div class="text" style="text-align: center;visibility: visible;">
         <h4>AMS</h4>
      	</div>   
      	<h4>su025v423</h4>
	<div class="table-responsive">
	  <table id="su025v423_stuckfile_table" class="table table-striped">
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
	<h4>su025v424</h4>
	<div class="table-responsive">
	  <table id="su025v424_stuckfile_table" class="table table-striped">
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
    </div>
    
    <div id="endpoint_ovcp_acc_apl" class="col-xs-6 col-sm-6 placeholder">
    <div class="text" style="text-align: center;visibility: visible;">
         <h4>APL</h4>
      	</div>    
    </div>
	<h4>su025v418</h4>
	<div class="table-responsive">
	  <table id="su025v418_stuckfile_table" class="table table-striped">
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
	
	<h4>su025v419</h4>
	<div class="table-responsive">
	  <table id="su025v419_stuckfile_table" class="table table-striped">
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
		$.getJSON("<%=ctx %>/alert/statusjson/acc/lastest", function(json){

			processStuckfile(json, "su025v423");
			processStuckfile(json, "su025v418");
			processStuckfile(json, "su025v424");
			processStuckfile(json, "su025v419");
						
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