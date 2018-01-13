<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="glb.title"/></title>
    <link href="${contextPath}/css/styles.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width">
</head>
<body>
<div class="text-center">
    <h2><spring:message code="decimal.fraction.title"/></h2>
</div>
<div align="center">
    <form:form method="post" action="${contextPath}/decimalToFraction/grade" modelAttribute="exercise">
        <div class="table center">
            <c:forEach var="fraction" items="${exercise.fractions}" varStatus="step">
                <div class="table-row">
                    <div class="table-cell-border-all">
                            ${step.count})
                    </div>
                    <div class="table-cell-border-all">
                        <div class="table">
                            <div class="table-row">
                                <div class="table-cell min-width">
                                    <c:out value="${fraction.decimal}"/>
                                </div>
                                <div class="table-cell text-center">
                                    <c:out value="="/>
                                </div>
                                <div class="table-cell">
                                    <div class="table">
                                        <div class="table-row">
                                            <spring:bind path="result[${step.index}].numerator">
                                                <div class="table-cell-border-down">
                                                    <form:input path="result[${step.index}].numerator" size="10"
                                                                cssClass="${status.error ? 'has-error' : ''}"/>
                                                </div>
                                            </spring:bind>
                                        </div>
                                        <div class="table-row">
                                            <spring:bind path="result[${step.index}].denominator">
                                                <div class="table-cell-border-up">
                                                    <form:input path="result[${step.index}].denominator" size="10"
                                                                cssClass="${status.error ? 'has-error' : ''}"/>
                                                </div>
                                            </spring:bind>
                                        </div>
                                    </div>
                                </div>
                                <spring:hasBindErrors name="exercise">
                                    <div class="table-cell">
                                        <div class="table">
                                            <div class="table-row">
                                                <div class="table-cell">
                                                    &nbsp;<form:errors path="result[${step.index}].numerator"
                                                                       cssClass="error-label"/>
                                                </div>
                                            </div>
                                            <div class="table-row">
                                                <div class="table-cell">
                                                    &nbsp;<form:errors path="result[${step.index}].denominator"
                                                                       cssClass="error-label"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </spring:hasBindErrors>
                                <ct:gradeResult isGraded="${isGraded}" solved="${exercise.result[step.index].solved}"
                                                correct="${exercise.result[step.index].correct}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <spring:url value="${contextPath}/decimalToFraction" var="restartURL"/>
        <spring:url value="${contextPath}/decimalToFraction/verify" var="verifyURL"/>
        <ct:buttons formName="exercise" restartURL="${restartURL}" verifyURL="${verifyURL}"/>
    </form:form>
</div>
</body>
</html>