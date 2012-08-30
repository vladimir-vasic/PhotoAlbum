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

			<h1>User Photo Albums</h1>

			<c:url var="addUserPhotoAlbumUrl"
				value="addUserPhotoAlbum.htm?userId=${User.userId}" />
			<c:url var="updateUserPhotoAlbumUrl"
				value="updateUserPhotoAlbum.htm?userId=${User.userId}&albumId=${albumId}" />
			<c:url var="deleteUserPhotoAlbumUrl"
				value="deleteUserPhotoAlbum.htm?userId=${User.userId}&albumId=${albumId}" />
			<form:form modelAttribute="User" method="GET"
				action="${addUserPhotoAlbumUrl}">
				<table>
					<tr>
						<td><select name="albumId" size="2">
								<c:forEach items="${User.userAlbums}" var="PictureAlbum">
									<option value="${PictureAlbum.albumId}">${PictureAlbum.albumName}</option>
								</c:forEach>
						</select></td>
					</tr>
				</table>
				<input type="submit" value="Add New Photo Album"
					onClick="this.form.action = '${addUserPhotoAlbumUrl}';" />
				<input type="submit" value="Edit Photo Album"
					onClick="this.form.action = '${updateUserPhotoAlbumUrl}';" />
				<input type="submit" value="Delete Photo Album"
					onClick="this.form.action = '${deleteUserPhotoAlbumUrl}';" />
			</form:form>
		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>