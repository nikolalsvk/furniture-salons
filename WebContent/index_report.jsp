<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Izvestaj</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.1.min.js">
</script>
<script>
	function searchReports(startDate, endDate) {
		var totalRevenue = 0;
		$("#table_reports tbody").empty();
		$.get("${pageContext.request.contextPath}/GetReportServlet", { startDate, endDate },
				function (data, status) {
			for(var i = 0; i < data.length; i++) {
				totalRevenue += data[i].total;
				$("#table_reports").append('<tr><td>' + data[i].date + '</td><td>' + data[i].total + '</td></tr>');
			}
			$("#totalRev").text("Ukupna zarada za dati period je: " + totalRevenue);
		});
		
	};
	
	function searchReportsCat(startDate, endDate, category) {
		var totalRevenue = 0;
		$("#table_reports_cat tbody").empty();
		$.get("${pageContext.request.contextPath}/GetReportCategoryServlet", { startDate, endDate, category },
				function (data, status) {
			for(var i = 0; i < data.length; i++) {
				totalRevenue += data[i].total;
				$("#table_reports_cat").append('<tr><td>' + data[i].name + '</td><td>' + data[i].quantity + '</td><td>' +
						data[i].total + '</td></tr>');
			}
			$("#totalRev_cat").text("Ukupna zarada za dati period je: " + totalRevenue);
		});
	};

	$(document).ready(function() {
		$("#startDate").datepicker({ dateFormat : 'dd-mm-yy' });
		$("#endDate").datepicker({ dateFormat : 'dd-mm-yy' });
		$("#startDate_cat").datepicker({ dateFormat : 'dd-mm-yy' });
		$("#endDate_cat").datepicker({ dateFormat : 'dd-mm-yy' });
		
		var name = "";
		var description = "";
		$.get("${pageContext.request.contextPath}/GetCategoriesIndexServlet", { name, description },
				function (data, status) {
			for(var i = 0; i < data.length; i++) {
				$("#select_category").append('<option id="' + data[i].name + '">' + data[i].name + '</option>');
			}
		});
		
		$("#list").click(function () {
			var startDate = $("#startDate").datepicker({ dateFormat: 'dd-mm-yy' }).val();
			var endDate = $("#endDate").datepicker({ dateFormat: 'dd-mm-yy' }).val();
			if(startDate == "" || endDate == "") {
				
			} else {
				searchReports(startDate, endDate);
			}
		});
		
		$("#list_cat").click(function () {
			var category = $("select#select_category option:selected").val();
			var startDate = $("#startDate_cat").datepicker({ dateFormat: 'dd-mm-yy' }).val();
			var endDate = $("#endDate_cat").datepicker({ dateFormat: 'dd-mm-yy' }).val();
			if(startDate == "" || endDate == "") {
				
			} else {
				searchReportsCat(startDate, endDate, category);
			}
		});
	});
</script>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
		<h1>Izvestaj</h1>
		<ul class="list-inline">
			<li>
				<label>Od:</label> 
				<input type="text" class="form-control datepicker" id="startDate" name="startDate"></li>
			<li>
				<label>Do:</label> 
				<input type="text" class="form-control datepicker" id="endDate" name="endDate">
			</li>
			<li>
				<button id="list">Izlistaj</button>
			</li>
		</ul>
		
		<table class="table table-condensed" id="table_reports">
			<thead>
				<tr>
					<th>Datum</th>
					<th>Ukupna zarada</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		
		<p id="totalRev"></p>
		
		<ul class="list-inline">
			<li>
				<label>Od:</label> 
				<input type="text" class="form-control datepicker" id="startDate_cat" name="startDate"></li>
			<li>
				<label>Do:</label> 
				<input type="text" class="form-control datepicker" id="endDate_cat" name="endDate">
			</li>
			<li>
				<label>Kategorija:</label> 
				<select id="select_category" class="selectpicker form-control">
				
				</select>
			</li>
			<li>
				<button id="list_cat">Izlistaj</button>
			</li>
		</ul>
		
		<table class="table table-condensed" id="table_reports_cat">
			<thead>
				<tr>
					<th>Naziv proizvoda</th>
					<th>Kolicina</th>
					<th>Ukupna zarada</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		
		<p id="totalRev_cat"></p>
	</div>
</body>
</html>