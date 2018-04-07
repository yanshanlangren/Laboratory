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
<title>Endpoint Status</title>

<link rel="stylesheet" href="<%=ctx %>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=ctx %>/css/dashboard.css" />

</head>
<body>
	
	<h2 class="sub-header">Endpoint Status</h2>
	<div id="endpointstatus">
	        
    </div>
<script language="javascript" type="text/javascript">
	$(document).ready(function(){
		$.getJSON("<%=ctx %>/alert/statusjson/prd/lastest", function(json){

			processEndpoint(json, "su025v239");
			processEndpoint(json, "su025v255");
						
		});
	});
	
	function processEndpoint(json, node){

		var endpoints = json["Root"][node]["Alert"][0]["ENDPOINTS"];
		$("#endpointstatus").append("<h4>"+node+"</h4>")
		
		for(var i=0;i<endpoints.length;i++){
			
			for(var item in endpoints[i]){
				var ep_name = item;
				var ep_data = endpoints[i][item];
				var fail_event_num = ep_data["FAILED_EVENTS_NUM"];
				var msg_endpoints = ep_data["MSGENDPOINT"];
				
				if($.isEmptyObject(fail_event_num)){
					fail_event_num = "<span style=\"color:red\">FAILED_EVENTS_NUM:N/A</span>";
				}else{
					if(fail_event_num > 0){
						fail_event_num = "<span style=\"color:red\">FAILED_EVENTS_NUM:"+fail_event_num+"</span>"
					}else{
						fail_event_num = "<span>FAILED_EVENTS_NUM:"+fail_event_num+"</span>"
					}
				}
				$("#endpointstatus").append("<div class=\"panel panel-primary\">"
						+"  <div class=\"panel-heading\"><b>"+ep_name+"</b></div>");
						
				//Endpoint
				if($.isEmptyObject(msg_endpoints) == false){
					$("#endpointstatus").append("  <div class=\"panel-body\">"
							+"   <p>"+fail_event_num+"</p></div><div class=\"table-responsive\">"
							+"  <table id=\""+node+"_"+ep_name+"_endpoint_table\" class=\"table table-striped\"><thead><tr><th>#</th><th>Message Endpoint Name</th><th>Status</th></tr></thead><tbody></tbody></table></div></div>");
					for(var j=0; j<msg_endpoints.length; j++){
						var msg = msg_endpoints[j]
						for(var item_2 in msg){
							var msg_name = item_2;
							var msg_status = msg[msg_name]["status"];
							if(msg_status.toUpperCase() == "ACTIVE"){
								msg_status = "<span style=\"color:green\">"+msg_status+"</span>"
							}else{
								msg_status = "<span style=\"color:red\">"+msg_status+"</span>"
							}
							$("#"+node+"_"+ep_name+"_endpoint_table").children("tbody").append("<tr><td>"+(j+1)+"</td> <td>"+msg_name+"</td><td>"+msg_status+"</td></tr>");
						}
					}
				}else if($.isArray(ep_data)){
					//Webserver 
					$("#endpointstatus").append("<div class=\"table-responsive\">"
							+"  <table id=\""+node+"_"+ep_name+"_endpoint_table\" class=\"table table-striped\"><thead><tr><th>#</th><th>Desc</th><th>Status</th></tr></thead><tbody></tbody></table></div></div>");
						
					for(var j=0; j<ep_data.length; j++){
						var item_2 = ep_data[j];
						var desc = item_2["desc"];
						var status = item_2["status"];
						if(status.toUpperCase() == "ACTIVE"){
							status = "<span style=\"color:green\">"+status+"</span>"
						}else{
							status = "<span style=\"color:red\">"+status+"</span>"
						}
						$("#"+node+"_"+ep_name+"_endpoint_table").children("tbody").append("<tr><td>"+(j+1)+"</td> <td>"+desc+"</td><td>"+status+"</td></tr>");
						
					}
				}
			}
			
		}
	}
	
	
	
</script>
</body>
</html>