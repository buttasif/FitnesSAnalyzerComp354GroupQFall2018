<%@ page language="java" contentType="text/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

{
		"deck": {
				"id": "",
					"cards": [
							<c:forEach items="${card}" var="c" varStatus="loop">
							{"t": "${c.getType()}", "n": "${c.getName()}"}<c:if test="${!loop.last}">,</c:if>
							</c:forEach>
					]
		}
}

