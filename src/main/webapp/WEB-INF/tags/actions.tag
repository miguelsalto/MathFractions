<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="formName" required="true" rtexprvalue="true" %>
<%@attribute name="restartURL" required="true" rtexprvalue="true" %>
<%@attribute name="verifyURL" required="true" rtexprvalue="true" %>

<div class="table">
    <div class="table-row">
        <div class="table">
            <div class="table-cell text-left">
                <input type="button" onclick="location.href='${restartURL}'"
                       value="<spring:message code="glb.restart"/>"/>
            </div>
            <div class="table-cell">
                <input type="button"
                       onclick="var form = document.getElementById('${formName}'); form.action='${verifyURL}'; form.submit();"
                       value="<spring:message code="glb.verify"/>"/>
            </div>
            <div class="table-cell">
                <input type="submit" value="<spring:message code="glb.evaluate"/>"/>
            </div>
        </div>
    </div>
    <div class="table-row">
        <a href="${pageContext.request.contextPath}/"><spring:message code="glb.return"/></a>
    </div>
</div>