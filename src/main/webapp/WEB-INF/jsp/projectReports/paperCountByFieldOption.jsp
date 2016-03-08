<%@ include file="/header.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<a href="${linkTo[ProjectReportsController].paperCountByFieldOption('html', project)}" class="btn btn-primary">HTML</a>
<a href="${linkTo[ProjectReportsController].paperCountByFieldOption('latex', project)}" class="btn btn-primary">LaTeX</a>
<a href="${linkTo[ProjectReportsController].paperCountByFieldOption('csv', project)}" class="btn btn-primary">CSV</a>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>
<p/><p/>
<form id="frm" method="post" action="${linkTo[ProjectReportsController].reload}">
    <input type="hidden" name="project.id" value="${project.id}">
    <input type="hidden" name="dataFormat" value="${dataFormat}">

    Field
    <select name="field.id" id="field.id" onchange="$('#frm').submit();">
        <c:forEach items="${fields}" var="f">
            <option value="${f.id}" <c:if test="${field.id == f.id}">selected</c:if> >${f.description}</option>
        </c:forEach>
    </select>
</form>
<br/><br/>

${table}

<hr/>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>


<%@ include file="/footer.jsp" %>
