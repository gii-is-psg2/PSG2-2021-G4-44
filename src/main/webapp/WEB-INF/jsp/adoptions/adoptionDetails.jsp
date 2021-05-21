<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<petclinic:layout pageName="adoptionDetails">

    <h2>Informacion de la adopcion</h2>


    <table class="table table-striped">
        <tr>
            <th><b><c:out value="${adoption.pet.type}"/></b></th>
            <td></td>
        </tr>
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${adoption.pet.name}"/></b></td>
        </tr>

        <tr>
            <th>Propietario</th>
			<td><c:out value="${adoption.owner.firstName} ${adoption.owner.lastName}"/></td>
        </tr>
        <tr>
            <th>Descripcion</th>
			<td><c:out value="${adoption.descripcion}"/></td>
        </tr>
    </table>
	<c:choose>
        <c:when test="${me==true}">
        
		    <spring:url value="${adoptionId}/delete" var="deleteUrl">
		        <spring:param name="adoptionId" value="${adoption.id}"/>
		    </spring:url>
		    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Eliminar Adopción</a>
       	</c:when>
       <c:otherwise>
       
    	<sec:authorize access="hasAuthority('owner')"> 
    	
			<form:form action= "${adoption.id}/requirement/new" class="form-horizontal" id="add-owner-form">
	        				
    			<textArea name="text" rows="7"/></textArea>
    
		        <br>
		        
	        <button class="btn btn-default" type="submit">Crear Solicitud</button>
	
		    </form:form>
	 	</sec:authorize>
	   </c:otherwise>
	</c:choose>

	<br><br>
	<div><h2>Solicitudes de adopción:</h2>
        <c:forEach items="${requirements}" var="requirement">
           <div>
            <tr>
				<h3>
					<c:out
						value="${requirement.new_owner.firstName} ${requirement.new_owner.lastName}:"></c:out>
				<td> </td>
				  
				<td>
				<c:if test="${me==true}">
					<spring:url value="${adoption.id}/requirement/${requirement.id}/acept"
						var="requirementUrl">
						<spring:param name="requirementId" value="${requirement.id}" />
					</spring:url> <a href="${fn:escapeXml(requirementUrl)}" class="btn btn-default">Aceptar</a>
				</c:if>
				</td>
				</h3>
			</tr>
            <tr>
                <td>
                   <c:out value="${requirement.text}"/>
                </td> 

                
            </tr>
            <br>
           </div>
        </c:forEach>
	 </div>

















</petclinic:layout>
