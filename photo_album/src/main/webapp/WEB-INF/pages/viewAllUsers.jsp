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

	<h1>Edit/Delete User</h1>

	<c:url var="editUserUrl" value="/rest/edituser?userId=${userId}" />
	<c:url var="deleteUserUrl" value="/rest/deleteuser?userId=${userId}" />
	<form:form modelAttribute="userList" method="GET" action="${editUserUrl}">
		<table>
			<tr>
				<td>
					<select name="userId" size="2">
						<c:forEach items="${userList}" var="User">
							<option value="${User.userId}">${User.userName}</option>
						</c:forEach>
					</select>
				<td>
			</tr>
		</table>
		<input type="submit" value="Edit" onClick="this.form.action = '${editUserUrl}';"/>
		<input type="submit" value="Delete" onClick="this.form.action = '${deleteUserUrl}';"/>
	</form:form>

</body>
</html>