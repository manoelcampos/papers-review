<%@ include file="/header.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<a href="${linkTo[ProjectReportsController].papersSummaryTable('html',project)}" class="btn btn-primary">HTML</a>
<a href="${linkTo[ProjectReportsController].papersSummaryTable('latex',project)}" class="btn btn-primary">LaTeX</a>
<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>
<br/><br/>

<form id="frm" method="post" action="${linkTo[ProjectReportsController].filterSummary}">
    <input type="hidden" name="project.id" value="${project.id}" />
    <input type="hidden" name="dataFormat" value="${dataFormat}" />

    Group
    <select name="fieldGroup.id"  onchange="$('#frm').submit();">
        <c:forEach items="${fieldGroups}" var="g">
            <option value="${g.id}" <c:if test="${fieldGroup.id == g.id}">selected</c:if> >${g.description}</option>
        </c:forEach>
    </select>
</form>

<br/>

${summaryTable}
<br/>
${legendTable}

<hr/>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>


<%@ include file="/footer.jsp" %>
