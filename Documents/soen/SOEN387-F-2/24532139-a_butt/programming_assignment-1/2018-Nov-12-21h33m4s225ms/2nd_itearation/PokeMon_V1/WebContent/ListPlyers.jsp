<%@ page language="java" contentType="text/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

{
		"players": [
		
			<c:foreach item="${players}" var="player" varStatus="lOOP">
			{"id": "${player.id}", "user": "${player.name}"}<c:if test="${!loop.last} }">,</c:if>
			</c:foreach>
		]
}


