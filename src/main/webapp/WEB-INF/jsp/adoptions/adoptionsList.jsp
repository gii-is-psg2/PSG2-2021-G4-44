<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="adoptions">



	<h2>Mascotas en adopción:</h2>
	<br>
	<table id="adoptionTable" class="table table-striped ownersHead">

		<thead>
			<tr>
				<th>Tipo</th>
				<th>Nombre</th>
				<th>Propietario</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adoptions}" var="adoption">
				<c:if test="${adoption.owner != me}">
				<tr>
					<td><c:out value="${adoption.pet.type}" /></td>
					<td><c:out value="${adoption.pet.name}" /></td>
					<td><c:out value="${adoption.owner.firstName}" /></td>
					<td><spring:url value="/adoptions/details/{adoptionId}"
							var="adoptionUrl">
							<spring:param name="adoptionId" value="${adoption.id}" />
						</spring:url> <a href="${fn:escapeXml(adoptionUrl)}" class="btn btn-default">Detalles</a>
					</td>

				</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>

	<h2>Mis mascotas en adopción:</h2>
	<table id="adoptionTable" class="table table-striped ownersHead">
			<thead>
				<tr>
					<th>Tipo</th>
					<th>Nombre</th>
					<th>Propietario</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${misAdoptions}" var="madoption">
					<tr>
						<td><c:out value="${madoption.pet.type}" /></td>
						<td><c:out value="${madoption.pet.name}" /></td>
						<td><c:out value="${madoption.owner.firstName}" /></td>
						<td><spring:url value="/adoptions/details/{adoptionId}"
								var="adoptionUrl">
								<spring:param name="adoptionId" value="${madoption.id}" />
							</spring:url> <a href="${fn:escapeXml(adoptionUrl)}" class="btn btn-default">Detalles</a>
						</td>

					</tr>
				</c:forEach>
			</tbody>
	</table>

	<h2>
		<sec:authorize access="hasAuthority('owner')">
			<a href="/adoptions/new" class="btn  btn-success"> <span
				class="glyphicon glyphicon-plus" aria-hidden="true"> </span>Añadir
				Adopción
			</a>
		</sec:authorize>
	</h2>

</petclinic:layout>