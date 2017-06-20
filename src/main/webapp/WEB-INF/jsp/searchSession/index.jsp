<%@ include file="/header.jsp" %>

<h3>Project: ${project.description} - User: ${project.endUser.name}</h3>

<table class="table-striped table-bordered">
    <thead>
      <tr>
        <th>Id</th>
        <th>Search String</th>
        <th>Repository</th>
        <th>Date</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>     
        <c:forEach items="${searchSessionList}" var="searchSession">
            <tr>
                <td>${searchSession.id}</td>
                <td>${searchSession.searchString}</td>
                <td>${searchSession.repository.description}</td>
                <td>${searchSession.searchDate}</td>
                <td>
                    <a href="${linkTo[SearchSessionController].importPapers(searchSession)}">Import Papers</a> |
                    <a href="${linkTo[PaperController].form(searchSession)}">Add Paper Manually</a> |
                    <a href="${linkTo[SearchSessionController].edit(searchSession)}">Edit</a> |
                    <a href="${linkTo[SearchSessionController].remove(searchSession)}" onclick="return window.confirm('Are you sure you want to remove the search session ${searchSession.id}?')">Remove</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<hr/>

<a href="${linkTo[SearchSessionController].form(project)}" class="btn btn-primary">New</a>
<a href="${linkTo[ProjectController].view(project)}" class="btn btn-default">Back</a>

<%@ include file="/footer.jsp" %>