<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.1.min.js">
</script>

<style type="text/css">
img {
	display: block;
	max-width: 300px;
	max-height: 100px;
	width: auto;
	height: auto;
}

.ui-datepicker-calendar {
    display: none;
    }​
</style>

<script>
	var editMode;

	function drawTable(data) {
		$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", function(data, status) {
			if(data == "IndexServlet") {
				window.location.href = data;
				return;
			}
			
			if (data.role === "admin") {
				$(".add").hide();
				$(".delete").show();
				$(".edit").show();
				$("#add_fur").show();
				$("#save_fur").show();
			}
			
			if (data.role === "manager") {
				$(".add").hide();
				$(".delete").hide();
				$(".edit").hide();
				$("#add_fur").hide();
			}
			
			if (data.role === "user") {
				$(".add").show();
				$(".quantity").show();
				$(".delete").hide();
				$(".edit").hide();
				$("#add_fur").hide();
			}
		});
		
		for (var i = 0; i < data.length; i++) {
			if(!data[i].discounted) {
				var discountedPrice = data[i].price;
			} else {
				var discountedPrice = data[i].discountedPrice;
			}
			// slika
			var image;
			if(data[i].image) {
				var imageShow = new Image();
				imageShow.src = data[i].image;
				image = '<img src="' + imageShow.src + '">'
			} else {
				image = "Nema sliku";
			}

			$("#table_fur_items").append('<tr><td>' + data[i].id + '</td><td>' + data[i].name + '</td><td>' 
	        		+ data[i].color + '</td><td>' + data[i].originCountry + '</td><td>' + data[i].manufacturerName + '</td><td>' 
					+ data[i].price + '</td><td>' + data[i].inStock+ '</td><td>' + data[i].category + 
					'</td><td>' + data[i].yearOfProduction + '</td><td>' + data[i].salon + '</td><td>' + data[i].discount + '</td><td>' + discountedPrice + '</td>' +
					'<td>' + image + '</td><td>' +
					'<input class="edit btn btn-default" type="button" value="Izmeni" style="display:none;" data-toggle="modal" data-target="#myModal" id="' + data[i].id + '" />' +
					'<input class="delete btn btn-default" type="button" value="Obrisi" style="display:none;" id="' + data[i].id + '" />' + '</td><td>' +
					'<form class="q_form" id="form_' + data[i].id + '"><input class="quantity form-control" type="number" style="display:none;" size="4" name="txt_' + data[i].id + '" /></td><td>' +
					'<input class="add btn btn-default" type="submit" value="Dodaj" style="display:none;" id="' + data[i].id + '" />' + '</td></form></tr>');
		}
		
		$(".delete").click(function (event) {
			var id = event.target.id;
			if(confirm("Da li sigurno zelite da obrisete namestaj sa sifrom: " + id + "?")) {
				$.get("${pageContext.request.contextPath}/DeleteFurnitureItemServlet", { id },
						function (data, status) {
					$("#table_fur_items tbody").empty();
		    	    drawTable(data);
				});
			}
		});
		
		$(".edit").click(function (event) {
			editMode = true;
			$("#id").attr("readonly", true);
			var id = event.target.id;
			$.get("${pageContext.request.contextPath}/EditFurnitureItemServlet", { id },
					function (data, status) {
				$("#id").val(data.id);
				$("#name").val(data.name);
				$("#color").val(data.color);
				$("#originCountry").val(data.originCountry);
				$("#manufacturerName").val(data.manufacturerName);
				$("#price").val(data.price);
				$("#inStock").val(data.inStock);
				$("#category").val(data.category);
				$("#yearOfProduction").val(data.yearOfProduction);
				$("#salon").val(data.salon);
				$("#picture").val(data.image);
			});
		});
		
		$(".add").click(function (event) {
			var id = event.target.id;
			var input_id = "txt_" + id;
			var number =  parseInt($('#table_fur_items').find('input[name="' + input_id + '"]').val());
			if(isNaN(number) || number < 1) {
				$("#not_a_num_alert").hide();
				$("#not_a_num_alert").show();
			} else {
				$.get("${pageContext.request.contextPath}/AddToCartServlet", { id, number },
						function (data, status) {
					if(data == "false") {
						$("#not_added_alert").toggle();
						$("#not_add_text").text("Proizvod nije dodat u korpu!");
					} else {
						$("#alert_add_text").text("Proizvod " + data.name + " je dodat u korpu!");
						$("#add_to_cart_alert").toggle();
						searchFurniture();
					}
				});
			}
		});
	};
	
	function searchFurniture() {
		$("#add_to_cart_alert").hide();
		
		var name = $("#name_search").val();
		var color = $("#color_search").val();
		var originCountry = $("#originCountry_search").val();
		var manufacturerName = $("#manufacturerName_search").val();
		var price = $("select#select_price option:selected").val();
		var inStock = $("#inStock_search").val();
		var category = $("select#select_category_search option:selected").val();
		var yearOfProduction = $("#year_search").val();
		var salon = $("select#select_salon_search option:selected").val();
		
		$.get("${pageContext.request.contextPath}/GetFurnitureIndexServlet", { name, color,
			originCountry, manufacturerName, price, inStock, category, yearOfProduction, salon },
				function (data, status) {
			$("#table_fur_items tbody").empty();
    	    drawTable(data);
		});
	};
	
	$(document).ready(function () {
		$("select").change(function () {
			searchFurniture();
		});
		
		$(".search").on('input', function (e) {
			searchFurniture();
		});
		
		$("input#txt_1245").focus(function () {
			alert("click");
		});
		
		$("#yearOfProduction").datepicker( {
		    changeYear: true,
		    showButtonPanel: false,
		    yearRange: "-100:+0",
		    dateFormat: 'yy',
		    onClose: function(dateText, inst) { 
		        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		        $(this).datepicker('setDate', new Date(year, 1, 1));
		    }
		});
		
		// kad se pokrene stranica, ucitavanje tabele
		searchFurniture();
		
		$("#save_fur").click(function () {
			$.post("${pageContext.request.contextPath}/IndexSalonServlet", function (data, status) {
				
			});
		});
		
		// file upload
		var files = [];
		 
		$("input[type=file]").change(function(event) {
		  $.each(event.target.files, function(index, file) {
		    var reader = new FileReader();
		    reader.onload = function(event) {  
		      object = {};
		      object.filename = file.name;
		      object.data = event.target.result;
		      files.push(object);
		    };  
		    reader.readAsDataURL(file);
		  });
		});
		 
		$("#submit_edit").click(function () {
			if ($("#furniture_form").valid()) {
				var id = $("#id").val();
				var name = $("#name").val();
				var color = $("#color").val();
				var originCountry = $("#originCountry").val();
				var manufacturerName = $("#manufacturerName").val();
				var price = $("#price").val();
				var inStock = $("#inStock").val();
				var category = $("#category").val();
				var yearOfProduction = $("#yearOfProduction").val();
				var salon = $("#salon").val();
				if(files[0]) {
					var imagedata = files[0].data;
				}
				
				if(editMode) {
					$.post("${pageContext.request.contextPath}/EditFurnitureItemServlet", { 
						id, name, color, originCountry, manufacturerName, price, inStock, category,
						yearOfProduction, salon, imagedata
					}, function (data, status) {
						$("#table_fur_items tbody").empty();
						drawTable(data);
					});
					editMode = false;
					$("#myModal").modal("toggle");
				} else {
					$.post("${pageContext.request.contextPath}/AddFurnitureItemServlet", {
						id, name, color, originCountry, manufacturerName, price, inStock, category,
						yearOfProduction, salon, imagedata
					}, function (data, status) {
						$("#table_fur_items tbody").empty();
						drawTable(data);
					});
					$("#myModal").modal("toggle");
				}
			}
		});
		
		$("#add_fur").click(function () {
			editMode = false;
			$("#id").attr("readonly", false);
			$("#id").val("");
			$("#name").val("");
			$("#color").val("");
			$("#originCountry").val("");
			$("#manufacturerName").val("");
			$("#price").val("");
			$("#inStock").val("");
			$("#category").val("");
			$("#yearOfProduction").val("");
			$("#salon").val("");
			$("#picture").val("");
		});
		
		// dodavanje fokusa kad se otvori modalni prozor
		$("#myModal").on('shown.bs.modal', function () {
		    if(editMode) {
		    	$('#name').focus();
		    } else {
		    	$('#id').focus();
		    }
		})
		
		$("#reset_search").click(function () {
			$("#name_search").val("");
			$("#color_search").val("");
			$("#originCountry_search").val("");
			$("#manufacturerName_search").val("");
			$("select#select_price").val("");
			$("#inStock_search").val("");
			$("select#select_category_search").val("default");
			$("#year_search").val("");
			$("select#select_salon_search").val("default");
			
			searchFurniture();
		});
		
		$("#furniture_form").validate({
			rules: {
				id: {
			    	minlength: 4,
			    	required: true
			    },
			    name: {
			    	minlength: 4,
			    	required: true
			    },
			    color: {
			    	minlength: 4,
			    	required: true
			    },
			    originCountry: {
			    	minlength: 4,
			    	required: true
			    },
			    manufacturerName: {
		            minlength: 2,
		            required: true
		        },
		        price: {
			      	required: true,
			      	number: true
			    },
			    inStock: {
			      	required: true,
			      	number: true
			    },
		        category: {
			      	required: true
			    },
		        yearOfProduction: {
			      	required: true,
			      	number: true
			    },
		        salon: {
			      	required: true
			    }
			}
		});
	});
