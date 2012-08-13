<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<c:url var="login" value="/login" />
	<form:form modelAttribute="User" method="POST" action="${login}">
		<p>Login</p>
		<br />
		<br />
		<fieldset>
			<table align="center">

				<tr>
					<td align="left"><label for="userName">Username:</label></td>
					<td><input type="text" name="userName" id="userName" /></td>
				</tr>
				<tr>
					<td align="left"><label for="userPassword">Password:</label></td>
					<td><input type="password" name="userPassword"
						id="userPassword" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"></td>
					<td><input type="reset" value="Cancel" /></td>
				</tr>
			</table>
		</fieldset>
	</form:form>
</body>
</html>