<%@ include file="/header.jsp" %>

<table class="table-striped table-bordered">
    <caption>Number of Papers by Repository</caption>
    <thead>
        <tr>
            <th>Repository</th>
            <th>Number of Papers</th>
        </tr>
    </thead>
    <tbody>
        <c:set var="total" value="0" />
        <c:forEach items="${list}" var="i">
            <tr>
                <td>${i.repository}</td>
                <td>${i.count}</td>
            </tr>
            <c:set var="total" value="${total + i.count}" />
        </c:forEach>
    </tbody>
    <tfoot>
        <tr>
            <td>Total of Papers</td>
            <td>${total}</td>
        </tr>
    </tfoot>
</table>



<hr/>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>


<%@ include file="/footer.jsp" %>