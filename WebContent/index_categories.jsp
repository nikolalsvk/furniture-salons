<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script>
	var editMode;

	function drawTable(data) {
		for (var i = 0; i < data.length; i++) {
			$("#table_salons").append('<tr><td>' + data[i].name + '</td><td>' + data[i].description + '</td><td>' 
	        		+ data[i].subCategory + '</td><td>' + 
	        		'<input class="edit" type="button" value="Izmeni" style="display:none;" data-toggle="modal" data-target="#myModal" id="' +
					data[i].name + '" /></td><td>' +
					'<input class="delete" type="button" value="Obrisi" style="display:none;" id="' + data[i].name + '" />'+
					'</td></tr>');
		}
		
		$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", function(data, status) {
			if (data.role === "admin") {
				$(".delete").show();
				$(".edit").show();
				$("#add_cat").show();
				$("#save_cat").show();
			}
			
			if (data.role === "manager") {
				$(".delete").hide();
				$(".edit").hide();
				$("#add_cat").hide();
			}
			
			if (data.role === "user") {
				$(".delete").hide();
				$(".edit").hide();
				$("#add_cat").hide();
			}
		});
		
		$(".delete").click(function (event) {
			var id = event.target.id;
			if(confirm("Da li sigurno zelite da obrisete kateogriju: " + id + "?")) {
				$.get("${pageContext.request.contextPath}/DeleteCategoryServlet", { id },
						function (data, status) {
					$("#table_salons tbody").empty();
		    	    drawTable(data);
				});
			}
		});
		
		$(".edit").click(function (event) {
			editMode = true;
			$("#name").attr("readonly", true);
			
			var name = event.target.id;
			$.get("${pageContext.request.contextPath}/EditCategoryServlet", { name },
					function (data, status) {
				$("#name").val(data.name);
				$("#description").val(data.description);
				$("#subCategory").val(data.subCategory);
				$("#subCategory option").each(function () {
					if($(this).val() == data.name) {
						$(this).hide();
					} else {
						$(this).show();
					}
				});
			});
		});
	};
	
	function searchFurniture() {
		var name = $("#name_search").val();
		var description = $("#description_search").val();
		$.get("${pageContext.request.contextPath}/GetCategoriesIndexServlet", { name, description },
				function (data, status) {
			$("#table_salons tbody").empty();
    	    drawTable(data);
		});
	};

	$(document).ready(function () {
		$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", function(data, status) {
			if(data == "IndexServlet") {
				window.location.href = data;
				return;
			}
		});
		
		$(".search").on('input', function (e) {
			searchFurniture();
		});
		
		searchFurniture();
		
		$("#save_cat").click(function () {
			$.post("${pageContext.request.contextPath}/IndexCategoriesServlet", function (data, status) {
				
			});
		});
		
		$("#submit_edit").click(function () {
			if ($("#category_form").valid()) {
				var name = $("#name").val();
				var description = $("#description").val();
				var subCategory = $("#subCategory").val();
				if(editMode) {
					$.post("${pageContext.request.contextPath}/EditCategoryServlet", {
						name, description, subCategory
					}, function (data, status) {
						$("#table_salons tbody").empty();
						drawTable(data);
					});
					editMode = false;
					$("#myModal").modal("toggle");
				} else {
					$.post("${pageContext.request.contextPath}/AddCategoryServlet", {
						name, description, subCategory
					}, function (data, status) {
						$("#table_salons tbody").empty();
						drawTable(data);
					});
					$("#myModal").modal("toggle");
				}
			}
		});
		
		$("#add_cat").click(function () {
			editMode = false;
			$("#name").attr("readonly", false);
			// prikazi sve skrivene opcije u select-u
			$("#subCategory option").each(function () {
				$(this).show();
			});
			$("#name").val("");
			$("#description").val("");
			$("#subCategory").val("");
		});
		
		// dodavanje fokusa kad se otvori modalni prozor
		$('#myModal').on('shown.bs.modal', function () {
		    if(editMode) {
		    	$('#description').focus();
		    } else {
		    	$('#name').focus();
		    }
		});
		
		$("#reset_search").click(function () {
			$("#name_search").val("");
			$("#description_search").val("");
			
			searchFurniture();
		});
		
		$("#category_form").validate({
			rules: {
				name: {
			    	minlength: 4,
			    	required: true
			    },
			    description: {
			    	minlength: 4,
			    	required: true
			    }
			}
		});
	});
</script>
<title>Sve kategorije</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
	<h1>Sve kategorije</h1>
	
	<button id="add_cat" style="display: none;" class="btn btn-success" 
			data-toggle="modal" data-target="#myModal">Unesi novu kategoriju</button>
		
	<button id="save_cat" style="display: none;" class="btn btn-success">Sacuvaj kategorije u fajl</button>
	
	<ul class="list-inline">
		<li>
		<label>Naziv:</label>
			<input type="text" id="name_search" class="form-control search" />
		</li>
		<li>
		<label>Opis:</label>
			<input type="text" id="description_search" class="form-control search" />
		</li>
		<li>
			<button id="reset_search" class="btn btn-default">Reset</button>
		</li>
	</ul>
		
	<table class="table table-condensed" id="table_salons">
		<thead>
			<tr>
				<th>Ime</th>
				<th>Opis</th>
				<th>Podkategorija</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
	
	<%@ include file="edit_furniture_category.jsp" %>
	</div>
</body>
</html>