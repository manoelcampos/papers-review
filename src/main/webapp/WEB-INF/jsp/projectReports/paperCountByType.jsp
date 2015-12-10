<%@ include file="/header.jsp" %>

<table class="table-striped table-bordered">
    <caption>Number of Papers by Type</caption>
    <thead>
        <tr>
            <th>Type</th>
            <th>Survey</th>
            <th>Number of Papers</th>
        </tr>
    </thead>
    <tbody>
        <c:set var="total" value="0" />
        <c:set var="surveys" value="0" />
        <c:set var="nonSurveys" value="0" />
        <c:if test="${not empty list}">
            <c:set var="prior" value="${list[0]}" />
            <c:set var="typeTotal" value="0" />
            <c:forEach items="${list}" var="i">
                <c:if test="${i.survey == 1}">
                    <c:set var="surveys" value="${surveys + i.count}" />
                </c:if>
                <c:if test="${i.survey == 0}">
                    <c:set var="nonSurveys" value="${nonSurveys + i.count}" />
                </c:if>
                <c:if test="${prior.paperType != i.paperType}">
                    <tr>
                        <td>Total of ${prior.paperType} Papers</td>
                        <td></td>
                        <td>${typeTotal}</td>
                    </tr>

                    <c:set var="typeTotal" value="0" />
                </c:if>

                <c:set var="typeTotal" value="${typeTotal + i.count}" />
                <tr>
                    <td>${i.paperType}</td>
                    <td>${i.surveyStr}</td>
                    <td>${i.count}</td>
                </tr>
                <c:set var="total" value="${total + i.count}" />

                <c:set var="prior" value="${i}" />
            </c:forEach>
            <tr>
                <td>Total of ${prior.paperType} Papers</td>
                <td></td>
                <td>${typeTotal}</td>
            </tr>
        </c:if>
    </tbody>
    <tfoot>
        <tr>
            <td>Total of Surveys</td>
            <td></td>
            <td>${surveys}</td>
        </tr>
        <tr>
            <td>Total of non-Surveys</td>
            <td></td>
            <td>${nonSurveys}</td>
        </tr>
        <c:if test="${total - (surveys + nonSurveys) > 0}">
            <tr>
                <td>Total of papers without survey classification</td>
                <td></td>
                <td>${total - (surveys + nonSurveys)}</td>
            </tr>
        </c:if>
        <tr>
            <td>Total of Papers</td>
            <td></td>
            <td>${total}</td>
        </tr>
    </tfoot>
</table>



<hr/>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>


<%@ include file="/footer.jsp" %>