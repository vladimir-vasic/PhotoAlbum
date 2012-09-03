<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="styles/styles.css" rel="stylesheet" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Photo Album</title>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/include/header.jsp"%>
		<%@ include file="/WEB-INF/include/sidebar.jsp"%>
		<div class="content">
			<form:form modelAttribute="User" method="POST">
				<p class="title">> > > Update User < < <</p>
				<br />
				<br />
				<fieldset>
					<table border="0" align="center">
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
						<tr>
							<td align="center"><br /> <br /> <input type="submit"
								value="Update" /></td>
							<td align="center" colspan="2"><br /> <br /> <input
								type="reset" value="Cancel" /></td>
						</tr>
					</table>
				</fieldset>

				<p class="title">> > > User Friends < < <</p>
				<br />
				<br />
				<fieldset>
					<table align="center" border="1">
						<tr align="left">
							<th>Username</th>
							<th>Delete</th>
						</tr>
						<c:forEach items="${User.friends}" var="friends">
							<tr align="left">
								<td width="200">${friends.userName}</td>
								<td width="70"><a href="<c:url value=""/>">Delete</a></td>
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
