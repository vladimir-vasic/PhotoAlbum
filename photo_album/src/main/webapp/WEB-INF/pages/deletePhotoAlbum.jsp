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
		document.getElementById("albumName").readOnly = true;
	}
	window.onload = disableFields;
</script>
</head>
<body>

	<h1>Delete User</h1>

	<c:url var="deletePhotoAlbumUrl" value="" />
	<form:form modelAttribute="PictureAlbum" method="POST" action="${deletePhotoAlbumUrl}">
		<table>
			<tr>
				<td align="left">Album Name:</td>
				<td><form:input id="albumName" path="albumName" /></td>
			</tr>
		</table>

		<input type="submit" value="Delete"/>
	</form:form>

</body>
</html>