</script>
<title>Home page</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
		<h1>Home page</h1>

		<button id="add_fur" style="display: none;" class="btn btn-success"
			data-toggle="modal" data-target="#myModal">Unesi novi
			namestaj</button>

		<button id="save_fur" style="display: none;" class="btn btn-success">Sacuvaj
			namestaj u fajl</button>

		<div id="slikashow"></div>

		<ul class="list-inline">
			<li><label>Naziv:</label> <input type="text" id="name_search"
				class="form-control search" /></li>
			<li><label>Boja:</label> <input type="text" id="color_search"
				class="form-control search" /></li>
			<li><label>Zemlja proizvodnje:</label> <input type="text"
				id="originCountry_search" class="form-control search" /></li>
			<li><label>Ime proizvodjaca:</label> <input type="text"
				id="manufacturerName_search" class="form-control search" /></li>
			<li><label>Cena:</label> <select id="select_price"
				class="selectpicker form-control">
					<option value=""></option>
					<option value="100-200">100-200</option>
					<option value="200-300">200-300</option>
					<option value="300-400">300-400</option>
					<option value="400-500">400-500</option>
					<option value="500-600">500-600</option>
					<option value="600-700">600-700</option>
					<option value="700-800">700-800</option>
					<option value="800-900">800-900</option>
					<option value="900-1000">900-1000</option>
			</select></li>
			<li><label>Na lageru:</label> <input type="number"
				id="inStock_search" class="form-control search" /></li>
			<li><label>Kategorija:</label> <select id="select_category_search"
				class="selectpicker form-control">
					<option value="default"></option>
					<c:forEach var="category" items="${ furnitureCategories.values }">
						<option value="${ category.name }">${ category.name }</option>
					</c:forEach>
			</select></li>
			<li><label>Godina proizvodnje:</label> <input type="number"
				id="year_search" class="form-control search" /></li>
			<li><label>Salon:</label> <select id="select_salon_search"
				class="selectpicker form-control">
					<option value="default"></option>
					<c:forEach var="salon" items="${ salons.values }">
						<option value="${ salon.salonName }">${ salon.salonName }</option>
					</c:forEach>
			</select></li>
			<li>
				<button id="reset_search" class="btn btn-default">Reset</button>
			</li>
		</ul>

		<br>

		<div class="alert alert-success" style="display: none;"
			id="add_to_cart_alert">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			<p id="alert_add_text">Proizvod dodat u korpu!</p>
		</div>

		<div class="alert alert-danger" style="display: none;"
			id="not_a_num_alert">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			<p id="alert_add_text">Morate uneti broj!</p>
		</div>
		
		<div class="alert alert-danger" style="display: none;"
			id="not_added_alert">
			<a href="#" class="close" data-dismiss="alert">&times;</a>
			<p id="not_add_text">Morate uneti broj!</p>
		</div>
		

		<table class="table table-condensed" id="table_fur_items">
			<thead>
				<tr>
					<th>ID</th>
					<th>Naziv</th>
					<th>Boja</th>
					<th>Zemlja proizvodnje</th>
					<th>Ime proizvodjaca</th>
					<th>Cena</th>
					<th>Na lageru</th>
					<th>Kategorija</th>
					<th>Godina proizvodnje</th>
					<th>Salon</th>
					<th>Popust</th>
					<th>Cena sa popustom</th>
					<th>Slika</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>

		<%@ include file="edit_furniture_item.jsp"%>
	</div>
</body>
</html>