<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[ProjectController].save}">
        Description <input type="text" name="o.description" value="${o.description}"/> 
        
        User
        <select name="o.endUser.id">
            <c:forEach items="${endUsers}" var="u">
                <option value="${u.id}" <c:if test="${o.endUser.id == u.id}">"selected"</c:if> >${u.name}</option>
            </c:forEach>
        </select>
        
        <input type="hidden" name="o.id" value="${o.id}"/>
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[ProjectController].index}" class="btn btn-default">Back</a>
    </form>
<%@ include file="/form-footer.jsp" %>
