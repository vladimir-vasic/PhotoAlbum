<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Photo Album</title>

<!-- <script type="text/javascript" src="js/jquery.js"></script> -->

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
</head>
<body>
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
<%-- 						<img src="data:image/jpg/png;base64,<c:out value='${Picture.contentString}'/>" width=50 height=50 alt='${Picture.name}'/> --%>
						<a href="#" onclick="OpenNewWindow('/photoAlbum/${User.userId}/getPictureContent.htm?pictureId=${Picture.pictureId}'); return true;">
							<img src="/photoAlbum/${User.userId}/getPictureContent.htm?pictureId=${Picture.pictureId}" width=50 height=50 alt='${Picture.name}'/>
						</a>
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
						<a href="#" onclick="OpenNewWindow('/photoAlbum/${User.userId}/getPictureContent.htm?pictureId=${Picture.pictureId}'); return true;">
							<img src="/photoAlbum/${User.userId}/getPictureContent.htm?pictureId=${Picture.pictureId}" width=50 height=50 alt='${Picture.name}'/>
						</a> 
					</c:forEach></td>
			</tr>
		</table>
	</form:form>

</body>
</html>