<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[PaperController].save}">
        <input type="hidden" name="paper.id" value="${paper.id}"/>
        <input type="hidden" name="paper.searchSession.id" value="${paper.searchSession.id}"/>
        
        Title <input type="text" name="paper.title" value="${paper.title}" size="120" maxlength="120"/><br/>
        Proposal Name <input type="text" name="paper.proposalName" value="${paper.proposalName}" size="60" maxlength="100"/><br/>

        Authors <input type="text" name="paper.authors" value="${paper.authors}" size="100" maxlength="400"/><br/>
        Bibliographic Author Names (Ex.: Campos et. al.)
        <input type="text" name="paper.bibliographicAuthorNames" value="${paper.bibliographicAuthorNames}" size="60" maxlength="100"/><br/>

        Publication Year <input type="text" name="paper.publicationYear"  size="6"  maxlength="6" value="${paper.publicationYear}"/>
        DOI <input type="text" name="paper.doi" size="50" maxlength="50" value="${paper.doi}"/><br/>
        URL <input type="text" name="paper.url" size="70" maxlength="70" value="${paper.url}"/><br/>

        Type
        <select name="paper.paperType.id">
            <option value="0">Not Defined</option>
            <c:forEach items="${paperTypes}" var="t">
                <option value="${t.id}" <c:if test="${paper.paperType.id == t.id}">selected</c:if> >${t.description}</option>
            </c:forEach>
        </select>

        Citation Key <input type="text" name="paper.citationKey"  size="30" value="${paper.citationKey}"/>
        <br/>
        
        Survey 
        <select name="paper.survey" id="survey">
            <option value="-1">Not Defined</option>
            <option value="1" <c:if test="${paper.survey==1}">selected</c:if>>Yes</option>
            <option value="0" <c:if test="${paper.survey==0}">selected</c:if>>No</option>
        </select>
        
        Status: 
        <select name="paper.status.id" id="status">
            <option value="0">Not Defined</option>
            <c:forEach items="${status}" var="s">
                <option value="${s.id}" <c:if test="${paper.status.id == s.id}">selected</c:if> >${s}</option>
            </c:forEach>
        </select>

        <br/>
        Search Section
        <select name="paper.searchSession.id">
            <option value="0">Not Defined</option>
            <c:forEach items="${searchSessions}" var="s">
                <option value="${s.id}" <c:if test="${paper.searchSession.id == s.id}">selected</c:if> >${s.id}. ${s.repository.description}</option>
            </c:forEach>
        </select>

        
        <br/>
        Abstract:
        <textarea name="paper.paperAbstract" rows="10" style="width: 100%">${paper.paperAbstract}</textarea>
        
        <br/>
        Notes:
        <textarea name="paper.notes" rows="5" style="width: 100%">${paper.notes}</textarea>

        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[PaperController].view(paper)}" class="btn btn-default">Back</a>
        <a href="${linkTo[ProjectController].view(paper.searchSession.project)}" class="btn btn-default">Go to Project</a>
    </form>
<%@ include file="/form-footer.jsp" %>
