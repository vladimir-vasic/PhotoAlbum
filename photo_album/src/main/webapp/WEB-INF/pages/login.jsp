<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<form:form method="post"  commandName="login">
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
					<td><input type="submit" value="Submit"></td>
					<td><input type="reset" value="Cancel" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>