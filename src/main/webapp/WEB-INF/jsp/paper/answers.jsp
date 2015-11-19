<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/form-header.jsp" %>    
    <form method="post" action="${linkTo[PaperController].saveAnswers}">
        <input type="hidden" name="paper.id" value="${paper.id}" />
        <h2>Paper: ${paper.title}</h2>
        <table class="table-striped table-bordered" style="width: 100%; border: 0px">
            <th>Field</th><th>Answers</th>
            <c:forEach items="${answersMap}" var="map" varStatus="i">
                <tr>
                    <td>${map.key.description}</td>
                    <td>
                        <input type="hidden" name="answers[${i.index}].field.id" value="${map.key.id}" />
                        <c:choose>
                            <c:when test="${map.key.fieldType.abbreviation == 'S'}">
                                <input type="text" name="answers[${i.index}].subjectiveAnswer" size="60" 
                                    value="<c:if test="${fn:length(map.value)>0}">${map.value[0].subjectiveAnswer}</c:if>"
                                />
                            </c:when>
                            <c:otherwise>
                                <select name="answers[${i.index}].fieldOptions" 
                                    <c:if test="${map.key.fieldType.abbreviation == 'M'}">
                                        multiple="true"
                                        size="${fn:length(map.key.fieldOptions)+2}"
                                    </c:if>>
                                    <option value="0">None</option>
                                    <c:forEach items="${map.key.fieldOptions}" var="fo" varStatus="j">
                                        <option value="${fpaper.id}"
                                            <c:forEach items="${map.value}" var="a">
                                                <c:if test="${a.fieldOption.id == fo.id}">selected</c:if>
                                            </c:forEach>
                                        >
                                            ${fo.description}
                                        </option>
                                    </c:forEach>
                                </select>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <hr/>
        
        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[PaperController].view(paper)}" class="btn btn-default">Back</a>
        <a href="${linkTo[ProjectController].view(paper.searchSession.project)}" class="btn btn-default">Go to Project</a>
    </form>
<%@ include file="/form-footer.jsp" %>

