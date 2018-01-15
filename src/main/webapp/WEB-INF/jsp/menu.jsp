<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<ct:header-css/>
<body>
<ct:header titleKey="content.table"/>
<div align="center">
    <div class="table center">
        <div class="table-row">
            <div class="table-cell-border-all text-center">
                <spring:message code="glb.convertions"/>
            </div>
        </div>
        <div class="table-row">
            <div class="table-cell-border-all text-center">
                <a href="${contextPath}/decimalToFraction"><spring:message code="decimal.fraction.title"/></a>
            </div>
        </div>
        <div class="table-row">
            <div class="table-cell-border-all text-center">
                <a href="${contextPath}/fractionToDecimal"><spring:message code="fraction.decimal.title"/></a>
            </div>
        </div>
        <div class="table-row">
            <div class="table-cell-border-all text-center">
                <a href="${contextPath}/mixedToDecimal"><spring:message code="mixed.decimal.title"/></a>
            </div>
        </div>
        <div class="table-row">
            <div class="table-cell-border-all text-center">
                <a href="${contextPath}/decimalToMixed"><spring:message code="decimal.mixed.title"/></a>
            </div>
        </div>
    </div>
</div>
</body>
</html>