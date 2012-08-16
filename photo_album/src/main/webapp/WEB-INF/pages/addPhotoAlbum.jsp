<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Album</title>
<!-- <script type="text/javascript"> 
// 	var modelUserId;
// 	function doMyBusiness() {
//     	showVar = '<c:out value="${PictureAlbum.albumOwner.userId}"/>';
// 	}
//     window.onload = doMyBusiness;
	</script>  -->
</head>
<body>
	<h1>Add New Photo Album</h1>
	<c:url var="userPhotoAlbumAdded" value="addUserPhotoAlbum" />
	<form:form modelAttribute="PictureAlbum" method="POST" action="${addUserPhotoAlbumUrl}">
		<table>
			<tr>
				<td align="left">Photo Album Name:</td>
				<td><form:input path="albumName" /></td>
			</tr>
		</table>
		<input type="submit" value="Add User Photo Album" onClick="this.form.action = '${userPhotoAlbumAdded}';"/>
	</form:form>
</body>
</html>