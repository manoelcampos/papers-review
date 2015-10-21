<%@ include file="/header.jsp" %>

<h3>Project ${o.description} - User: ${o.endUser.name}</h3>

<table class="table-striped" width="100%">
    <thead>
      <tr>
        <th>Description</th>
        <th>Abbreviation</th>
        <th>Type</th>
        <th width="30%">Options</th>
        <th>Field Actions</th>
      </tr>
    </thead>
    <tbody>  
        <c:if test="${o.fields != null}">
            <c:forEach items="${o.fields}" var="f">
                <tr>
                    <td><a href="${linkTo[FieldController].view}?id=${f.id}">${f.description}</a></td>
                    <td>${f.abbreviation}</td>
                    <td>${f.fieldType.description}</td>
                    <td>
                        <table class="table-striped"  width="100%">
                        <c:forEach items="${f.fieldOptions}" var="i">
                            <tr>
                                <td>
                                    <a href="${linkTo[FieldOptionController].edit}?id=${i.id}&projectId=${o.id}">${i.description}</a>
                                </td>
                                <td width="8%">                                    
                                    <a href="${linkTo[FieldOptionController].remove}?id=${i.id}&projectId=${o.id}" onclick="return window.confirm('Are you sure you want to remove the option ${i.description}?')">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </table>
                    </td>
                    <td>
                        <a href="${linkTo[FieldController].edit}?id=${f.id}" class="btn btn-primary">Edit</a>
                        <a href="${linkTo[FieldController].remove}?id=${f.id}" onclick="return window.confirm('Are you sure you want to remove the field ${f.description}?')" class="btn btn-primary">Remove</a>
                        <c:if test="${f.fieldType.abbreviation!='S'}">
                            <a href="${linkTo[FieldOptionController].form}?fieldId=${f.id}&projectId=${o.id}" class="btn btn-primary">New Option</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </tbody>
</table>

<hr/>

<a href="${linkTo[FieldController].form}?projectId=${o.id}" class="btn btn-primary">New Field</a>
<a href="${linkTo[ProjectController].view}?id=${o.id}" class="btn btn-default">Back</a>

<%@ include file="/footer.jsp" %>