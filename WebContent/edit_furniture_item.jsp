<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
	    <!-- Modal content-->
	    <div class="modal-content">
	    	<div class="modal-header">
		      	<button type="button" class="close" data-dismiss="modal">&times;</button>
		      	<h4 class="modal-title">Namestaj</h4>
			</div>
	    	<div class="modal-body">
			<form role="form" class="form-horizontal" enctype="multipart/form-data" id="furniture_form">
				<div class="form-group">
					<label for="id" class="control-label col-sm-2">Sifra:</label> 
					<div class="col-sm-10"> 
						<input type="text" class="form-control" id="id" name="id" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="control-label col-sm-2">Ime:</label> 
					<div class="col-sm-10">
						<input type="text" class="form-control" id="name" name="name">
					</div>
				</div>
				<div class="form-group">
					<label for="color" class="control-label col-sm-2">Boja:</label> 
					<div class="col-sm-10">
						<input type="text" class="form-control" id="color" name="color">
					</div>
				</div>
				<div class="form-group">
					<label for="originCountry" class="control-label col-sm-2">Zemlja porekla:</label> 
					<div class="col-sm-10">
						<input type="text" class="form-control" id="originCountry" name="originCountry">
					</div>
				</div>
				<div class="form-group">
					<label for="manufacturerName" class="control-label col-sm-2">Ime proizvodjaca:</label> 
					<div class="col-sm-10">
						<input type="text" class="form-control" id="manufacturerName" name="manufacturerName">
					</div>
				</div>
				<div class="form-group">
					<label for="price" class="control-label col-sm-2">Cena:</label> 
					<div class="col-sm-10">
						<input type="text" class="form-control" id="price" name="price">
					</div>
				</div>
				<div class="form-group">
					<label for="inStock" class="control-label col-sm-2">Na lageru:</label> 
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inStock" name="inStock">
					</div>
				</div>
				<div class="form-group">
					<label for="category" class="control-label col-sm-2">Kategorija:</label> 
					<div class="col-sm-10">
						<select class="form-control col-sm-10" id="category" name="category">
							<c:forEach var="category" items="${ furnitureCategories.values }">
							<option value="${ category.name }">${ category.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="yearOfProduction" class="control-label col-sm-2">Godina proizvodnje:</label> 
					<div class="col-sm-10">
						<input type="text" class="form-control" id="yearOfProduction" name="yearOfProduction">
					</div>
				</div>
				<div class="form-group">
					<label for="salon" class="control-label col-sm-2">Salon:</label>
					<div class="col-sm-10">
						<select class="form-control" id="salon" name="salon">
							<c:forEach var="salon" items="${ salons.values }">
							<option value="${ salon.salonName }">${ salon.salonName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="picture" class="control-label col-sm-2">Slika:</label> 
					<div class="col-sm-10">
						<input type="file" class="form-control" id="picture" name="picture" accept="image/*">
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
