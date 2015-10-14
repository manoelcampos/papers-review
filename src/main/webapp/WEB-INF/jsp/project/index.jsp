<%@ include file="/header.jsp" %>

<table class="table-striped">
    <thead>
      <tr>
        <th>Description</th>
        <th>User</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>     
        <c:forEach items="${projectList}" var="o">
            <tr>
                <td><a href="${linkTo[ProjectController].view}?id=${o.id}">${o.description}</a></td>
                <td>${o.endUser.name}</td>
                <td>
                    <a href="${linkTo[ProjectController].edit}?id=${o.id}">Edit</a>
                    <a href="${linkTo[ProjectController].remove}?id=${o.id}" onclick="return window.confirm('Are you sure you want to remove the project ${o.description}?')">Remove</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a href="${linkTo[ProjectController].form}" class="btn btn-primary">New</a>
<a href="${pageContext.request.contextPath}" class="btn btn-default">Back</a>


<%@ include file="/footer.jsp" %>