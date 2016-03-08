<%@ include file="/header.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<a href="${linkTo[ProjectReportsController].paperCountByFieldOption(project)}" class="btn btn-primary">HTML</a>
<a href="${linkTo[ProjectReportsController].paperCountByFieldOptionLatex(project)}" class="btn btn-primary">LaTeX</a>
<a href="${linkTo[ProjectReportsController].paperCountByFieldOptionCsv(project)}" class="btn btn-primary">CSV</a>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>
<p/>
<form id="frm" method="post" action="${linkTo[ProjectReportsController].paperCountByFieldOption(project, field)}">
    Field
    <select name="field.id">
        <option value="0">All</option>
        <c:forEach items="${fields}" var="f">
            <option value="${f.id}" <c:if test="${field.id == f.id}">selected</c:if> >${f.description}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Show" class="btn btn-primary"/>
</form>
<br/><br/>

${table}

<hr/>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>


<%@ include file="/footer.jsp" %>
