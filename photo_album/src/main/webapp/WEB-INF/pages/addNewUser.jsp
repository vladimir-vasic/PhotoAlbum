<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Create New User</h1>

<c:url var="saveUrl" value="/photoAlbum/rest/user" />
<form:form modelAttribute="userAttribute" method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td><form:label path="userName">User Name:</form:label></td>
			<td><form:input path="userName"/></td>
		</tr>

		<tr>
			<td><form:label path="userPassword">User password</form:label></td>
			<td><form:input path="userPassword"/></td>
		</tr>
		
		<tr>
			<td><form:label path="userEmail">User email</form:label></td>
			<td><form:input path="userEmail"/></td>
		</tr>
	</table>
	
	<input type="submit" value="Save" />
</form:form>

</body>
</html>