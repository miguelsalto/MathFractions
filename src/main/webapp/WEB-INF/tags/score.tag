<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="score" required="true" rtexprvalue="true" type="java.lang.Double" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty score}">
<div class="text-center">
    <div class="score ${score > 7 ? 'ok-label' : 'error-label'}"><spring:message code="glb.score"/>: ${score}</div>
</div>
</c:if>
