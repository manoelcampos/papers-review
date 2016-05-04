<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[FieldOptionController].save}">
        <input type="hidden" name="fieldOption.id" value="${fieldOption.id}"/>
        <input type="hidden" name="fieldOption.field.id" value="${field.id}"/>
        Description <input type="text" name="fieldOption.description" value="${fieldOption.description}" size="80"/>
        Abbreviation <input type="text" name="fieldOption.abbreviation" value="${fieldOption.abbreviation}" size="20"/>
        Parent Option
        <select name="fieldOption.parentFieldOption.id">
            <option value="0">None</option>
            <c:forEach items="${fieldOptions}" var="i">
                <option value="${i.id}">${i.description}</option>
            </c:forEach>
        </select>
        Show in Reports
        <select name="fieldOption.showInReports">
            <option value="true" <c:if test="${field.showInReports == true}">selected</c:if> >Yes</option>
            <option value="false" <c:if test="${field.showInReports != true}">selected</c:if> >No</option>
        </select><br/>
        <br/>
        Notes:
        <textarea name="fieldOption.notes" rows="15" style="width: 100%">${fieldOption.notes}</textarea>
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[FieldController].view(fieldOption.field)}" class="btn btn-default">Back</a>                
        <a href="${linkTo[ProjectController].view(fieldOption.field.project)}" class="btn btn-default">Go to Project</a>
    </form>
<%@ include file="/form-footer.jsp" %>