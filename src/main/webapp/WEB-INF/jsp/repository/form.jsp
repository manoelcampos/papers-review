<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[RepositoryController].save}">
        Description <input type="text" name="repository.description" value="${repository.description}"/>
        <input type="hidden" name="repository.id" value="${repository.id}"/>
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[RepositoryController].index}" class="btn btn-default">Back</a>
    </form>
<%@ include file="/form-footer.jsp" %>