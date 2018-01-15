<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="titleKey" required="true" rtexprvalue="false" %>
<div class="text-center">
    <h2><spring:message code="${titleKey}"/></h2>
</div>