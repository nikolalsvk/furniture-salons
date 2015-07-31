<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.1.min.js">
</script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
 
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
		
	

<script>
	function checkLoggedIn() {
		$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", function(data, status) {
			if(data == "IndexServlet" && !window.location.href.includes("index.jsp")) {
				window.location.href = data;
				return;
			}
		});
	};

	$(document).ready(function() {
		checkLoggedIn();
		
		$('[data-toggle="popover"]').popover();
		
		$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", 
				function(data, status) {
			if (data == "IndexServlet") {
				$("#loginUser").show();
				$("#loginAdmin").show();
				$("#register").show();
			} else {
				if(data.role == "user") {
					$("#cart_link").show();
				} else {
					$("#cart_link").hide();
				}
				if(data.role == "manager") {
					$("#reports_url").show();
				} else {
					$("#reports_url").hide();
				}
				$("#logout").show();
				$("#user_info").text("Logged in as " + data.username);
				$("#user_info").attr("data-content", data.name + " " + data.lastName +
						"\n" + data.phoneNumber + " " + data.email);
				$("#user_info").show();
			}
		});
		
		$("#logout").click(function () {
			$.get("${pageContext.request.contextPath}/LogoutServlet",
					function(data, status) {
				$("#reports_url").hide();
				$("#logout").hide();
				$("#cart_link").hide();
				$("#user_info").hide();
				$("#loginUser").show();
				$("#loginAdmin").show();
				$("#register").show();
				$(".alert").hide();
				$("#logout_alert").show();
			});
			
			checkLoggedIn();
		});
		
		$("#submit_loginUser").click(function () {
			if ($("#loginUser_form").valid()) {
				var username = $("#user_username").val();
				var password = $("#user_password").val();
				$.post("${pageContext.request.contextPath}/LoginServlet", { username, password },
						function(data, status) {
					if(data != "IndexServlet") {
						$.get("${pageContext.request.contextPath}/GetCurrentUserRoleServlet", 
								function(data, status) {
							if(data.role == "user") {
								$("#cart_link").show();
							} else {
								$("#cart_link").hide();
							}
							if(data.role == "manager") {
								$("#reports_url").show();
							} else {
								$("#reports_url").hide();
							}
						});
						$("#loginUserModal").modal("toggle");
						$("#logout").show();
						$("#user_info").text("Logged in as " + username);
						$("#user_info").attr("data-content", data.name + " " + data.lastName +
								"\n" + data.phoneNumber + " " + data.email);
						$("#user_info").show();
						$("#loginUser").hide();
						$("#loginAdmin").hide();
						$("#register").hide();
						$(".alert").hide();
						$("#login_success_alert").show();
					} else {
						// poruka o neuspesnom logovanju
					}
				});
			} else {
				$("#login_alert").show();
				return;
			}
		});
		
		$("#submit_loginAdmin").click(function () {
			if ($("#loginAdmin_form").valid()) {
				var username = $("#admin_username").val();
				var password = $("#admin_password").val();
				$.post("${pageContext.request.contextPath}/LoginAdminServlet", { username, password },
						function(data, status) {
					if(data != "IndexServlet") {	
						$("#loginAdminModal").modal("toggle");
						$("#reports_url").hide();
						$("#logout").show();
						$("#logout").show();
						$("#cart_link").hide();
						$("#user_info").text("Logged in as " + username);
						$("#user_info").attr("data-content", data.name + " " + data.lastName +
								"\n" + data.phoneNumber + " " + data.email);
						$("#user_info").show();
						$("#loginUser").hide();
						$("#loginAdmin").hide();
						$("#register").hide();
						$(".alert").hide();
						$("#login_success_alert").show();
					} else {
						$("#login_alert").show();
					}
				});
			} else {
				$("#login_alert").show();
				return;
			}
		});
		
		$("#submit_register").click(function () {
			if ($("#register_form").valid()) {
				var username = $("#user").val();
				var password = $("#pass").val();
				var name = $("#namer").val();
				var lastName = $("#lastName").val();
				var role = $("#role").val();
				var phoneNumber = $("#phoneNumber").val();
				var email = $("#email").val();
				$.post("${pageContext.request.contextPath}/RegisterServlet", { username, password, name,
					lastName, role, phoneNumber, email },
						function(data, status) {
					if(data == true) {
						$("#registerModal").modal("toggle");
						$(".alert").hide();
						$("#register_alert").show();
					} else {
						$("#login_alert").show();
					}
				});
			} else {
				return;
			}
		});
		
		// fokus kad se otvore modali
		$("#loginUserModal").on('shown.bs.modal', function () {
		    $('#user_username').focus();
		})
		
		$("#loginAdminModal").on('shown.bs.modal', function () {
		    $('#admin_username').focus();
		})
		
		$("#registerModal").on('shown.bs.modal', function () {
		    $('#user').focus();
		})
		
		$("#loginUser_form").validate({
			rules: {
				user_username: {
			    	required: true
			    },
			    user_password: {
			    	required: true
			    }
			}
		});
		
		$("#loginAdmin_form").validate({
			rules: {
				admin_username: {
			    	required: true
			    },
			    admin_password: {
			    	required: true
			    }
			}
		});
		
		$("#register_form").validate({
			rules: {
				username: {
			    	minlength: 4,
			    	required: true
			    },
			    password: {
			    	minlength: 4,
			    	required: true
			    },
			    lastName: {
			    	minlength: 4,
			    	required: true
			    },
			    phoneNumber: {
			    	minlength: 4,
			    	required: true
			    },
			    namer: {
		            minlength: 2,
		            required: true
		        },
			    email: {
			      	required: true,
			      	email: true
			    }
			}
		});
	});
