<%@ include file="/form-header.jsp" %>
    <form method="post" enctype="multipart/form-data" action="${linkTo[SearchSectionController].saveImportedPapers}">
        <input type="hidden" name="o.id" value="${o.id}"/>
        BibTeX file <input type="file" name="bibTexFile" />
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[SearchSectionController].index}?projectId=${o.project.id}" class="btn btn-default">Back</a>
        <a href="${linkTo[ProjectController].view}?id=${o.project.id}" class="btn btn-default">Go to Project</a>
    </form>
        
    <table class="table-striped">
        <tr><th>#</th><th>Title</th><th>Search Section</th><th>Year</th><th>Citation Key</th></tr>
        <c:forEach items="${list}" var="p" varStatus="status">
            <tr>
                <td>${status.index+1}</td><td>${p.title}</td><td>${p.searchSection.id}</td>
                <td>${p.publicationYear}</td><td>${p.citationKey}</td>
            </tr>
        </c:forEach>
    </table>
<%@ include file="/form-footer.jsp" %>

