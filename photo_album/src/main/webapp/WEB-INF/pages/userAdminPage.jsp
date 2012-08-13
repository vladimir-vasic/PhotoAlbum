<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>User administration</h1>

	<c:url var="addUserUrl" value="/rest/newuser" />
	<c:url var="editUserUrl" value="/rest/viewallusers" />
	<c:url var="deleteUserUrl" value="/rest/viewallusers" />

	<table>
		<tr>
			<td><a href="${addUserUrl}">Add user</a></td>
		</tr>

		<tr>
			<td><a href="${editUserUrl}">Edit user</a></td>
		</tr>

		<tr>
			<td><a href="${deleteUserUrl}">Delete user</a></td>
		</tr>
	</table>


</body>
</html>