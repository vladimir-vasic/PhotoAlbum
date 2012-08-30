<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="styles/styles.css" rel="stylesheet" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo album</title>
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
	<div class="container">
		<%@ include file="/WEB-INF/include/header.jsp"%>
		<%@ include file="/WEB-INF/include/sidebar.jsp"%>
		<div class="content">

			<h1>Delete User</h1>

			<c:url var="deleteUserUrl"
				value="deleteUser.htm?userId=${User.userId}" />
			<form:form modelAttribute="User" method="POST"
				action="${deleteUserUrl}">
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
		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>