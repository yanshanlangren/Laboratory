<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String ctx = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="<%=ctx %>/js/jquery-3.1.1.js"></script>
<script src="<%=ctx %>/js/jstree/jstree.min.js"></script>
<link rel="stylesheet" href="<%=ctx %>/js/jstree/themes/default/style.min.css" />
<script type="text/javascript">
	
</script>
</head>
<body>
	<form action="<%=ctx %>/user" method="post">
		<!-- <input type="hidden" name="_method" value="put"/> -->
		Name:<input type="text" name="name" value=""/><br/>
		Age:<input type="text" name="age" value=""/>
		<input type="submit" value="Submit"/>
	</form>
	<c:if test="${user != null}">
		info:
		Name:${user.name }
		Age:${user.age }
	</c:if>
	
</body>
</html>