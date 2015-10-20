<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[FieldOptionController].save}">
        <input type="hidden" name="o.field.id" value="${o.field.id}"/>
        <input type="hidden" name="o.field.project.id" value="${o.field.project.id}"/>
        Description <input type="text" name="o.description" value="${o.description}"/>
        Abbreviation <input type="text" name="o.abbreviation" value="${o.abbreviation}"/>

        <input type="hidden" name="o.id" value="${o.id}"/>
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[ProjectController].fields}?id=${o.field.project.id}" class="btn btn-default">Back</a>
        <a href="${linkTo[ProjectController].view}?id=${o.field.project.id}" class="btn btn-default">Go to Project</a>
    </form>
<%@ include file="/form-footer.jsp" %>