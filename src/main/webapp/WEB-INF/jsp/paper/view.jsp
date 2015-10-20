<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/header.jsp" %>
    <h2><a href="${linkTo[PaperController].edit}?id=${o.id}">Paper: ${o.title}</a></h2>
    Authors: ${o.authors}<br/>
    Year: ${o.publicationYear} &nbsp;&nbsp; DOI: ${o.doi} &nbsp;&nbsp; URL: ${o.url}<br/>
    Type:${o.paperType.description} &nbsp;&nbsp; Citation Key: ${o.citationKey}<br/>
    Survey: ${o.survey} &nbsp;&nbsp; Search Section:  ${o.searchSection.id}

    <hr/>
    
    <table class="table-striped">
        <thead>
          <tr>
            <th>Field</th>
            <th>Answers</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>     
            <c:forEach items="${o.paperFieldAnswers}" var="a">
                <tr>
                    <td>${a.field.description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${a.fieldOption != null && a.fieldOption.id > 0}">
                                ${a.fieldOption.description}
                            </c:when>
                            <c:otherwise>
                                ${a.subjectiveAnswer}
                            </c:otherwise>                            
                        </c:choose>
                    </td>
                    <td><a href="${linkTo[PaperController].removeAnswer}?paperFieldAnswerId=${a.id}" onclick="return window.confirm('Are you sure you want to remove the answer ${a.getAnswer()}?')">Remove</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>    
    
    <hr/>
    <a href="${linkTo[PaperController].answers}?paperId=${o.id}" class="btn btn-primary">Edit Fields' Answers</a>
    <a href="${linkTo[ProjectController].view}?id=${o.searchSection.project.id}" class="btn btn-default">Back</a>
<%@ include file="/footer.jsp" %>