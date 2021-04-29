<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="causes">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#date").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${cause['new']}">Nueva </c:if>Causa</h2>

        <b>Causa</b>

        <form:form modelAttribute="cause" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Nombre" name="name"/>
                <petclinic:inputField label="Organizacion" name="organization"/>
                <petclinic:inputField label="Descripcion" name="description"/>
                <petclinic:inputField label="Dinero objetivo" name="budgetTarget"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="causeId" value="${cause.id}"/>
                    <button class="btn btn-default" type="submit">Añadir causa</button>
                </div>
            </div>
        </form:form>

        <br/>

    </jsp:body>

</petclinic:layout>
