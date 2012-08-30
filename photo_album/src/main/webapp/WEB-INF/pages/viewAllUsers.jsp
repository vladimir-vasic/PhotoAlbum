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

			<p class="title">> > > User Administration < < <</p>
			<br /> <br />
			<fieldset>
				<form:form modelAttribute="userList" method="GET">
					<table align="center" border="1">
						<tr align="left">
							<th>Username</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						<c:forEach var="User" items="${userList}">
							<tr align="left">
								<td width="200">${User.userName}</td>
								<td width="70"><a
									href="<c:url value="editUser.htm?userId=${User.userId}"/>">Edit</a></td>
								<td width="70"><a
									href="<c:url value="deleteUser.htm?userId=${User.userId}"/>">Delete</a></td>
							</tr>
						</c:forEach>
					</table>
				</form:form>
			</fieldset>

		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>
