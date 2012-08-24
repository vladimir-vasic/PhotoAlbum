<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Album</title>
</head>
<body>
	LOGIN WAS SUCCESSFUL
	<h3>Username : ${userName}</h3>
	<h3>Username : ${username}</h3>
	<h3>Username : ${j_username}</h3>

	<h1 class="header">PHOTOALBUM</h1>
	<br />
	<security:authorize ifNotGranted="ROLE_USER, ROLE_SUPERVISOR">
		<a href="index.htm">Home</a>&nbsp;
		|&nbsp;
		<a href="register.htm"> Registracija </a> &nbsp;|&nbsp;
		<a href="login.htm"> Login </a>
	</security:authorize>

	<security:authorize ifAllGranted="ROLE_USER">
		Welcome <security:authentication property="principal.username" />
		&nbsp;|&nbsp;
		<a href="index.htm">Home</a>&nbsp; | &nbsp;
		<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
		&nbsp; | &nbsp;
	</security:authorize>

	<security:authorize ifAllGranted="ROLE_SUPERVISOR">
		Welcome <security:authentication property="principal.username" />
		&nbsp;|&nbsp;
		<a href="index.htm">Home</a>&nbsp; | &nbsp;
		<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
	</security:authorize>
	&nbsp;
</body>
</html>