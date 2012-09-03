<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<h1>Add New Picture to Album</h1>
			<c:url var="pictureAddToAlbum" value="addPictureToPhotoAlbum.htm" />
			<form:form modelAttribute="UploadItem" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>Upload Fields</legend>
					<p>
						<form:input id="fileUpload" path="fileData" type="file" />
					</p>

					<p>
						<input type="submit" />
					</p>

				</fieldset>
			</form:form>
		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>