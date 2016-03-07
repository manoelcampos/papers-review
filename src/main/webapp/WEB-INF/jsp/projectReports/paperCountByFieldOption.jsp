<%@ include file="/header.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<a href="${linkTo[ProjectReportsController].paperCountByFieldOptionHtml(project)}" class="btn btn-primary">HTML</a>
<a href="${linkTo[ProjectReportsController].paperCountByFieldOptionLatex(project)}" class="btn btn-primary">LaTeX</a>
<a href="${linkTo[ProjectReportsController].paperCountByFieldOptionCsv(project)}" class="btn btn-primary">CSV</a>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>
<br/><br/><br/>

${table}

<hr/>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>


<%@ include file="/footer.jsp" %>
