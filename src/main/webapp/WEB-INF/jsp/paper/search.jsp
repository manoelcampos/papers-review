<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[PaperController].doSearch}">
        Title <input type="text" name="paper.title" value="${paper.title}" size="120"/><br/>
        Authors <input type="text" name="paper.authors" value="${paper.authors}" size="100" maxlength="400"/><br/>
        Publication Year 
        <input type="text" name="paper.publicationYear"  size="6" maxlength="6" 
               <c:if test="${paper.publicationYear > 0}">value="${paper.publicationYear}"</c:if> />
        DOI <input type="text" name="paper.doi" size="50" maxlength="50" value="${paper.doi}"/><br/>

        Paper Type
        <select name="paper.paperType.id">
            <option value="0">All</option>
            <c:forEach items="${paperTypes}" var="t">
                <option value="${t.id}" <c:if test="${paper.paperType.id == t.id}">selected</c:if> >${t.description}</option>
            </c:forEach>
        </select>

        Project
        <select name="paper.searchSession.project.id">
            <option value="0">All</option>
            <c:forEach items="${projects}" var="p">
                <option value="${p.id}" <c:if test="${project.id == p.id}">selected</c:if> >${p.description}</option>
            </c:forEach>
        </select>

        Repository
        <select name="paper.searchSession.repository.id">
            <option value="0">All</option>
            <c:forEach items="${repositories}" var="r">
                <option value="${r.id}" <c:if test="${paper.searchSession.repository.id == r.id}">selected</c:if> >${r.description}</option>
            </c:forEach>
        </select>

        Citation Key <input type="text" name="paper.citationKey"  size="30" value="${paper.citationKey}"/>
        <br/>
        
        Survey 
        <select name="paper.survey" id="survey">
            <option value="-1">All</option>
            <option value="1" <c:if test="${paper.survey==1}">selected</c:if>>Yes</option>
            <option value="0" <c:if test="${paper.survey==0}">selected</c:if>>No</option>
        </select>
        
        Status: 
        <select name="paper.status.id" id="status">
            <option value="0">Not Defined</option>
            <c:forEach items="${status}" var="s">
                <option value="${s.id}" <c:if test="${paper.status.id == s.id}">selected</c:if> >${s}</option>
            </c:forEach>
        </select>

        
        <hr/>

        <input type="submit" value="Search" class="btn btn-primary"/>
        <c:choose>
            <c:when test="${not empty project}">
                <a href="${linkTo[ProjectController].view(project)}" class="btn btn-default">Back</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}" class="btn btn-default">Back</a>
            </c:otherwise>
        </c:choose>
    </form>
                
    <h3>${fn:length(list)} papers found</h3>
    <c:if test="${not empty list}">
        <table class="table-striped table-bordered">
        <thead>
          <tr>
            <th>Title</th>
            <th>Year</th>
            <th>Citation Key</th>
            <th>Authors</th>
            <th>Repository</th>
            <th>Project</th>
          </tr>
        </thead>
        <tbody>     
            <c:forEach items="${list}" var="p">
                <tr>
                  <td><a href="${linkTo[PaperController].view(p)}">${p.title}</a></td>
                  <td>${p.publicationYear}</td>
                  <td>${p.citationKey}</td>
                  <td>${p.authors}</td>
                  <td>${p.searchSession.repository.description}</td>
                  <td>${p.searchSession.project.description}</td>
                </tr>
          </c:forEach>
        </tbody>                 
        </table>
    </c:if>
                
<%@ include file="/form-footer.jsp" %>

