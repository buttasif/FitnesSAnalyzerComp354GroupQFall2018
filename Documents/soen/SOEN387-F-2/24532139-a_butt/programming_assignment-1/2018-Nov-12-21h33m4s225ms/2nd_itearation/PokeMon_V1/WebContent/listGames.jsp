<%@ page language="java" contentType="text/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

{

"games": [
	
		<c:forEach items="${games}" var="g" varStatus="loop">
		${g}<c:if test="${!loop.last}">,</c:if>
		</c:forEach>	
	]


}




