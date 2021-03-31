<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <a class="btn btn-default" href='<spring:url value="/checkIn/new" htmlEscape="true"/>'>Check In</a>
    
    <form action="/checkIn/new" class="inline">
    	<button class="close" style="Color: red">Check In</button>
	</form>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pet2.jpg" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}"/>
        </div>
    </div>
</petclinic:layout>
