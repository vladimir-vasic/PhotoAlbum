<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Album</title>
<script language="javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js">
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
		newWindow.document.writeln("<html>");
	    newWindow.document.writeln("<body style='margin: 0 0 0 0;'>");
	    newWindow.document.writeln("<a href='javascript:window.close();'>");
	    newWindow.document.writeln("<img src='data:image/jpg/png;base64,<c:out value='" + picture + "'/>' alt='Click to close' id='bigImage'/>");
	    newWindow.document.writeln("</a>");
	    newWindow.document.writeln("</body></html>");
	    newWindow.document.close();
	}
	
</script>
</head>
<body>
	<div id="Layer1" style="display: none; position: absolute; z-index: 1;"></div>
	<h1>Update Photo Album</h1>
	<c:url var="addPictureToPhotoAlbumUrl" value="/${User.userId}/updatePhotoAlbum/${UserPictures4Display.userPictureAlbum.albumId}/addPictureToPhotoAlbum.htm" />
	<c:url var="allUserPhotoAlbumsUrl" value="/${User.userId}/allUserPhotoAlbums.htm" />
	<form:form modelAttribute="UserPictures4Display" method="GET" action="${allUserPhotoAlbumsUrl}">
		<table>
			<tr>
				<td align="left">Photo Album Name:</td>
				<td><form:input path="userPictureAlbum.albumName" /></td>
			</tr>
			<tr>
				<td><select name="pictureId" size="2">
						<c:forEach items="${UserPictures4Display.userPictureAlbum.albumPictures}" var="Picture">
							<option value="${Picture.pictureId}">${Picture.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><c:forEach items="${UserPictures4Display.userPictureAlbum.albumPictures}" var="Picture">
						<img src="data:image/jpg/png;base64,<c:out value='${Picture.contentString}'/>" width=50 height=50 alt='${Picture.name}'/>
					</c:forEach></td>
			</tr>
		</table>
		<input type="submit" value="Add Picture To Photo Album" onClick="this.form.action = '${addPictureToPhotoAlbumUrl}';" />
		<input type="submit" value="Back To Photo Albums" onClick="this.form.action = '${allUserPhotoAlbumsUrl}';" />
		<table>
			<tr>
				<td>Friends Pictures</td>
			</tr>
			<tr>
				<td><c:forEach items="${UserPictures4Display.friendsPictures}" var="Picture">
						<a href="javascript:newWin('${Picture.contentString}')"><img src="data:image/jpg/png;base64,<c:out value='${Picture.contentString}'/>" width=50 height=50 alt='${Picture.name}'/></a>
					</c:forEach></td>
			</tr>
		</table>
	</form:form>
</body>
</html>