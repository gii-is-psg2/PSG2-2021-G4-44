<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2>
        <c:if test="${vet['new']}">Nuevo </c:if> Veterinario
    </h2>
    <form:form modelAttribute="vet" class="form-horizontal" id="add-vet-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="First Name" name="firstName"/>
            <petclinic:inputField label="Last Name" name="lastName"/>
            <div class="control-group"> 
            
            
            <c:choose>
                        <c:when test="${vet['new']}">
                            <div class="control-group" optional>


                 <c:forEach items="${specialties}" var="sp">
                  <input type="checkbox" name="specialties" value="${sp.id}">  ${sp.name}<br>
                   </c:forEach>

            </div>
                        </c:when>
                        <c:otherwise>
                           <div class="control-group">


                 <c:forEach items="${specialtiesNoSeleccionadas}" var="sp">
                  <input type="checkbox" name="specialties" value="${sp.id}">  ${sp.name}<br>
                   </c:forEach>

            </div>
                        </c:otherwise>
                    </c:choose>

            </div>

        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${vet['new']}">
                        <button class="btn btn-default" type="submit">Añadir Veterinario</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar veterinario</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
