<%@ include file="/header.jsp" %>

<table class="table-striped table-bordered">
    <thead>
      <tr>
        <th>Description</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>     
        <c:forEach items="${repositoryList}" var="repository">
            <tr>
                <td>${repository.description}</td>
                <td>
                    <a href="${linkTo[RepositoryController].edit(repository)}">Edit</a>
                    <a href="${linkTo[RepositoryController].remove(repository)}" onclick="return window.confirm('Are you sure you want to remove the repository ${repository.description}?')">Remove</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<hr/>

<a href="${linkTo[RepositoryController].form}" class="btn btn-primary">New</a>
<a href="${pageContext.request.contextPath}" class="btn btn-default">Back</a>

<%@ include file="/footer.jsp" %>