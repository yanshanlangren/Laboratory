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
<title>Alert Status Overview</title>

<link rel="stylesheet" href="<%=ctx %>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=ctx %>/css/dashboard.css" />

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Alert Status</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Help</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
      <div class="container-fluid">
      	<div id="env_tabs" class="row">
	    	<ul class="nav nav-pills nav-justified" role="tablist">
	    	<li role="presentation" data-toggle="tab"><a href="#" onclick="javascript:env='prd';overview();">PRD</a></li>
			<li role="presentation" class="active" data-toggle="tab"><a href="#" onclick="javascript:env='acc';overview();">ACC</a></li>	   
			</ul>
			<ul class="nav nav-pills nav-justified" role="tablist">
			  <li role="presentation" class="active" data-toggle="tab"><a href="#" onclick="javascript:dm='ovcp';overview();">OVCP</a></li>
			  <li role="presentation" data-toggle="tab"><a href="#" onclick="javascript:dm='nedt';overview();">NEDT</a></li>
			  <li role="presentation" data-toggle="tab"><a href="#" onclick="javascript:dm='bijs';overview();">BIJS</a></li>
			  <li role="presentation" data-toggle="tab"><a href="#" onclick="javascript:dm='reis';overview();">REIS</a></li>
			  <li role="presentation" data-toggle="tab"><a href="#" onclick="javascript:dm='reis';overview();">DPK</a></li>
			</ul>
		</div>
	</div>
    </nav>
	
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar" style="margin-top:80px;">
          <ul class="nav nav-sidebar">
          	<li class="active"><a href="#" onclick="javascript:overview();">Overview<span class="sr-only">(current)</span></a></li>
 			
            <li data-toggle="tab"><a href="#" onclick="javascript:loadPage('qmgrstatus')">QMGR Status</a></li>
            <li data-toggle="tab"><a href="#" onclick="javascript:loadPage('endpointstatus')">EndPoints</a></li>
            <li data-toggle="tab"><a href="#" onclick="javascript:loadPage('stuckfilestatus')">StuckFiles</a></li>
         
            <li data-toggle="tab"><a href="#" onclick="javascript:$('#main').load('<%=ctx %>/datatree_'+env)">Data Tree</a></li>
            
          </ul>
        </div>
        <div id="main" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="margin-top:80px;">
          
        </div>
      </div>
    </div>

<script language="javascript" type="text/javascript" src="<%=ctx %>/js/jquery-3.1.1.js"></script>
<script language="javascript" type="text/javascript" src="<%=ctx %>/js/bootstrap.min.js"></script>
<script language="javascript" type="text/javascript">
	var dm="";
	var env="";
	$(document).ready(function(){
		/*$("#env_tabs a").click(function(e){
			alert("a");
			e.preventDefault();
			$(this).tab("");
		});*/
		
		dm="ovcp";
		env="acc";
		
		overview();
		
	});
	
	function overview(){
		$("#main").html("");
		$.get("<%=ctx %>/view/"+dm+"/"+env+"/qmgrstatus", function(data){
			$("#main").append(data);
		});
		$.get("<%=ctx %>/view/"+dm+"/"+env+"/stuckfilestatus", function(data){
			$("#main").append(data);
		});
		$.get("<%=ctx %>/view/"+dm+"/"+env+"/endpointstatus", function(data){
			$("#main").append(data);
		});
	}
	
	function loadPage(page){
		$("#main").html("");
		javascript:$('#main').load('<%=ctx %>/view/'+dm+"/"+env+"/"+page)
	}
</script>
</body>
</html>