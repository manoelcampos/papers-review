<%@ include file="/header.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

${gen.openTable()}
    ${gen.caption("Papers Summary Table")}
    ${gen.openHead()}
        ${gen.openRow()}
            ${gen.columnHeader("#")}
            ${gen.columnHeader("Paper")}
            ${gen.columnHeader("Type")}
            <c:forEach items="${fieldList}" var="f">
                ${gen.columnHeader(f.abbreviation)}
            </c:forEach>
        ${gen.closeRow()}
    ${gen.closeHead()}
    ${gen.openBody()}
        <c:forEach items="${paperList}" var="p" varStatus="status">
            ${gen.openRow()}
                ${gen.column(status.index+1)}
                ${gen.column(p.title)}
                ${gen.column(p.paperType.abbreviation)}
                <c:forEach items="${fieldList}" var="f">
                    ${gen.column(p.getPaperFieldAbbreviatedAnswers(f))}
                </c:forEach>
            ${gen.closeRow()}
        </c:forEach>
    ${gen.closeBody()}
${gen.closeTable()}

<hr/>

<a href="${linkTo[ProjectReportsController].index(project)}" class="btn btn-primary">Back</a>


<%@ include file="/footer.jsp" %>