<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<ct:header-css/>
<body>
<ct:header titleKey="fraction.decimal.title"/>
<div align="center">
    <form:form method="post" action="${contextPath}/fractionToDecimal/grade" modelAttribute="exercise">
        <div class="table center">
            <c:forEach var="fraction" items="${exercise.fractions}" varStatus="step">
                <div class="table-row">
                    <div class="table-cell-border-all">
                            ${step.count})
                    </div>
                    <div class="table-cell-border-all">
                        <div class="table">
                            <div class="table-row">
                                <div class="table-cell">
                                    <div class="table">
                                        <div class="table-row">
                                            <div class="table-cell-border-down min-width text-center">
                                                <c:out value="${fraction.numerator}"/>
                                            </div>
                                        </div>
                                        <div class="table-row">
                                            <div class="table-cell-border-up text-center">
                                                <c:out value="${fraction.denominator}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-cell text-center">
                                    <c:out value="="/>
                                </div>
                                <div class="table-cell">
                                    <spring:bind path="result[${step.index}].decimal">
                                        <form:input path="result[${step.index}].decimal" size="10"
                                                    cssClass="${status.error ? 'has-error' : ''}"
                                                    autocomplete="off"/>
                                    </spring:bind>
                                </div>
                                <spring:hasBindErrors name="exercise">
                                    <div class="table-cell">
                                        <div class="table-cell">
                                            &nbsp;<form:errors path="result[${step.index}].decimal"
                                                               cssClass="error-label"/>
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
        <spring:url value="${contextPath}/fractionToDecimal" var="restartURL"/>
        <spring:url value="${contextPath}/fractionToDecimal/verify" var="verifyURL"/>
        <ct:actions formName="exercise" restartURL="${restartURL}" verifyURL="${verifyURL}"/>
    </form:form>
</div>
</body>
</html>