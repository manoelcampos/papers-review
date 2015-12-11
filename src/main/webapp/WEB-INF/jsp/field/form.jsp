<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[FieldController].save}">
        <input type="hidden" name="field.id" value="${field.id}"/>
        <input type="hidden" name="field.project.id" value="${project.id}"/>
        Description <input type="text" name="field.description" value="${field.description}" size="80"/>
        Abbreviation <input type="text" name="field.abbreviation" value="${field.abbreviation}"  size="20"/>
        Show in Reports
        <select name="field.showInReports">
            <option value="true" <c:if test="${field.showInReports == true}">selected</c:if> >Yes</option>
            <option value="false" <c:if test="${field.showInReports != true}">selected</c:if> >No</option>
        </select>

        Type
        <select name="field.fieldType.id">
            <c:forEach items="${fieldTypes}" var="t">
                <option value="${t.id}" <c:if test="${field.fieldType.id == t.id}">selected</c:if> >${t.description}</option>
            </c:forEach>
        </select>
        
        <br/>
        Notes:
        <textarea name="field.notes" rows="15" style="width: 100%">${field.notes}</textarea>
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[ProjectController].view(project)}" class="btn btn-default">Back</a>
    </form>
<%@ include file="/form-footer.jsp" %>