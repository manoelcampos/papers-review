<%@ include file="/header.jsp" %>

<h3>Project Description: ${o.description} - User: ${o.endUser.name}</h3>

<c:forEach items="${repositoriesMap}" var="r">
<div id='jqxTree'>
    <ul>
        <li>
            ${r.key.description} Repository
            <ul>
                <c:forEach items="${r.value}" var="s">
                <li>
                    Search Section ${s.key.id}
                    <ul>
                        <table class="table-striped">
                            <tr>
                                <th>Paper Title</th><th>Type</th><th>Authors</th>
                                <th>Year</th><th>DOI</th><th>Citation Key</th>
                            </tr>
                            <c:forEach items="${s.value}" var="p">
                            <tr>
                                <th>${p.title}</th><th>${p.paperType}</th><th>${p.authors}</th>
                                <th>${p.publicationYear}</th><th>${p.doi}</th><th>${p.citationKey}</th>
                            </tr>
                            </c:forEach>
                        </table>
                    </ul>
                </li>
                </c:forEach>
            </ul>
        </li>
</div>
</c:forEach>


<a href="${linkTo[ProjectController].fields}?id=${o.id}" class="btn btn-primary">View Fields</a>
<a href="${linkTo[SearchSectionController].form}?projectId=${o.id}" class="btn btn-primary">New Search Section</a>
<a href="${linkTo[ProjectController].index}" class="btn btn-default">Back</a>

<%@ include file="/footer.jsp" %>