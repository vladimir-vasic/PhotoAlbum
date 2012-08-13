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

	<h1>Update User</h1>

	<c:url var="updateUserUrl" value="/rest/updateuser?userId=${User.userId}" />
	<form:form modelAttribute="User" method="POST" action="${updateUserUrl}">
		<table>
			<tr>
				<td align="left">User Name:</td>
				<td><form:input path="userName" /></td>
			</tr>

			<tr>
				<td align="left">User Password:</td>
				<td><form:input path="userPassword" /></td>
			</tr>

			<tr>
				<td align="left">User Email:</td>
				<td><form:input path="userEmail" /></td>
			</tr>
		</table>

		<input type="submit" value="Update" />
	</form:form>

</body>
</html>