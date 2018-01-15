<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<ct:header-css />
<body>
<ct:header titleKey="decimal.mixed.title" />
<div align="center">
    <form:form method="post" action="${contextPath}/decimalToMixed/grade" modelAttribute="exercise">
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
                                <spring:hasBindErrors name="exercise">
                                    <div class="table-cell text-center">
                                        <form:errors path="result[${step.index}].wholeNumber" cssClass="error-label"/>
                                    </div>
                                </spring:hasBindErrors>
                                <div class="table-cell">
                                    <div class="table">
                                        <div class="table-row">
                                            <div class="table-cell">
                                                <spring:bind path="result[${step.index}].wholeNumber">
                                                    <div class="table-cell">
                                                        <form:input path="result[${step.index}].wholeNumber" size="10"
                                                                    cssClass="${status.error ? 'has-error' : ''}"
                                                                    autocomplete="off"/>
                                                    </div>
                                                </spring:bind>
                                            </div>
                                            <div class="table-cell">
                                                <div class="table">
                                                    <div class="table-row">
                                                        <spring:bind path="result[${step.index}].numerator">
                                                            <div class="table-cell-border-down">
                                                                <form:input path="result[${step.index}].numerator" size="10"
                                                                            cssClass="${status.error ? 'has-error' : ''}"
                                                                            autocomplete="off"/>
                                                            </div>
                                                        </spring:bind>
                                                    </div>
                                                    <div class="table-row">
                                                        <spring:bind path="result[${step.index}].denominator">
                                                            <div class="table-cell-border-up">
                                                                <form:input path="result[${step.index}].denominator" size="10"
                                                                            cssClass="${status.error ? 'has-error' : ''}"
                                                                            autocomplete="off"/>
                                                            </div>
                                                        </spring:bind>
                                                    </div>
                                                </div>
                                            </div>
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
        <ct:score score="${score}"/>
        <spring:url value="${contextPath}/decimalToMixed" var="restartURL"/>
        <spring:url value="${contextPath}/decimalToMixed/verify" var="verifyURL"/>
        <ct:actions formName="exercise" restartURL="${restartURL}" verifyURL="${verifyURL}"/>
    </form:form>
</div>
</body>
</html>