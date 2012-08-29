<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Album</title>
<script type="text/javascript">
	function disableFields() {
		document.getElementById("userName").readOnly = true;
		document.getElementById("userPassword").readOnly = true;
		document.getElementById("userEmail").readOnly = true;
	}
	window.onload = disableFields;
</script>
</head>
<body>

	<h1>Delete User</h1>

	<c:url var="deleteUserUrl" value="/deleteUser.htm?userId=${User.userId}" />
	<form:form modelAttribute="User" method="POST" action="${deleteUserUrl}">
		<table>
			<tr>
				<td align="left">User Name:</td>
				<td><form:input id="userName" path="userName" /></td>
			</tr>

			<tr>
				<td align="left">User Password:</td>
				<td><form:input id="userPassword" path="userPassword" /></td>
			</tr>

			<tr>
				<td align="left">User Email:</td>
				<td><form:input id="userEmail" path="userEmail" /></td>
			</tr>
		</table>

		<input type="submit" value="Delete" />
	</form:form>

</body>
</html>