</script>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="IndexServlet">KupiteNamestaj.buy</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="IndexSalonServlet">Namestaj</a></li>
				<li><a href="IndexSalonsServlet">Saloni</a></li>
				<li><a href="IndexCategoriesServlet">Kategorije namestaja</a></li>
				<li><a href="IndexAdditionalServicesServlet">Dodatne usluge</a></li>
				<li><a href="IndexActionSaleServlet">AKCIJA</a></li>
				<li><a href="IndexReportServlet" id="reports_url">Izvestaji</a></li>
			</ul>
		</div>
		<div>
			<ul class="nav navbar-nav navbar-right">
				<li><a id="loginUser" style="display: none; cursor: pointer;"
					data-toggle="modal" data-target="#loginUserModal">Login</a></li>
				<li><a id="loginAdmin" style="display: none; cursor: pointer;"
					data-toggle="modal" data-target="#loginAdminModal">Admin login</a>
				<li><a id="register" style="display: none; cursor: pointer;"
					data-toggle="modal" data-target="#registerModal">Register</a></li>
				<li><a id="cart_link" style="display: none;" href="IndexShoppingCartServlet">Korpa</a></li>
				<li><a id="user_info" style="display: none;" href="#" title="Detalji" data-toggle="popover" data-trigger="focus"
					data-placement="bottom" data-content="Content"></a></li>
				<li><a id="logout" style="display: none; cursor: pointer;">Logout</a></li>
			</ul>
		</div>
	</div>
</nav>

<%@ include file="login.jsp" %>
<%@ include file="login_admin.jsp" %>
<%@ include file="register.jsp" %>

<div class="alert alert-success" style="display: none;" id="logout_alert">
	<a href="#" class="close" data-dismiss="alert">&times;</a>
	<p>Uspesno ste se izlogovali!</p>
</div>

<div class="alert alert-success" style="display: none;" id="login_success_alert">
	<a href="#" class="close" data-dismiss="alert">&times;</a>
	<p>Uspesno ste se ulogovali!</p>
</div>

<div class="alert alert-warning" style="display: none;" id="login_alert">
	<a href="#" class="close" data-dismiss="alert">&times;</a>
	<p>Neuspesno logovanje!</p>
</div>

<div class="alert alert-warning" style="display: none;" id="register_alert">
	<a href="#" class="close" data-dismiss="alert">&times;</a>
	<p>Uspesno ste se registrovali!</p>
</div>