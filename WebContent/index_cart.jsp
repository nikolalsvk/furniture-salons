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
	var total;
	var service_cost = 0;
	var service = "";
	function drawTable(data) {
		total = 0;
		for (var i = 0; i < data.length; i++) {
			var item = data[i].furnitureItem;
			if(!item.discounted) {
				var discountedPrice = item.price;
			} else {
				var discountedPrice = item.discountedPrice;
			}
			total += data[i].total;
	        $("#table_cart").append('<tr><td>' + item.id + '</td><td>' + item.name + '</td><td>' 
	        		+ item.color + '</td><td>' + item.originCountry + '</td><td>' + item.manufacturerName + '</td><td>' 
					+ item.price + '</td><td>' + item.category + 
					'</td><td>' + item.yearOfProduction + '</td><td>' + item.salon + '</td><td>' + item.discount + '</td><td>' + 
					discountedPrice + '</td><td>' + data[i].count + '</td><td>' +
					'<input class="delete" type="button" value="Obrisi" id="' + item.id + '" />' + '</td></tr>');
	    };
		
	    $("#total").text(total);
	    
		$(".delete").click(function (event) {
			var id = event.target.id;
			if(confirm("Da li sigurno zelite da obrisete namestaj sa sifrom: " + id + " iz korpe?")) {
				$.get("${pageContext.request.contextPath}/DeleteShoppingCartItemServlet", { id },
						function (data, status) {
					$("#table_cart tbody").empty();
		    	    drawTable(data);
				});
			}
		});
	}
	
	function checkLoggedIn() {
		$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", function(data, status) {
			if(data == "IndexServlet" && !window.location.href.includes("index.jsp")) {
				window.location.href = data;
				return;
			}
			
			if(data.role != "user") {
				window.location.href = data;
			}
		});
	};
	
	function service_fun() {
		service = $("select#select_service option:selected").val();
		if(service == "default") {
			total -= service_cost;
			service_cost = 0;
			$("#total").text(total);
		} else	{
			$.get("${pageContext.request.contextPath}/GetAdditionalServicesIndexServlet", function(data, status) {
				for(var i = 0; i < data.length; i++) {
					if(data[i].name == service) {
						if(service_cost == 0) {
							service_cost = data[i].price;
							total += data[i].price;;
							$("#total").text(total);
							break;
						} else {
							total -= service_cost;
							service_cost = data[i].price;
							total += service_cost;
							$("#total").text(total);
							break;
						}
					}
				}
			});
		}
	}

	$(document).ready(function () {
		$.get("${pageContext.request.contextPath}/GetShoppingCartIndexServlet",
				function (data, status) {
			$("#table_cart tbody").empty();
    	    drawTable(data);
		});
		
		$("#buy").click(function () {
			if(confirm("Da li zelite da kupite namestaj?")) {
				$.get("${pageContext.request.contextPath}/AddReceiptServlet", { total, service },
						function (data, status) {
					
					$("#table_cart tbody").empty();
					drawTable(data);
			    	$("#table_cart").hide();
			    	
			    	var items = data.furnitureItems;
			    	    
			    	for (var i = 0; i < items.length; i++) {
			    	    $("#name_class").append('<b>' + items[i].furnitureItem.name + "</b> Cena: <b>" + items[i].furnitureItem.price + 
			    	    		"</b> Kolicina: <b>" + items[i].count + '</b><br>')
			   		}
			    	$("#tax").append(data.tax);
			    	$("#price").append(data.finalPrice);
					$("#date").append(data.date);
			    	$("#user_receipt").append(data.user);
			    	$("#additional_service").append(data.additionalService);
			    	$("#receipt").show();
				});
			}
		});
		
		$("#select_service").change(service_fun);
	})
</script>
<title>Vasa korpa</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
		<h1>Korpa</h1>

		<ul class="list-inline">
			<li>
				<button id="buy" class="btn btn-success">Kupite namestaj iz
					korpe</button>
			</li>
			<li>
				<p>
					Ukupna CENA prozivoda u Vasoj korpi: <font id="total"></font>
					credits
				</p>
			</li>
			<li>
				<select id="select_service" class="selectpicker form-control">
					<option value="default"></option> 
					<c:forEach var="service" items="${ additionalServices.values }">
					<option value="${ service.name }">${ service.name }</option>
					</c:forEach>
				</select>
			</li>
		</ul>

		<table class="table table-condensed" id="table_cart">
			<thead>
				<tr>
					<th>ID</th>
					<th>Naziv</th>
					<th>Boja</th>
					<th>Zemlja proizvodnje</th>
					<th>Ime proizvodjaca</th>
					<th>Cena</th>
					<th>Kategorija</th>
					<th>Godina proizvodnje</th>
					<th>Salon</th>
					<th>Popust</th>
					<th>Cena sa popustom</th>
					<th>Kolicina</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>


		<div id="receipt" class="span12"
			style="border: 2px solid black; display: none;">
			<form role="form" class="form-horizontal">
					<h1>Vas racun:</h1>
				<div class="form-group">
					<label for="name_class" class="control-label col-sm-2">Ime:</label>
					<div class="col-sm-10" id="name_class"></div>
				</div>
				<div class="form-group">
					<label for="tax" class="control-label col-sm-2">Porez:</label>
					<div class="col-sm-10" id="tax"></div>
				</div>
				<div class="form-group">
					<label for="price" class="control-label col-sm-2">Ukupna
						cena:</label>
					<div class="col-sm-10" id="price"></div>
				</div>
				<div class="form-group">
					<label for="date" class="control-label col-sm-2">Datum:</label>
					<div class="col-sm-10" id="date"></div>
				</div>
				<div class="form-group">
					<label for="additional_service" class="control-label col-sm-2">Dodatna usluga:</label>
					<div class="col-sm-10" id="additional_service"></div>
				</div>
				<div class="form-group">
					<label for="user_receipt" class="control-label col-sm-2">Kupac:</label>
					<div class="col-sm-10" id="user_receipt"></div>
				</div>

			</form>

		</div>
	</div>
</body>
</html>