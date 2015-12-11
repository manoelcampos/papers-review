<%@ include file="/header.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<a href="${linkTo[ProjectReportsController].papersSummaryTableHtml(project)}" class="btn btn-primary">HTML</a>
<a href="${linkTo[ProjectReportsController].papersSummaryTableLatex(project)}" class="btn btn-primary">LaTeX</a>
<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>
<br/><br/><br/>

${summaryTable}
<br/>
${legendTable}

<hr/>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>


<%@ include file="/footer.jsp" %>
