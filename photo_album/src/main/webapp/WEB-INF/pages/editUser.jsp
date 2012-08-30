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

			<p class="title">> > > Update User < < <</p>
			<br /> <br />
			<fieldset>

				<form:form modelAttribute="User" method="POST">

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

				</form:form>

			</fieldset>

		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>