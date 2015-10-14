<%@ include file="/header.jsp" %>

<table class="table-striped">
    <thead>
      <tr>
        <th>Description</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>     
        <c:forEach items="${repositoryList}" var="o">
            <tr>
                <td>${o.description}</td>
                <td>
                    <a href="${linkTo[RepositoryController].edit}?id=${o.id}">Edit</a>
                    <a href="${linkTo[RepositoryController].remove}?id=${o.id}" onclick="return window.confirm('Are you sure you want to remove the repository ${o.description}?')">Remove</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="${linkTo[RepositoryController].form}" class="btn btn-primary">New</a>
<a href="${pageContext.request.contextPath}" class="btn btn-default">Back</a>

<%@ include file="/footer.jsp" %>