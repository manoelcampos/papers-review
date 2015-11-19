<%@ include file="/header.jsp" %>

${url}

<table class="table-striped table-bordered">
    <thead>
      <tr>
        <th>Description</th>
        <th>User</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>     
        <c:forEach items="${projectList}" var="project">
            <tr>
                <td><a href="${linkTo[ProjectController].view(project)}">${project.description}</a></td>
                <td>${project.endUser.name}</td>
                <td>
                    <a href="${linkTo[ProjectController].edit(project)}">Edit</a>
                    <a href="${linkTo[ProjectController].remove(project)}" onclick="return window.confirm('Are you sure you want to remove the project ${project.description}?')">Remove</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<hr/>

<a href="${linkTo[ProjectController].form}" class="btn btn-primary">New</a>
<a href="${pageContext.request.contextPath}" class="btn btn-default">Back</a>


<%@ include file="/footer.jsp" %>