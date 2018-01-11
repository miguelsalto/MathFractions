<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Fractions Practice</title>
    <style>
        .table-cell {
            display: table-cell;
            padding: 3px 10px;
            text-align: right;
            border: thin solid;
        }
    </style>
</head>
<body>
<h2>Decimal a Fracci&oacute;n:</h2>
<form:form method="post" action="/decimalToFraction/grade" modelAttribute="exercise">
    <div style="display: table">
        <c:forEach var="fraction" items="${exercise.fractions}" varStatus="step">
            <div style="display: table-row;">
                <div class="table-cell">
                        ${step.count}
                </div>
                <div class="table-cell">
                    <c:out value="${fraction.decimal}"></c:out>
                </div>
                <div class="table-cell">
                    <div style="display: table">
                        <div style="display: table-row">
                            <div class="table-cell">
                                <form:input path="result[${step.index}].numerator" size="10"></form:input>
                            </div>
                        </div>
                        <div style="display: table-row">
                            <div class="table-cell">
                                <form:input path="result[${step.index}].denominator" size="10"></form:input>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${not empty isGraded}">
                    <div class="table-cell">
                        <c:choose>
                            <c:when test="${exercise.result[step.index].correct}">
                                OK
                            </c:when>
                            <c:otherwise>
                                ERROR
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
    </div>
</form:form>
</body>
</html>