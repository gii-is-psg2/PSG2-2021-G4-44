<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">

	<a href="/causes" class="btn btn-default">Volver a la lista</a>

    <h2>Causa <c:out value="${cause.id}"/> : <c:out value="${cause.name}"/></h2>
    
    <p>Detalles de la causa: <c:out value="${cause.description}"/></p></b>
    <p>Cantidad objetivo: <c:out value="${cause.budgetTarget}"/> euros</p></b>
 	<p>Organizaci�n tras el objetivo: <c:out value="${cause.organization}"/></p></b></b></b>
 	
 	
    <table id="donationsTable" class="table table-striped" >
        <thead>
        <tr>
            <th>Donante</th>
            <th>Cantidad</th>
            <th>Fecha</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${donations}" var="donation">
            <tr>
                <td>
                   <c:out value="${donation.client}"/>
                </td>
                <td>
                    <c:out value="${donation.amount}"/> <!-- crear en clase causa -->
                </td>
                <td>
                    <c:out value="${donation.date}"/>
                </td>
      
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>