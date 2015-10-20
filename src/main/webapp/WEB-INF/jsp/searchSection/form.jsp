<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[SearchSectionController].save}">
        <input type="hidden" name="o.project.id" value="${o.project.id}"/>
        Search String <input type="text" name="o.searchString" value="${o.searchString}"/>
        
        Date
        <input type='text' name="o.searchDate" value="${o.searchDate}" maxlength="10"/>

        Repository
        <select name="o.repository.id">
            <c:forEach items="${repositories}" var="r">
                <option value="${r.id}" <c:if test="${o.repository.id == r.id}">"selected"</c:if> >${r.description}</option>
            </c:forEach>
        </select>

        <input type="hidden" name="o.id" value="${o.id}"/>
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[SearchSectionController].index}?projectId=${o.project.id}" class="btn btn-default">Back</a>
        <a href="${linkTo[ProjectController].view}?id=${o.project.id}" class="btn btn-default">Go to Project</a>
    </form>
<%@ include file="/form-footer.jsp" %>

