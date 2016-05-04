<%@ include file="/header.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h3>Project ${project.description} - User: ${project.endUser.name}</h3>
<table class="table-striped table-bordered" width="100%">
    <caption>Number of Field Groups: ${fn:length(project.fieldGroups)}</caption>
    <thead>
      <tr>
        <th>#</th>
        <th>Description</th>
        <th>Field Actions</th>
      </tr>
    </thead>
    <tbody>  
        <c:forEach items="${project.fieldGroups}" var="fg" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${fg.description}<br/><i>${fg.notes}</i></td>
                <td>
                    <a href="${linkTo[FieldGroupController].edit(fg)}" class="btn btn-primary">Edit</a>
                    <a href="${linkTo[FieldGroupController].remove(fg)}" onclick="return window.confirm('Are you sure you want to remove the group ${fg.description}?')" class="btn btn-primary">Remove</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<hr/>

<a href="${linkTo[FieldGroupController].form(project)}" class="btn btn-primary">New Field Group</a>
<a href="${linkTo[ProjectController].view(project)}" class="btn btn-default">Back</a>

<%@ include file="/footer.jsp" %>