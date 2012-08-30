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
			<h1>Update Photo Album</h1>
			<c:url var="addPictureToPhotoAlbumUrl"
				value="addPictureToPhotoAlbum.htm?userId=${User.userId}&albumId=${PictureAlbum.albumId}" />
			<c:url var="userAdminUrl" value="/rest/userAdmin.htm" />
			<form:form modelAttribute="PictureAlbum" method="GET"
				action="${updateUserUrl}">
				<table>
					<tr>
						<td align="left">Photo Album Name:</td>
						<td><form:input path="albumName" /></td>
					</tr>
					<tr>
						<td><select name="pictureId" size="2">
								<c:forEach items="${PictureAlbum.albumPictures}" var="Picture">
									<option value="${Picture.pictureId}">${Picture.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><c:forEach items="${PictureAlbum.albumPictures}"
								var="Picture">
								<img
									src="data:image/jpg/png;base64,<c:out value='${Picture.contentString}'/>"
									width=50 height=50 alt='${Picture.name}' />
							</c:forEach></td>
					</tr>
				</table>
				<input type="submit" value="Add Picture To Photo Album"
					onClick="this.form.action = '${addPictureToPhotoAlbumUrl}';" />
				<input type="submit" value="Back To Admin"
					onClick="this.form.action = '${userAdminUrl}';" />
				<table>
					<tr>
						<td>Friends Pictures</td>
					</tr>
					<tr>
						<td><img
							src="http://student.eepis-its.edu/~putrizesi/Tugas%20UAS/robot.png"
							width=50 height=50 alt=robot /> <img
							src="data:image/jpg/png;base64, getFriendsPictures.htm?userId=1&albumId=1"
							width=50 height=50 alt='neka_slika' /></td>
					</tr>
				</table>
			</form:form>
		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>