<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[SearchSessionController].save}">
        <input type="hidden" name="searchSession.project.id" value="${searchSession.project.id}"/>
        Search String <input type="text" name="searchSession.searchString" value="${searchSession.searchString}"/>
        
        Date
        <input type='text' name="searchSession.searchDate" value="${searchSession.searchDate}" maxlength="10"/>

        Repository
        <select name="searchSession.repository.id">
            <c:forEach items="${repositories}" var="r">
                <option value="${r.id}" <c:if test="${searchSession.repository.id == r.id}">selected</c:if> >${r.description}</option>
            </c:forEach>
        </select>

        <input type="hidden" name="searchSession.id" value="${searchSession.id}"/>
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[SearchSessionController].index(searchSession.project)}" class="btn btn-default">Back</a>
        <a href="${linkTo[ProjectController].view(searchSession.project)}" class="btn btn-default">Go to Project</a>
    </form>
<%@ include file="/form-footer.jsp" %>

