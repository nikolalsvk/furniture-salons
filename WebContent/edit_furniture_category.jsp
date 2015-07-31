<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Kategorija namestaja</h4>
			</div>
			<div class="modal-body">
				<form role="form" class="form-horizontal" id="category_form">
					<div class="form-group">
						<label for="name" class="control-label col-sm-2">Ime:</label> 
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name">
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="control-label col-sm-2">Opis:</label> 
						<div class="col-sm-10">
							<input type="text" class="form-control" id="description" name="description">
						</div>
					</div>
					<div class="form-group">
					<label for="subCategory" class="control-label col-sm-2">Podkategorija:</label> 
					<div class="col-sm-10">
						<select class="form-control col-sm-10" id="subCategory" name="subCategory">
							<option value=""></option>
							<c:forEach var="subCategory" items="${ furnitureCategories.values }">
							<option value="${ subCategory.name }">${ subCategory.name }</option>
							</c:forEach>
						</select>
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