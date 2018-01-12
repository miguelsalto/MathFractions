<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Fractions Practice</title>
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>
<h2>Decimal a Fracci&oacute;n:</h2>
<form:form method="post" action="/decimalToFraction/grade" modelAttribute="exercise">
    <div style="display: table">
        <c:forEach var="fraction" items="${exercise.fractions}" varStatus="step">
            <div style="display: table-row;">
                <div class="table-cell-border-all">
                        ${step.count})
                </div>
                <div class="table-cell-border-up-down">
                    <c:out value="${fraction.decimal}" />
                </div>
                <div class="table-cell-border-up-down">
                    =
                </div>
                <div class="table-cell-border-up-down">
                    <div style="display: table">
                        <div style="display: table-row">
                            <spring:bind path="result[${step.index}].numerator">
                                <div class="table-cell-border-down">
                                    <form:input path="result[${step.index}].numerator" size="10" cssClass="${status.error ? 'has-error' : ''}"/>
                                </div>
                            </spring:bind>
                        </div>
                        <div style="display: table-row">
                            <spring:bind path="result[${step.index}].denominator">
                                <div class="table-cell-border-up">
                                    <form:input path="result[${step.index}].denominator" size="10" cssClass="${status.error ? 'has-error' : ''}"/>
                                </div>
                            </spring:bind>
                        </div>
                    </div>
                </div>
                <div class="table-cell-border-up-down-right">
                    <div style="display: table">
                        <div style="display: table-row">
                            <div class="table-cell">
                                &nbsp;<form:errors path="result[${step.index}].numerator" cssClass="error-label"/>
                            </div>
                        </div>
                        <div style="display: table-row">
                            <div class="table-cell">
                                &nbsp;<form:errors path="result[${step.index}].denominator" cssClass="error-label"/>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${not empty isGraded}">
                    <div class="table-cell">
                        <c:choose>
                            <c:when test="${exercise.result[step.index].correct}">
                                <span class="ok-label">OK</span>
                            </c:when>
                            <c:otherwise>
                                <span class="error-label">ERROR</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
    <div>
        <br />
        <input type="submit" value="Calificar"/>
        <spring:url value="/decimalToFraction" var="restartURL" />

    </div>
</form:form>
        <button onclick="location.href='${restartURL}'">Reiniciar</button>
</body>
</html>