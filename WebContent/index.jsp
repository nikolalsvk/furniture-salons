<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Salon</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
		<h1>Saloni nameštaja</h1>

		<p>
			Dobrodošli na sajt salona nameštaja. Da biste videli sadržaj sajta
			potrebno je da se <a href="LoginServlet">ulogujete</a> ili <a
				href="RegisterServlet">registrujete</a>.
		</p>

		<a href="LoginAdminServlet">Admin login</a>
	</div>
</body>
</html>