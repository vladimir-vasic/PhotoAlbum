<%@ include file="/WEB-INF/include/include.jsp"%>

<div class="header">
	<br /> <br />
	<h1 class="header">Photo album</h1>
	<br />

	<security:authorize ifNotGranted="ROLE_USER, ROLE_SUPERVISOR">
		<a href="login.htm"> Login </a>
	</security:authorize>

	<security:authorize ifAllGranted="ROLE_USER">
		Welcome <security:authentication property="principal.username" />
		&nbsp;|&nbsp;
		<a href="index.htm">Home</a>&nbsp; | &nbsp;
		<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
	</security:authorize>

	<security:authorize ifAllGranted="ROLE_SUPERVISOR">
		Welcome <security:authentication property="principal.username" />
		&nbsp;|&nbsp;
		<a href="index.htm">Home</a>&nbsp; | &nbsp;
		<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
	</security:authorize>
	&nbsp;
</div>
