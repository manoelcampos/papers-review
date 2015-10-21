<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/header.jsp" %>
    <h2>Paper: ${o.title}</h2>
    Year: ${o.publicationYear} &nbsp;&nbsp; Authors: ${o.authors}<br/>
    
    <c:if test="${not empty o.doi}">
        DOI: <a href="${o.getDoiLink()}">${o.doi}</a> <br/> 
    </c:if>
    <c:if test="${not empty o.url}">
        URL: <a href="${o.url}">${o.url}</a> <br/> 
    </c:if>

    Type: ${o.paperType.description} &nbsp;&nbsp; Citation Key: ${o.citationKey} &nbsp;&nbsp;
    Search Section:  ${o.searchSection.id}<br/>
    Survey: ${o.survey} &nbsp;&nbsp; 
    Accepted on Selection Phase: ${o.acceptedOnSelectionPhase}&nbsp;&nbsp;
    Accepted on Extraction Phase: ${o.acceptedOnExtractionPhase}<br/> 
    
    <a href="${linkTo[PaperController].edit}?id=${o.id}">Edit Paper</a>
    | <a href="${linkTo[PaperController].remove}?id=${o.id}"  onclick="return window.confirm('Are you sure you want to remove the paper ${o.title}?')">Remove Paper</a>
    <hr/>
    
    <table class="table-striped table-bordered">
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