<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/header.jsp" %>
    <h3>Project ${o.project.description} - User: ${o.project.endUser.name}</h3>
    
    <h4>Field: ${o.description} &nbsp;&nbsp; Abbreviation: ${o.abbreviation}&nbsp;&nbsp; 
    Type: ${o.fieldType.description}</h4>
    <br/>

    <a href="${linkTo[FieldController].edit}?id=${o.id}">Edit Field</a>
    | <a href="${linkTo[FieldController].remove}?id=${o.id}"  onclick="return window.confirm('Are you sure you want to remove the field ${o.description}?')">Remove Field</a>
    <hr/>
    
    <table class="table-striped table-bordered">
        <thead>
          <tr>
            <th>Option</th>
            <th>Abbreviation</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>     
            <c:forEach items="${o.fieldOptions}" var="fo">
                <tr>
                    <td><a href="${linkTo[FieldOptionController].edit}?id=${fo.id}">${fo.description}</a></td>
                    <td>${fo.abbreviation}</td>
                    <td><a href="${linkTo[FieldOptionController].remove}?id=${fo.id}" onclick="return window.confirm('Are you sure you want to remove the option ${fo.description}?')">Remove</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>    
    
    <hr/>
    <a href="${linkTo[FieldOptionController].form}?fieldId=${o.id}" class="btn btn-primary">New Option</a>
    <a href="${linkTo[ProjectController].fields}?id=${o.project.id}" class="btn btn-default">Back</a>
<%@ include file="/footer.jsp" %>