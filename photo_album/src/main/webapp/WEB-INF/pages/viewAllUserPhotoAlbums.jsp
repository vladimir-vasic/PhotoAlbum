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

			<p class="title">> > > User Photo Albums < < <</p>
			<br /> <br />
			<fieldset>
				<form:form modelAttribute="User" method="GET">
					<table align="center" border="1">
						<tr align="left">
							<th>Album</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						<c:forEach items="${User.userAlbums}" var="PictureAlbum">
							<tr align="left">
								<td width="200">${PictureAlbum.albumName}</td>
								<td width="70"><a
									href="<c:url value="updateUserPhotoAlbum.htm?albumId=${PictureAlbum.albumId}"/>">Edit</a></td>
								<td width="70"><a
									href="<c:url value="deleteUserPhotoAlbum.htm?albumId=${PictureAlbum.albumId}"/>">Delete</a></td>
							</tr>
						</c:forEach>
						<tr>
							<td align="center" colspan="3"><a
								href="<c:url value="addUserPhotoAlbum.htm"/>">Add new album</a></td>
						</tr>
					</table>
				</form:form>
			</fieldset>

		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>

</body>
</html>
