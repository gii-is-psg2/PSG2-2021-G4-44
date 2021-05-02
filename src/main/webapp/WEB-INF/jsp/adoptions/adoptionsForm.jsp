<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="adoptions">
    <h2>Nueva adopción</h2>

    <form:form modelAttribute="adoption" class="form-horizontal" id="add-owner-form">
        <p>¿Qué  mascota quiere poner en adopción?</p>
        <select name="pet" id="owners">
	    	<c:forEach var="pet" items="${lsMascota}">
	            <option value="${pet.id}">${pet.name}</option>
	        </c:forEach>
	    </select>
	    <p>Añada una descripción: </p>
        <petclinic:inputField label ="Descripción" name="descripcion" ></petclinic:inputField>
      	
  
                           
  		<button class="btn btn-default" type="submit">Crear</button>
    </form:form>
</petclinic:layout>
