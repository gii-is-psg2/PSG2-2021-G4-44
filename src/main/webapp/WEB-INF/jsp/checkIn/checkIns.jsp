<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="checkIn">
	<jsp:attribute name="customScript">
        <script>
			$(function() {
				$("#fecha_entrada").datepicker({
					dateFormat : 'yy/mm/dd'
				});
			});
			

			$(function() {
				$("#fecha_salida").datepicker({
					dateFormat : 'yy/mm/dd'
				});
			});
		</script>
    </jsp:attribute>
    
	<jsp:body>
	<h2>
        <c:if test="${checkIn['new']}">Nuevo </c:if> Check In
    </h2>        
		<form:form modelAttribute="checkIn" class="form-horizontal" id="add-owner-form">
	    	<select name="pet.name" id="owners">
	          <c:forEach var="pet" items="${lsMascota}">
	            <option value="${pet}">${pet}</option>
	          </c:forEach>
	        </select>
	        
	        <input hidden="true" name="owner" value="${owner}"/>
	        
			<div class="form-group has-feedback">
		        <br><br>
		        <p>Fecha de Entrada</p>
		        <input id="fecha_entrada" name="dateE"/>
		        
		        <br><br>
		        <p>Fecha de Salida</p>
		        <input id="fecha_salida" name="dateS"/>

		        <br>
	        </div>
	        <button class="btn btn-default" type="submit">Crear Check In</button>
	
	    </form:form>
    </jsp:body>
</petclinic:layout>
