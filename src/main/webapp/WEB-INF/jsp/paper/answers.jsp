<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/form-header.jsp" %>    
    <form method="post" action="${linkTo[PaperController].saveAnswers}">
        <input type="hidden" name="paperId" value="${paper.id}" />
        <h2>Paper: ${paper.title}</h2>
        <table>
            <th>Field</th><th>Answers</th>
            <c:forEach items="${answersMap}" var="map" varStatus="i">
                <tr>
                    <td>${map.key.description}</td>
                    <td>
                        <input type="hidden" name="answers[${i.index}].field.id" value="${map.key.id}" />
                        <c:if test="${map.key.fieldType.abbreviation == 'S'}">
                            <input type="text" name="answers[${i.index}].subjectiveAnswer" size="40" 
                                value="<c:if test="${fn:length(map.value)>0}">${map.value[0].subjectiveAnswer}</c:if>"
                            />
                        </c:if>
                        <c:if test="${map.key.fieldType.abbreviation != 'S'}">
                            <select name="answers[${i.index}].fieldOptions" <c:if test="${map.key.fieldType.abbreviation == 'M'}">multiple="true"</c:if>>
                                <c:forEach items="${map.key.fieldOptions}" var="fo" varStatus="j">
                                    <option value="${fo.id}"
                                        <c:forEach items="${map.value}" var="a">
                                            <c:if test="${a.fieldOption.id == fo.id}">selected</c:if>
                                        </c:forEach>
                                    >
                                        ${fo.description}
                                    </option>
                                </c:forEach>
                            </select>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <hr/>
        
        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[PaperController].view}?id=${paper.id}" class="btn btn-default">Back</a>
        <a href="${linkTo[ProjectController].view}?id=${paper.searchSection.project.id}" class="btn btn-default">Go to Project</a>
    </form>
<%@ include file="/form-footer.jsp" %>

