<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/header.jsp" %>
    <h2>Paper: ${paper.title}</h2>
    Year: ${paper.publicationYear} &nbsp;&nbsp; Authors: ${paper.authors}<br/>
    
    <c:if test="${not empty paper.doi}">
        DOI: <a href="${paper.getDoiLink()}">${paper.doi}</a> <br/> 
    </c:if>
    <c:if test="${not empty paper.url}">
        URL: <a href="${paper.url}">${paper.url}</a> <br/> 
    </c:if>

    Type: ${paper.paperType.description} &nbsp;&nbsp; Search Session: ${paper.searchSession.id} &nbsp;&nbsp;
    Citation Key: ${paper.citationKey}<br/>
    Survey: ${paper.survey} &nbsp;&nbsp; 
    Status: ${paper.status}

    <c:if test="${not empty paper.paperAbstract}">
    <br/> 
    Abstract:<br/>
    <div style="width: 100%; border: #cccccc 1px;">${paper.paperAbstract}</div>
    <br/> 
    </c:if>

    <c:if test="${not empty paper.notes}">
    Notes:<br/>
    <div style="width: 100%; border: #cccccc 1px;">${paper.notes}</div>
    <br/> 
    </c:if>
    
    <a href="${linkTo[PaperController].edit(paper)}">Edit Paper</a>
    | <a href="${linkTo[PaperController].remove(paper)}"  onclick="return window.confirm('Are you sure you want to remove the paper ${paper.title}?')">Remove Paper</a>
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
            <c:forEach items="${paper.paperFieldAnswers}" var="a">
                <tr>
                    <td><a href="${linkTo[FieldController].view(a.field)}" title="Click to view the field definition">
                            ${a.field.description}
                        </a>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${a.fieldOption != null && a.fieldOption.id > 0}">
                                <a href="${linkTo[FieldOptionController].edit(a.fieldOption)}" title="Click to edit the field option">
                                    ${a.fieldOption.description}
                                </a>
                            </c:when>
                            <c:otherwise>
                                ${a.subjectiveAnswer}
                            </c:otherwise>                            
                        </c:choose>
                    </td>
                    <td><a href="${linkTo[PaperController].removeAnswer(a)}" onclick="return window.confirm('Are you sure you want to remove the answer ${a.getAnswer()}?')">Remove</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>    
    
    <hr/>
    <a href="${linkTo[PaperController].answers(paper)}" class="btn btn-primary">Edit Fields' Answers</a>
    <a href="${linkTo[ProjectController].view(paper.searchSession.project)}" class="btn btn-default">Back</a>
<%@ include file="/footer.jsp" %>