<%@ include file="/header.jsp" %>

<a href="${linkTo[ProjectReportsController].papersSummaryTable(project)}" class="btn btn-default">Papers Summary Table</a> |
<a href="${linkTo[ProjectReportsController].paperCountByFieldOption('html', project)}" class="btn btn-default">Paper Count by User-defined Fields</a> |

<a href="${linkTo[ProjectReportsController].paperCountByStatus(project)}" class="btn btn-default">Paper Count by Status</a>
<a href="${linkTo[ProjectReportsController].paperCountByRepository(project)}" class="btn btn-default">Paper Count by Repository</a>
<a href="${linkTo[ProjectReportsController].paperCountByType(project)}" class="btn btn-default">Paper Count by Type</a>
<hr/>

<a href="${linkTo[ProjectController].index}" class="btn btn-primary">Back</a>


<%@ include file="/footer.jsp" %>