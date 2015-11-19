<%@ include file="/form-header.jsp" %>
    <form method="post" enctype="multipart/form-data" action="${linkTo[SearchSessionController].saveImportedPapers}">
        <input type="hidden" name="searchSession.id" value="${searchSession.id}"/>
        BibTeX file <input type="file" name="bibTexFile" />
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[SearchSessionController].index(searchSession.project)}" class="btn btn-default">Back</a>
        <a href="${linkTo[ProjectController].view(searchSession.project)}" class="btn btn-default">Go to Project</a>
    </form>
        
    <table class="table-striped table-bordered">
        <tr><th>#</th><th>Title</th><th>Search Session</th><th>Year</th><th>Citation Key</th></tr>
        <c:forEach items="${paperList}" var="p" varStatus="status">
            <tr>
                <td>${status.index+1}</td><td>${p.title}</td><td>${p.searchSession.id}</td>
                <td>${p.publicationYear}</td><td>${p.citationKey}</td>
            </tr>
        </c:forEach>
    </table>
<%@ include file="/form-footer.jsp" %>

