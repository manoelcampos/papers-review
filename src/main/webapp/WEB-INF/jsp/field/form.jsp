<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[FieldController].save}">
        <input type="hidden" name="o.project.id" value="${o.project.id}"/>
        Description <input type="text" name="o.description" value="${o.description}"/>
        Abbreviation <input type="text" name="o.abbreviation" value="${o.abbreviation}"/>
        Type
        <select name="o.fieldType.id">
            <c:forEach items="${fieldTypes}" var="t">
                <option value="${t.id}" <c:if test="${o.fieldType.id == t.id}">selected</c:if> >${t.description}</option>
            </c:forEach>
        </select>

        <input type="hidden" name="o.id" value="${o.id}"/>
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[ProjectController].fields}?id=${o.project.id}" class="btn btn-default">Back</a>
        <a href="${linkTo[ProjectController].view}?id=${o.project.id}" class="btn btn-default">Go to Project</a>
    </form>
<%@ include file="/form-footer.jsp" %>