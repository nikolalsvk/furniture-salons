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
			$("#table_services").append('<tr><td>' + data[i].name + '</td><td>' + data[i].description + '</td><td>' 
	        		+ data[i].price + '</td><td>' + 
	        		'<input class="edit" type="button" value="Izmeni" style="display:none;" data-toggle="modal" data-target="#myModal" id="' +
					data[i].name + '" /></td><td>' +
					'<input class="delete" type="button" value="Obrisi" style="display:none;" id="' + data[i].name + '" />'+
					'</td></tr>');
		}
		
		$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", function(data, status) {
			if (data.role === "admin") {
				$(".delete").show();
				$(".edit").show();
				$("#add_serv").show();
				$("#save_serv").show();
			}
			
			if (data.role === "manager") {
				$(".delete").hide();
				$(".edit").hide();
				$("#add_serv").hide();
			}
			
			if (data.role === "user") {
				$(".delete").hide();
				$(".edit").hide();
				$("#add_serv").hide();
			}
		});
		
		$(".delete").click(function (event) {
			var id = event.target.id;
			if(confirm("Da li sigurno zelite da obrisete kateogriju: " + id + "?")) {
				$.get("${pageContext.request.contextPath}/DeleteAdditionalServiceServlet", { id },
						function (data, status) {
					$("#table_services tbody").empty();
		    	    drawTable(data);
				});
			}
		});
		
		$(".edit").click(function (event) {
			editMode = true;
			$("#name").attr("readonly", true);
			
			var name = event.target.id;
			$.get("${pageContext.request.contextPath}/EditAdditionalServiceServlet", { name },
					function (data, status) {
				$("#name").val(data.name);
				$("#description").val(data.description);
				$("#price").val(data.price);
			});
		});
	};
	
	$(document).ready(function () {
		$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", function(data, status) {
			if(data == "IndexServlet") {
				window.location.href = data;
				return;
			}
		});
		
		$.get("${pageContext.request.contextPath}/GetAdditionalServicesIndexServlet", function (data, status) {
			$("#table_services tbody").empty();
			drawTable(data);
		});
		
		$("#save_cat").click(function () {
			$.post("${pageContext.request.contextPath}/IndexAdditionalServicesServlet", function (data, status) {
				
			});
		});
		
		$("#add_serv").click(function () {
			editMode = false;
			$("#name").attr("readonly", false);
			$("#name").val("");
			$("#description").val("");
			$("#price").val("");
		});
		
		$("#submit_edit").click(function () {
			if ($("#service_form").valid()) {
				var name = $("#name").val();
				var description = $("#description").val();
				var price = $("#price").val();
				if(editMode) {
					$.post("${pageContext.request.contextPath}/EditAdditionalServiceServlet", {
						name, description, price
					}, function (data, status) {
						$("#table_services tbody").empty();
						drawTable(data);
					});
					editMode = false;
					$("#myModal").modal("toggle");
				} else {
					$.post("${pageContext.request.contextPath}/AddAdditionalServiceServlet", {
						name, description, price
					}, function (data, status) {
						$("#table_services tbody").empty();
						drawTable(data);
					});
					$("#myModal").modal("toggle");
				}
			}
		});
		
		$("#service_form").validate({
			rules: {
				name: {
			    	minlength: 4,
			    	required: true
			    },
			    description: {
			    	minlength: 4,
			    	required: true
			    },
			    price: {
			    	number: true,
			    	required: true
			    }
			}
		});
	});
</script>
<title>Dodatne usluge</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
	<h1>Dodatne usluge</h1>
	
	<button id="add_serv" style="display: none;" class="btn btn-success" 
			data-toggle="modal" data-target="#myModal">Unesi novu uslugu</button>
		
	<button id="save_serv" style="display: none;" class="btn btn-success">Sacuvaj usluge u fajl</button>
		
	<table class="table table-condensed" id="table_services">
		<thead>
			<tr>
				<th>Ime</th>
				<th>Opis</th>
				<th>Cena</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
	<%@ include file="edit_additional_service.jsp" %>
	</div>
</body>
</html>