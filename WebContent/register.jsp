<div id="registerModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Registracija</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="register_form">
					<div class="form-group">
						<label for="username">Username:</label> <input type="text"
							class="form-control" id="user" name="username">
					</div>
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							class="form-control" id="pass" name="password">
					</div>
					<div class="form-group">
						<label for="namer">First name:</label> <input type="text"
							class="form-control" id="namer" name="namer">
					</div>
					<div class="form-group">
						<label for="lastName">Last name:</label> <input type="text"
							class="form-control" id="lastName" name="lastName">
					</div>
					<div class="form-group">
						<label for="role">Role:</label> <select class="form-control"
							id="role" name="role">
							<option value="manager">manager</option>
							<option value="user">user</option>
						</select>
					</div>
					<div class="form-group">
						<label for="phoneNumber">Telefon:</label> <input type="tel"
							class="form-control" id="phoneNumber" name="phoneNumber">
					</div>
					<div class="form-group">
						<label for="email">Email:</label> <input type="email"
							class="form-control" id="email" name="email">
					</div>
					<div class="form-group" class="control-label col-sm-1">
						<div>
							<button type="button" class="btn btn-default" 
							id="submit_register">Register</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>