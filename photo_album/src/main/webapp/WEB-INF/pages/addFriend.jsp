<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Album</title>

</head>
<body>
	<h1>Add Friend</h1>
	<c:url var="friendAddedUrl" value="friendAdded?userId=${selUserId}" />
	<form:form modelAttribute="userList" method="POST" action="${friendAddedUrl}">
		<table>
			<tr>
				<td>
					<select name="selUserId" size="2">
						<c:forEach items="${userList}" var="User">
							<option value="${User.userId}">${User.userName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<input type="submit" value="Add Friend" onClick="this.form.action = '${friendAddedUrl}';"/>
	</form:form>
</body>
</html>