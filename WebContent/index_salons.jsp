<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script>
	function drawTable(data) {
		for (var i = 0; i < data.length; i++) {
			$("#table_salons").append('<tr><td>' + data[i].salonName + '</td><td>' + data[i].address + '</td><td>' 
	        		+ data[i].phoneNumber + '</td><td>' + data[i].email + '</td><td>' + data[i].webAddress + '</td><td>' 
					+ data[i].pib + '</td><td>' + data[i].companyNumber+ '</td><td>' + data[i].accountNumber + '</td></tr>');
		}
		
		$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", function(data, status) {
		
		});
	}

	$(document).ready(function () {
		$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", function(data, status) {
			if(data == "IndexServlet") {
				window.location.href = data;
				return;
			}
		});
		
		$.get("${pageContext.request.contextPath}/GetSalonsIndexServlet", function (data, status) {
			$("#table_salons tbody").empty();
			drawTable(data);
		});
	});
</script>
<title>Svi saloni</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
	<h1>Svi saloni</h1>
	<table class="table table-condensed" id="table_salons">
		<thead>
			<tr>
				<th>Ime</th>
				<th>Adresa</th>
				<th>Telefon</th>
				<th>Email</th>
				<th>Sajt</th>
				<th>PIB</th>
				<th>Maticni broj</th>
				<th>Broj ziro racuna</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
	</div>
</body>
</html>