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
				$("#fechaEntrada").datepicker({
					dateFormat : 'yy/mm/dd'
				});
			});
			
			
			$(function() {
				$("#fechaSalida").datepicker({
					dateFormat : 'yy/mm/dd'
				});
			});
		</script>
    </jsp:attribute>
    
	<jsp:body>
                <p>${lsCIs}</p>
	<c:forEach items="${lsCIs}" var="check">
	<p>Mascota: ${check.pet}</p>
	<p>Fecha de Entrada: ${check.fechaEntrada}</p>
	<p>Fecha de Salida: ${check.fechaSalida}</p>
	<br>
	</c:forEach>
                <br><br>
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
		        <petclinic:inputField label="fecha_entrada" name="fechaEntrada"></petclinic:inputField>
		        
		        <br><br>
		        <p>Fecha de Salida</p>
		        <petclinic:inputField label="fecha_salida" name="fechaSalida"></petclinic:inputField>

		        <br>
	        </div>
	        <button class="btn btn-default" type="submit">Crear Check In</button>
	
	    </form:form>
    </jsp:body>
</petclinic:layout>
