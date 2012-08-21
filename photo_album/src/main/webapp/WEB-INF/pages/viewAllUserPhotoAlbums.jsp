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
	var showVar;
	function doMyBusiness() {
    	showVar = '<c:out value="${User.userId}"/>';
//     	alert("The variable show is " + showVar);
	}
    window.onload = doMyBusiness;
</script> 
</head>
<body>

	<h1>User Photo Albums</h1>

	<c:url var="addUserPhotoAlbumUrl" value="/rest/${userId}/addUserPhotoAlbum" />
	<c:url var="updateUserPhotoAlbumUrl" value="/rest/${userId}/updateUserPhotoAlbum/?albumId=${albumId}" />
	<c:url var="deleteUserPhotoAlbumUrl" value="/rest/${userId}/deleteUserPhotoAlbum/?albumId=${albumId}" />
	<form:form modelAttribute="User" method="GET" action="${addUserPhotoAlbumUrl}">
		<table>
			<tr>
				<td>
					<select name="albumId" size="2">
						<c:forEach items="${User.userAlbums}" var="PictureAlbum">
							<option value="${PictureAlbum.albumId}">${PictureAlbum.albumName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<input type="submit" value="Add New Photo Album" onClick="this.form.action = '${addUserPhotoAlbumUrl}';"/>
		<input type="submit" value="Edit Photo Album" onClick="this.form.action = '${updateUserPhotoAlbumUrl}';"/>
		<input type="submit" value="Delete Photo Album" onClick="this.form.action = '${deleteUserPhotoAlbumUrl}';"/>
	</form:form>
</body>
</html>