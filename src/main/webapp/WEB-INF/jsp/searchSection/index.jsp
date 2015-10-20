<%@ include file="/header.jsp" %>

<h3>Project: ${project.description} - User: ${project.endUser.name}</h3>

<table class="table-striped">
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
        <c:forEach items="${list}" var="o">
            <tr>
                <td>${o.id}</td>
                <td>${o.searchString}</td>
                <td>${o.repository.description}</td>
                <td>${o.searchDate}</td>
                <td>
                    <a href="${linkTo[SearchSectionController].importPapers}?id=${o.id}">Import Papers</a>
                    <a href="${linkTo[SearchSectionController].edit}?id=${o.id}">Edit</a>
                    <a href="${linkTo[SearchSectionController].remove}?id=${o.id}" onclick="return window.confirm('Are you sure you want to remove the search section ${o.id}?')">Remove</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<hr/>

<a href="${linkTo[SearchSectionController].form}?projectId=${project.id}" class="btn btn-primary">New</a>
<a href="${linkTo[ProjectController].view}?id=${project.id}" class="btn btn-default">Back</a>

<%@ include file="/footer.jsp" %>