<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Album</title>
</head>
<body>
	<h1>Update Photo Album</h1>
	<c:url var="addPictureToPhotoAlbumUrl" value="/rest/${User.userId}/updatePhotoAlbum/${PictureAlbum.albumId}/addPictureToPhotoAlbum.htm" />
	<c:url var="userAdminUrl" value="/rest/userAdmin.htm" />
	<form:form modelAttribute="PictureAlbum" method="GET" action="${updateUserUrl}">
		<table>
			<tr>
				<td align="left">Photo Album Name:</td>
				<td><form:input path="albumName" /></td>
			</tr>
			<tr>
				<td>
					<select name="pictureId" size="2">
						<c:forEach items="${PictureAlbum.albumPictures}" var="Picture">
							<option value="${Picture.pictureId}">${Picture.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<c:forEach items="${PictureAlbum.albumPictures}" var="Picture">
						 <img src="data:image/jpg/png;base64,<c:out value='${Picture.contentString}'/>" width=50 height=50 alt='${Picture.name}'/>
					</c:forEach>
				</td>
			</tr>
		</table>
		<input type="submit" value="Add Picture To Photo Album" onClick="this.form.action = '${addPictureToPhotoAlbumUrl}';" />
		<input type="submit" value="Back To Admin" onClick="this.form.action = '${userAdminUrl}';" />
		<table>
			<tr>
				<td>Friends Pictures</td>
			</tr>
			<tr>
				<td>
					<img src="http://student.eepis-its.edu/~putrizesi/Tugas%20UAS/robot.png" width=50 height=50 alt=robot/>
					<img src="data:image/jpg/png;base64, /rest/1/updatePhotoAlbum/1/getFriendsPictures.htm" width=50 height=50 alt='neka_slika'/>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>