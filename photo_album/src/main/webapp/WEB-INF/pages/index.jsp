<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Photo Album</title>
<link href="<c:url value="/style/style.css"/>" rel="stylesheet" type="text/css"></link>
</head>
<body>
	<div class="container"><%@ include
			file="/WEB-INF/include/header.jsp"%>
		<%@ include file="/WEB-INF/include/sidebar.jsp"%>
		<div class="content"></div>
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
</body>
</html>
