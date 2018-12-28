<%@ page language="java" contentType="text/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

{
		"players": [
		
			<c:forEach items="${players}" var="player" varStatus="loop">
			{"id": ${player.id}, "user": "${player.name}"}<c:if test="${!loop.last}">,</c:if>
			</c:forEach>
		]
}


