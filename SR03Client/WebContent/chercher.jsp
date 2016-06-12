<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chercher annonce</title>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/jquery.min.js"></script>
<link href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/style.css" rel="stylesheet">
</head>
<body>

<div class="btn-group btn-group-justified">
  <a id="button_nom" href="#" class="btn btn-primary">Chercher par nom</a>
  <a id="button_categorie" href="#" class="btn btn-primary">Chercher par categorie</a>
  <a id="button_adresse" href="#" class="btn btn-primary">Chercher par adresse</a>
</div>

<div class="formulaire-nom">
	<form class="form" id="form_nom" method="POST" action="nom">
	
        <div class="form-group">
			<label for="label">Nom :</label>
			<input class="form-control" type="text" id="annonce_nom" name="annonce_nom"/>
        </div>	
        		
		<input class="btn btn-default" type="submit" value="Chercher"/>
		
	</form>
</div>

<div class="formulaire-categorie">
	<form class="form" id="form_categorie" method="POST" action="categorie">
	
        <div class="form-group">
			<label for="label">Catégorie :</label>
			<input class="form-control" type="text" id="annonce_categorie" name="annonce_categorie"/>
        </div>
        
		<input class="btn btn-default" type="submit" value="Chercher"/>
		
	</form>
</div>

<div class="formulaire-adresse">
		<form class="form" id="form_adresse" method="POST" action="adresse">
	        
	        <div class="form-group">
				<label for="label">Rue :</label>
				<input class="form-control" type="text" id="annonce_rue" name="annonce_rue"/>
	        </div>
	        
	        <div class="form-group">
				<label for="number">Ville :</label>
				<input class="form-control" type="text" id="annonce_ville" name="annonce_ville"/>
	        </div>
	        
	        <div class="form-group">
				<label for="number">Code postal :</label>
				<input class="form-control" type="text" id="annonce_codepostal" name="annonce_codepostal"/>
	        </div>
			
			<input class="btn btn-default" type="submit" value="Chercher"/>
		</form>
</div>

<div>
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>ID</td>
				<td>Nom</td>
				<td>Catégorie</td>
				<td>Rue</td>
				<td>Ville</td>
				<td>Code postal</td>
				<td>Téléphone</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="annonce" items="${annonces}">
				<tr class="tr-annonce">
					<td>${annonce.id}</td>
					<td>${annonce.name}</td>
					<td>${annonce.category}</td>
					<td>${annonce.rue}</td>
					<td>${annonce.ville}</td>
					<td>${annonce.codepostal}</td>
					<td>${annonce.phone}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<ul class="pagination">
		<%
			if (request.getAttribute("pageNum") != null) {
				int pageNumber = (Integer) request.getAttribute("pageNum");
				for (int i=1; i<=pageNumber; i++) {
					out.println("<li id=\"" + String.valueOf(i) + "\" class=\"li-page\"><a>" + String.valueOf(i) + "</a></li>");
				}
			}
		%>

		
	</ul>
	<br/>
	
	<script type="text/javascript">
	$(function() {
		  $("#form_nom").show();
		  $("#form_categorie").hide();
		  $("#form_adresse").hide();
		
		  var items = $(".tr-survey");
		  var numItems = items.length;
		  var perPage = 5;
		  var numPage = Math.ceil(numItems / perPage);
		  var current = 1;
		  var pageItems = $(".li-page");
		  items.slice(perPage).hide();
		  $("#" + current).addClass("active");
		  for (i=1; i<=numPage; i++) {
			  $("#" + i).on("click", function() {
				  update($(this).attr("id"));
			  });
		  }	
		  $("#button_nom").on("click", function() {
			  $("#form_nom").show();
			  $("#form_categorie").hide();
			  $("#form_adresse").hide();
		  });
		  $("#button_categorie").on("click", function() {
			  $("#form_nom").hide();
			  $("#form_categorie").show();
			  $("#form_adresse").hide();
		  });
		  $("#button_adresse").on("click", function() {
			  $("#form_nom").hide();
			  $("#form_categorie").hide();
			  $("#form_adresse").show();
		  });
		  
		  function update(pageNumber)
		  {
			  var showFrom = perPage * (pageNumber-1);
		      var showTo = showFrom + perPage;
		      if (showTo > numItems) {
		    	  showTo = numItems;
		      }
		      items.hide();
		      items.slice(showFrom, showTo).show();
		      $("#" + current).removeClass("active");
		      $("#" + pageNumber).addClass("active");
		      current = pageNumber;
		  }
		});
	</script>
	

</body>
</html>