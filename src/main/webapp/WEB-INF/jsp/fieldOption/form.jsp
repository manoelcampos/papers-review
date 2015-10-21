<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[FieldOptionController].save}">
        <input type="hidden" name="o.field.id" value="${o.field.id}"/>
        <c:if test="${not empty projectId}">
            <input type="hidden" name="o.field.project.id" value="${projectId}"/>
        </c:if>
        Description <input type="text" name="o.description" value="${o.description}"/>
        Abbreviation <input type="text" name="o.abbreviation" value="${o.abbreviation}"/>

        <input type="hidden" name="o.id" value="${o.id}"/>
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <c:choose>
            <c:when test="${not empty projectId}">
                <a href="${linkTo[ProjectController].fields}?id=${projectId}" class="btn btn-default">Back</a>
            </c:when>
            <c:otherwise>
                <a href="${linkTo[FieldController].view}?id=${o.field.id}" class="btn btn-default">Back</a>                
            </c:otherwise>
        </c:choose>
        <a href="${linkTo[ProjectController].view}?id=${o.field.project.id}" class="btn btn-default">Go to Project</a>
    </form>
<%@ include file="/form-footer.jsp" %>