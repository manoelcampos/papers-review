<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[FieldController].save}">
        <input type="hidden" name="o.project.id" value="${o.project.id}"/>
        Search String <input type="text" name="o.searchString" value="${o.searchString}"/>
        Repository
        <select name="o.repository.id">
            <c:forEach items="${repositories}" var="t">
                <option value="${t.id}" <c:if test="${o.fieldType.id == t.id}">"selected"</c:if> >${t.description}</option>
            </c:forEach>
        </select>

        <input type="hidden" name="o.id" value="${o.id}"/>
        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[ProjectController].view}?id=${o.project.id}" class="btn btn-default">Back</a>
    </form>
<%@ include file="/form-footer.jsp" %>