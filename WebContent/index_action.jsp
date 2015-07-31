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

<script>
	function getFurName(data) {
		var nameOfEntity;
		if(data.entity == "komad") {
			var id = data.entityOnSale;
			$.ajax({
			     async: false,
			     type: 'GET',
			     url: '${pageContext.request.contextPath}/GetFurnitureItemByIdServlet',
			     data: { id },
			     success: function(data_id) {
			    	 nameOfEntity = data_id.name;
			     }
			});
		} else {
			nameOfEntity = data.entityOnSale;
		}
		
		return nameOfEntity;
	}

	function drawTable(data) {
		$("#table_sales_not_current tbody").empty();
		$("#table_sales_current tbody").empty();
		
		var flag;
		
		for (var i = 0; i < data.length; i++) {
			var nameOfEntity = getFurName(data[i]);
			if(data[i].current) {
				$("#table_sales_current").append('<tr><td>' + data[i].salon + '</td><td>' + nameOfEntity + '</td><td>' 
		        		+ data[i].discount + '</td><td>' + data[i].startDate + '</td><td>' + data[i].endDate + '</td><td>' +
		        		'</td><td>' +
						'<input class="delete" type="button" value="Obrisi" style="display:none;" id="' + data[i].entityOnSale + '" ' +
						' name="' + data[i].salon + '" alt="' + data[i].entity + '" />'+
						'</td></tr>');
			} else {
				$("#table_sales_not_current").append('<tr><td>' + data[i].salon + '</td><td>' + nameOfEntity + '</td><td>' 
		        		+ data[i].discount + '</td><td>' + data[i].startDate + '</td><td>' + data[i].endDate + '</td><td>' +
		        		'</td><td>' +
						'<input class="delete" type="button" value="Obrisi" style="display:none;" id="' + data[i].entityOnSale + '" ' +
						' name="' + data[i].salon + '" alt="' + data[i].entity + '" />'+
						'</td></tr>');
			}
		}
		
		$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", function(data, status) {
			if (data.role === "admin" || data.role === "manager") {
				$(".delete").show();
				$(".edit").show();
				$("#add_sale").show();
			}
			
			if (data.role === "user") {
				$(".delete").hide();
				$(".edit").hide();
				$("#add_sale").hide();
			}
		});
		
		$(".delete").click(function (event) {
			var entityOnSale = event.target.id;
			var salon = event.target.name;
			var entity = event.target.alt;
			if(confirm("Da li sigurno zelite da obrisete akciju za: " + entityOnSale + "?")) {
				$.get("${pageContext.request.contextPath}/DeleteActionSaleServlet", { entityOnSale, salon, entity },
						function (data, status) {
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
		
		$("#startDate").datepicker({ dateFormat: 'dd-mm-yy' });
		$("#endDate").datepicker({ dateFormat: 'dd-mm-yy' });
		
		$("#salon").change(function () {
			// ako je izabran komad namestaja izlistati sve komade iz tog salona
			// else izlistati kategorije namestaja koji pripadaju izabranom salonu
			var entity = $("#entity").val();
			if(entity == "komad") {
				var name = "";
				var color = "";
				var originCountry = "";
				var manufacturerName = "";
				var price = "";
				var inStock = "";
				var category = "";
				var yearOfProduction = "";
				var salon = $("select#salon option:selected").val();
				$("#entityOnSale").empty();
				$.get("${pageContext.request.contextPath}/GetFurnitureIndexServlet", { name, color,
					originCountry, manufacturerName, price, inStock, category, yearOfProduction, salon },
						function (data, status) {
					for (var i = 0; i < data.length; i++) {
						$("#entityOnSale").append('<option id="' + data[i].id + '">' + data[i].name + '</option>');
					}
				});
			} else if (entity == "kategorija") {
				var name = "";
				var description = "";
				$("#entityOnSale").empty();
				$.get("${pageContext.request.contextPath}/GetCategoriesIndexServlet", { name, description },
						function (data, status) {
					for (var i = 0; i < data.length; i++) {
						$("#entityOnSale").append('<option id="' + data[i].id + '">' + data[i].name + '</option>');
					}
				});
			}
		});
		
		$("#entity").change(function() {
			var entity = $("#entity").val();
			if(entity == "komad") {
				var name = "";
				var color = "";
				var originCountry = "";
				var manufacturerName = "";
				var price = "";
				var inStock = "";
				var category = "";
				var yearOfProduction = "";
				var salon = $("select#salon option:selected").val();
				$("#entityOnSale").empty();
				$.get("${pageContext.request.contextPath}/GetFurnitureIndexServlet", { name, color,
					originCountry, manufacturerName, price, inStock, category, yearOfProduction, salon },
						function (data, status) {
					for (var i = 0; i < data.length; i++) {
						$("#entityOnSale").append('<option id="' + data[i].id + '">' + data[i].name + '</option>');
					}
				});
			} else if (entity == "kategorija") {
				var name = "";
				var description = "";
				$("#entityOnSale").empty();
				$.get("${pageContext.request.contextPath}/GetCategoriesIndexServlet", { name, description },
						function (data, status) {
					for (var i = 0; i < data.length; i++) {
						$("#entityOnSale").append('<option id="' + data[i].id + '">' + data[i].name + '</option>');
					}
				});
			}
		});
		
		$.get("${pageContext.request.contextPath}/GetActionSalesServlet", function (data, status) {
			drawTable(data);
		});
		
		$("#save_sale").click(function () {
			$.post("${pageContext.request.contextPath}/IndexActionSalesServlet", function (data, status) {
				
			});
		});
		
		$("#add_sale").click(function () {
			editMode = false;
			$("#name").attr("readonly", false);
			$("#name").val("");
			$("#entity").val("");
			$("#entityOnSale").val("");
			$("#discount").val("");
			$("#startDate").val("");
			$("#endDate").val("");
		});
		
		$("#submit_edit").click(function () {
			if($("#action_form").valid()){
				var salon = $("select#salon option:selected").val();
				var entity = $("select#entity option:selected").val();
				if(entity == "komad") {
					var entityOnSale = $("select#entityOnSale option:selected").attr('id');
				} else {
					var entityOnSale = $("select#entityOnSale option:selected").val();
				}
				var discount = $("select#discount option:selected").val();
				var startDate = $("#startDate").datepicker({ dateFormat: 'dd-mm-yy' }).val();
				var endDate = $("#endDate").datepicker({ dateFormat: 'dd-mm-yy' }).val();
				if(!salon || !entity || !entityOnSale || !discount || startDate == "" || endDate == "") {
					$("#alert_form").show();
				} else {
					$("#alert_form").hide();
					if(editMode) {
						$.post("${pageContext.request.contextPath}/EditAdditionalServiceServlet", {
							salon, entity, entityOnSale, discount, startDate, endDate
						}, function (data, status) {
							drawTable(data);
						});
						editMode = false;
						$("#myModal").modal('toggle');
					} else {
						$.post("${pageContext.request.contextPath}/AddActionSaleServlet", {
							salon, entity, entityOnSale, discount, startDate, endDate
						}, function (data, status) {
							drawTable(data);
							
						});
						$("#myModal").modal('toggle');
					}
				}
			}
		});
		
		$("#action_form").validate({
			rules: {
				salon: {
			    	required: true
			    },
			    entity: {
			    	required: true
			    },
			    entityOnSale: {
			    	required: true
			    },
			    discount: {
			    	required: true
			    },
			    startDate: {
		            required: true
		        },
		        endDate: {
			      	required: true,
			    }
			}
		});
	});
</script>
<title>AKCIJA</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">

		<button id="add_sale" style="display: none;" class="btn btn-success"
			data-toggle="modal" data-target="#myModal">Unesi novu akciju</button>

		<p>Aktuelne akcije</p>
		<table class="table table-condensed" id="table_sales_current">
			<thead>
				<tr>
					<th>Salon</th>
					<th>Na akciji</th>
					<th>Popust</th>
					<th>Pocinje</th>
					<th>Zavrsava</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		
		<p>Prosle akcije</p>
		<table class="table table-condensed" id="table_sales_not_current">
			<thead>
				<tr>
					<th>Salon</th>
					<th>Na akciji</th>
					<th>Popust</th>
					<th>Pocinje</th>
					<th>Zavrsava</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>


		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Dodavanje akcije</h4>
					</div>
					<div class="modal-body">
						<p id="alert_form" style="display:none;">Morate popuniti sva polja.</p>
						<form role="form" class="form-horizontal" id="action_form">
							<div class="form-group">
								<label for="salon" class="control-label col-sm-2">Salon:</label>
								<div class="col-sm-10">
									<select id="salon" class="selectpicker form-control" name="salon">
										<c:forEach var="salon" items="${ salons.values }">
											<option value="${ salon.salonName }">${ salon.salonName }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="entity" class="control-label col-sm-2">Entitet
									na akciji:</label>
								<div class="col-sm-10">
									<select id="entity" class="selectpicker form-control" name="entity">
										<option value="komad">Komad namestaja</option>
										<option value="kategorija">Kategorija namestaja</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="entityOnSale" class="control-label col-sm-2">Na
									akciji:</label>
								<div class="col-sm-10">
									<select id="entityOnSale" class="selectpicker form-control" name="entityOnSale">
									
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="discount" class="control-label col-sm-2">Popust:</label>
								<div class="col-sm-10">
									<select id="discount" class="selectpicker form-control" name="discount">
										<option value="0.05">5%</option>
										<option value="0.1">10%</option>
										<option value="0.15">15%</option>
										<option value="0.2">20%</option>
										<option value="0.25">25%</option>
										<option value="0.3">30%</option>
										<option value="0.35">35%</option>
										<option value="0.4">40%</option>
										<option value="0.45">45%</option>
										<option value="0.5">50%</option>
										<option value="0.55">55%</option>
										<option value="0.6">60%</option>
										<option value="0.65">65%</option>
										<option value="0.7">70%</option>
										<option value="0.75">75%</option>
										<option value="0.8">80%</option>
										<option value="0.85">85%</option>
										<option value="0.9">90%</option>
										<option value="0.95">95%</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="startDate" class="control-label col-sm-2">Pocinje:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="startDate"
										name="startDate">
								</div>
							</div>
							<div class="form-group">
								<label for="endDate" class="control-label col-sm-2">Zavrsava
									se:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="endDate"
										name="endDate">
								</div>
							</div>
							<div class="form-group" class="control-label col-sm-1">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="button" class="btn btn-default" id="submit_edit">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>