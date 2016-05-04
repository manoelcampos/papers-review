<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[FieldGroupController].save}">
        <input type="hidden" name="fieldGroup.id" value="${fieldGroup.id}"/>
        <input type="hidden" name="fieldGroup.project.id" value="${project.id}"/>
        Description <input type="text" name="fieldGroup.description" value="${fieldGroup.description}" size="80" maxlength="120"/>
        <br/>
        Notes <input type="text" name="fieldGroup.notes" value="${fieldGroup.notes}" size="120"/>
        <br/>
        Table ID <input type="text" name="fieldGroup.tableId" value="${fieldGroup.tableId}" size="80"/>

        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[ProjectController].fieldGroups(project)}" class="btn btn-default">Back</a>
    </form>
<%@ include file="/form-footer.jsp" %>