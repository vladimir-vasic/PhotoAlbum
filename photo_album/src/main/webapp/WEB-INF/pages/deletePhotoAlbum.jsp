<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<link href="styles/styles.css" rel="stylesheet" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo album</title>
<script type="text/javascript">
	function disableFields() {
		document.getElementById("albumName").readOnly = true;
	}
	window.onload = disableFields;
</script>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/include/header.jsp"%>
		<%@ include file="/WEB-INF/include/sidebar.jsp"%>
		<div class="content">
			<p class="title">> > > Delete Photo Album < < <</p>
			<br /> <br />
			<form:form modelAttribute="PictureAlbum" method="POST">
				<fieldset>
					<table align="center" border="0">
						<tr>
							<td align="left">Album Name:</td>
							<td><form:input id="albumName" path="albumName" /></td>
						</tr>
						<td align="center" colspan="2"><br /> <input type="submit"
							value="Delete" /></td>
					</table>

				</fieldset>
			</form:form>

		</div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>