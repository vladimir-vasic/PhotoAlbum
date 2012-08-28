<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Album</title>
</head>
<body>
	<c:if test="${not empty param.login_error}">
		<font color="red"> Your login attempt was not successful, try
			again.<br /> <br /> Reason: <c:out
				value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
		</font>
	</c:if>
	<form action="<c:url value='/j_spring_security_check'/>" method="post">
		<p>Login</p>
		<br /> <br />
		<fieldset>
			<table align="center">

				<tr>
					<td align="left"><label for="j_username">Username:</label></td>
					<td><input type="text" name="j_username" id="j_username" /></td>
				</tr>
				<tr>
					<td align="left"><label for="j_password">Password:</label></td>
					<td><input type="password" name="j_password" id="j_password" /></td>
				</tr>
				<tr>
					<td><input type='checkbox' name='_spring_security_remember_me'
						class="checkbox" value="true" checked="checked"/> Remember me.</td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"></td>
					<td><input type="reset" value="Cancel" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>