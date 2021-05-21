<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">
    <h2>Causas</h2>

    <table id="causesTable" class="table table-striped ownersHead" >
        <thead>
        <tr>
             <th>Nombre</th>
            <th>Recaudado</th>
            <th>Objetivo</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${causes}" var="cause">
            <tr>
                <td>
                   <c:out value="${cause.name}"/>
                </td>
                <td>
                    <c:out value="${cause.budgetAchieved}"/> <!-- crear en clase causa -->
                </td>
                <td>
                    <c:out value="${cause.budgetTarget}"/>
                </td>
             
                <td>
                   <spring:url value="/causes/{causeId}/donations/new" var="createDonationUrl">
       					<spring:param name="causeId" value="${cause.id}"/>
   					</spring:url>
   					<a href="${fn:escapeXml(createDonationUrl)}" class="btn btn-default">Realizar donacion</a>  
   				</td>
                <td>
                    <spring:url value="/causes/{causeId}" var="causeUrl">
                        <spring:param name="causeId" value="${cause.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(causeUrl)}" class="btn btn-default">Detalles</a>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>
		<a href="/causes/new" class="btn btn-default"><span
			class="glyphicon glyphicon-plus" aria-hidden="true"></span>Crear Causa</a>

	</p>
</petclinic:layout>