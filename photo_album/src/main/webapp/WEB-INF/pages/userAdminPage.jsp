<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="styles/styles.css" rel="stylesheet" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo album</title>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/include/header.jsp"%>
		<%@ include file="/WEB-INF/include/sidebar.jsp"%>
		<div class="content">

			<h1>User administration</h1>

			<c:url var="addUserUrl" value="newUser.htm" />
			<c:url var="editUserUrl" value="viewAllusers.htm" />
			<c:url var="deleteUserUrl" value="viewAllusers.htm" />

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


		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>