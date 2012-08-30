<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="styles/styles.css" rel="stylesheet" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Album</title>
<script language="javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js">
	function showPic(sUrl) {
		var x, y;
		x = event.clientX;
		y = event.clientY;
		document.getElementById("Layer1").style.left = x;
		document.getElementById("Layer1").style.top = y;
		document.getElementById("Layer1").innerHTML = "<img height=200 width=400 src=\"" + sUrl + "\">";
		document.getElementById("Layer1").style.display = "block";
	}
	function hiddenPic() {
		document.getElementById("Layer1").innerHTML = "";
		document.getElementById("Layer1").style.display = "none";
	}

	function newWin(picture) {
		var newWindow = window.open("", "pictureViewer", "scrollbars=yes, resizable=yes, width=300, height=300");
		newWindow.document.writeln("<html>_____________________________________<body style='
		: 0 0 0 0;'>cument.writeln("_$tag___________________________________________$tag__$tag__________________________");
		newWindow.document.writeln("<a href='javascript:window.close();'>");
		newWindow.document
				.writeln("<img src='data:image/jpg/png;base64,_$tag__valu_____________________________________close' id='big_______________________$tag_______________
</script>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/include/header.jsp"%>
		<%@ include file="/WEB-INF/include/sidebar.jsp"%>
		<div class="content">
			<div id="Layer1"
				style="display: none; position: absolute; z-index: 1;"></div>
			<p class="title">> > > Update Photo Album < < <</p>
			<br /> <br />
			<fieldset>
				<form:form modelAttribute="UserPictures4Display" method="GET"
					action="${allUserPhotoAlbumsUrl}">
					<table>
						<tr>
							<td align="left">Photo Album Name:</td>
							<td><form:input path="userPictureAlbum.albumName" /></td>
						</tr>

						<c:forEach
							items="${UserPictures4Display.userPictureAlbum.albumPictures}"
							var="Picture">
							<tr align="left">
								<td width="200">${Picture.name}</td>
								<td align="center"><img
									src="data:image/jpg/png;base64,<c:out value='${Picture.contentString}'/>"
									width=50 height=50 alt='${Picture.name}' /></td>
							</tr>
						</c:forEach>
						<tr>
							<td align="center" colspan="2"><a
								href="<c:url value="addPictureToPhotoAlbum.htm?albumId=${UserPictures4Display.userPictureAlbum.albumId}"/>">Add
									Picture To Photo Album</a></td>
						</tr>
					</table>

					<table>
						<tr>
							<td>Friends Pictures</td>
						</tr>
						<tr>
							<td><c:forEach
									items="${UserPictures4Display.friendsPictures}" var="Picture">
									<a href="javascript:newWin('${Picture.contentString}')"><img
										src="data:image/jpg/png;base64,<c:out value='${Picture.contentString}'/>"
										width=50 height=50 alt='${Picture.name}' /></a>
								</c:forEach></td>
						</tr>
					</table>
				</form:form>
			</fieldset>
		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>
