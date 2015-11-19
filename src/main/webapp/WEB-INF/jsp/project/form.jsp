<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[ProjectController].save}">
        Description <input type="text" name="project.description" value="${project.description}"/> 
        
        User
        <select name="project.endUser.id">
            <c:forEach items="${endUsers}" var="u">
                <option value="${u.id}" <c:if test="${project.endUser.id == u.id}">selected</c:if> >${u.name}</option>
            </c:forEach>
        </select>
        
        <input type="hidden" name="project.id" value="${project.id}"/>
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[ProjectController].index}" class="btn btn-default">Back</a>
    </form>
<%@ include file="/form-footer.jsp" %>
