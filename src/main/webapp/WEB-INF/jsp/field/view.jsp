<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/header.jsp" %>
    <h3>Project ${field.project.description} - User: ${field.project.endUser.name}</h3>
    
    <h4>Field: ${field.description} &nbsp;&nbsp; Abbreviation: ${field.abbreviation}&nbsp;&nbsp; 
    Type: ${field.fieldType.description}</h4>
    Show in Reports: ${field.showInReports}<br/>

    Notes:<br/>
    <div style="width: 100%; border: #cccccc 1px;">${field.notes}</div>
    
    <br/>

    <a href="${linkTo[FieldController].edit(field)}">Edit Field</a> | 
    <a href="${linkTo[FieldController].remove(field)}"  onclick="return window.confirm('Are you sure you want to remove the field ${field.description}?')">Remove Field</a>
    <hr/>
    
    <table class="table-striped table-bordered">
        <thead>
          <tr>
            <th>Option</th>
            <th>Parent</th>
            <th>Abbreviation</th>
            <th>Notes</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>     
            <c:forEach items="${field.fieldOptions}" var="fo">
                <tr>
                    <td><a href="${linkTo[FieldOptionController].edit(fo)}">${fo.description}</a></td>
                    <td>${fo.parentFieldOption.description}</td>
                    <td>${fo.abbreviation}</td>
                    <td>${fo.notes}</td>
                    <td><a href="${linkTo[FieldOptionController].remove(fo)}" onclick="return window.confirm('Are you sure you want to remove the option ${fo.description}?')">Remove</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>    
    
    <hr/>
    <a href="${linkTo[FieldOptionController].form(field)}" class="btn btn-primary">New Option</a>
    <a href="${linkTo[ProjectController].view(project)}" class="btn btn-default">Back</a>

<%@ include file="/footer.jsp" %>