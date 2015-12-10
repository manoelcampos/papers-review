<%@ include file="/header.jsp" %>

<table class="table-striped table-bordered">
    <caption>Number of Papers by Status</caption>
    <thead>
        <tr>
            <th>Status</th>
            <th>Number of Papers</th>
        </tr>
    </thead>
    <tbody>
        <c:set var="total" value="0" />
        <c:set var="accepted" value="0" />
        <c:set var="rejected" value="0" />
        <c:forEach items="${list}" var="i">
            <c:if test="${i.accepted == 1}">
                <c:set var="accepted" value="${accepted + i.count}" />
            </c:if>
            <c:if test="${i.accepted == 0}">
                <c:set var="rejected" value="${rejected + i.count}" />
            </c:if>

            <tr>
                <td>${i.status}</td>
                <td>${i.count}</td>
            </tr>
            <c:set var="total" value="${total + i.count}" />
        </c:forEach>
    </tbody>
    <tfoot>
        <tr><td>Sub-total of Accepted</td><td>${accepted}</td></tr>
        <tr><td>Sub-total of Rejected</td><td>${rejected}</td></tr>
        <c:if test="${total - (accepted+rejected) > 0}">
            <tr><td>Sub-total of not Classified Papers</td><td>${total - (accepted+rejected)}</td></tr>
        </c:if>

        <tr>
            <td>Total of Papers</td>
            <td>${total}</td>
        </tr>
    </tfoot>
</table>

<hr/>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>


<%@ include file="/footer.jsp" %>