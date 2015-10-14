<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[RepositoryController].save}">
        Description <input type="text" name="o.description" value="${o.description}"/>
        <input type="hidden" name="o.id" value="${o.id}"/>
        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[RepositoryController].index}" class="btn btn-default">Back</a>
    </form>
<%@ include file="/form-footer.jsp" %>