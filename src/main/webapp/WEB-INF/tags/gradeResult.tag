<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="isGraded" required="true" rtexprvalue="true" %>
<%@attribute name="solved" required="true" rtexprvalue="true" %>
<%@attribute name="correct" required="true" rtexprvalue="true" %>

<c:if test="${not empty isGraded}">
    <div class="table-cell text-left min-width">
        <c:choose>
            <c:when test="${solved}">
                <c:choose>
                    <c:when test="${correct}">
                        <span class="ok-label"><spring:message code="glb.ok"/></span>
                    </c:when>
                    <c:otherwise>
                        <span class="error-label"><spring:message code="glb.error"/></span>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <spring:message code="glb.pending"/>
            </c:otherwise>
        </c:choose>
    </div>
</c:if>