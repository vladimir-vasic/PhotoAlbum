<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="styles/styles.css" rel="stylesheet" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Album</title>
<script type="text/javascript">
	function OpenNewWindow(bigurl)
	{
	    var newWindow = window.open("", "pictureViewer", "location=no, directories=no, fullscreen=no, menubar=no, status=no, toolbar=no, width=500, height=500, scrollbars=no");
	    newWindow.document.writeln("<html>");
	    newWindow.document.writeln("<body style='margin: 0 0 0 0;'>");
	    newWindow.document.writeln("<a href='javascript:window.close();'>");
	    newWindow.document.writeln("<img src='" + bigurl + "' alt='Click to close' id='bigImage'/>");
	    newWindow.document.writeln("</a>");
	    newWindow.document.writeln("</body></html>");
	    newWindow.document.close();
	}
</script>
<script language="javascript" type="text/javascript">
	var scrt_var = 10; 
	function updatePage(albumId) {
// 		var pom = ${UserPictures4Display.userPictureAlbum.albumId};
// 		alert("updatePhotoAlbumName.htm?albumId=${UserPictures4Display.userPictureAlbum.albumId}&newAlbumName=" + document.getElementById("albumName").value);
		location.href = "updatePhotoAlbumName.htm?albumId=${UserPictures4Display.userPictureAlbum.albumId}&newAlbumName=" + document.getElementById("albumName").value;
	}
</script>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/include/header.jsp"%>
		<%@ include file="/WEB-INF/include/sidebar.jsp"%>
		<div class="content">
			<p class="title">> > > Update Photo Album < < <</p>
			<br /> <br />
			<fieldset>
				<form:form modelAttribute="UserPictures4Display" method="GET" action="${allUserPhotoAlbumsUrl}">
					<table>
						<tr>
							<td align="left">Photo Album Name:</td>
							<td><form:input path="userPictureAlbum.albumName" id="albumName"/></td>
							<td>
								<a href="javascript:updatePage()">update</a>
							</td>
						</tr>
						<c:forEach items="${UserPictures4Display.userPictureAlbum.albumPictures}" var="Picture">
							<tr align="left">
								<td width="200">${Picture.name}</td>
								<td align="center">
									<a href="#" onclick="OpenNewWindow('getPictureContent.htm?pictureId=${Picture.pictureId}'); return true;">
										<img src="getPictureContent.htm?pictureId=${Picture.pictureId}" width=50 height=50 alt='${Picture.name}' />
									</a>
								</td>
							</tr>
						</c:forEach>

						<tr>
							<td align="center" colspan="2">
								<a href="<c:url value="addPictureToPhotoAlbum.htm?albumId=${UserPictures4Display.userPictureAlbum.albumId}"/>">Add Picture To Photo Album</a>
							</td>
						</tr>
					</table>

					<table>
						<tr>
							<td>Friends Pictures</td>
						</tr>
						<tr>
							<td>
								<c:forEach items="${UserPictures4Display.friendsPictures}" var="Picture">
									<a href="#" onclick="OpenNewWindow('getPictureContent.htm?pictureId=${Picture.pictureId}'); return true;">
										<img src="getPictureContent.htm?pictureId=${Picture.pictureId}" width=50 height=50 alt='${Picture.name}' />
									</a>
								</c:forEach>
							</td>
						</tr>
					</table>
				</form:form>
			</fieldset>
		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>

</body>
</html>
