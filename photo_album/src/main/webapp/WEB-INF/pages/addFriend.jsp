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

			<form:form modelAttribute="userList" method="POST">
				<p class="title">> > > Add Friend < < <</p>
				<br />
				<br />
				<fieldset>
					<table align="center" border="1">
						<tr align="left">
							<th>Username</th>
							<th>Add</th>
						</tr>
						<c:forEach var="UserFriend" items="${userList}">
							<tr align="left">
								<td width="200">${UserFriend.userName}</td>
								<td width="70"><a
									href="<c:url value="friendAdded.htm?userId=${User.userId}&selUserId=${UserFriend.userId}"/>">Add
										friend</a></td>
							</tr>
						</c:forEach>
					</table>
				</fieldset>
			</form:form>

		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>