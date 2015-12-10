<%@ include file="/header.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h3>Project ${project.description} - User: ${project.endUser.name}</h3>
<table class="table-striped table-bordered" width="100%">
    <caption>Number of Fields: ${fn:length(project.fields)}</caption>
    <thead>
      <tr>
        <th>#</th>
        <th>Description</th>
        <th>Abbreviation</th>
        <th>Type</th>
        <th width="30%">Options</th>
        <th>Field Actions</th>
      </tr>
    </thead>
    <tbody>  
        <c:if test="${project.fields != null}">
            <c:forEach items="${project.fields}" var="f" varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td><a href="${linkTo[FieldController].view(f)}">${f.description}</a></td>
                    <td>${f.abbreviation}</td>
                    <td>${f.fieldType.description}</td>
                    <td>
                        <table class="table-striped table-bordered"  width="100%">
                        <c:forEach items="${f.fieldOptions}" var="fo">
                            <tr>
                                <td>
                                    <a href="${linkTo[FieldOptionController].edit(fo)}">${fo.description}</a>
                                </td>
                                <td width="8%">                                    
                                    <a href="${linkTo[FieldOptionController].remove(fo)}" onclick="return window.confirm('Are you sure you want to remove the option ${fo.description}?')">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </table>
                    </td>
                    <td>
                        <a href="${linkTo[FieldController].edit(f)}" class="btn btn-primary">Edit</a>
                        <a href="${linkTo[FieldController].remove(f)}" onclick="return window.confirm('Are you sure you want to remove the field ${f.description}?')" class="btn btn-primary">Remove</a>
                        <c:if test="${f.fieldType.abbreviation!='S'}">
                            <a href="${linkTo[FieldOptionController].form(f)}" class="btn btn-primary">New Option</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </tbody>
</table>

<hr/>

<a href="${linkTo[FieldController].form(project)}" class="btn btn-primary">New Field</a>
<a href="${linkTo[ProjectController].view(project)}" class="btn btn-default">Back</a>

<%@ include file="/footer.jsp" %>