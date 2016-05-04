<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/header.jsp" %>
<link rel="stylesheet" type="text/css" href="http://jqwidgets.com/public/jqwidgets/styles/jqx.base.css">
<link rel="stylesheet" type="text/css" href="http://jqwidgets.com/public/jqwidgets/styles/jqx.energyblue.css">

<h3>Project Description: ${project.description} - User: ${project.endUser.name}</h3>
<div id='jqxTree'>
    <ul>
        <c:forEach items="${repositoriesMap}" var="r">
        <li item-expanded='true'>
            ${r.key.description} Repository (${fn:length(r.value)} search session(s))
            <c:if test="${fn:length(r.value) > 0}">
            <ul>
                <c:forEach items="${r.value}" var="s">
                <li item-expanded='false'>
                    Search Session ${s.key.id} (${fn:length(s.value)} paper(s))
                    <c:if test="${fn:length(s.value) > 0}">
                    <ul>
                        <c:forEach items="${s.value}" var="p" varStatus="status">
                        <li>
                            <span 
                                <c:choose>
                                    <c:when test="${empty p.status}">
                                        style="color: #939393;" <!-- cinza -->
                                    </c:when>
                                    <c:when test="${p.status.acceptedOnExtractionPhase()}">
                                        style="color: #006B24;" <!-- verde escuro -->
                                    </c:when>
                                    <c:when test="${p.status.acceptedOnSelectionPhase()}">
                                        style="color: #4DB870;" <!-- verde claro -->
                                    </c:when>
                                    <c:when test="${p.status.rejectedOnSelectionPhase()}">
                                        style="color: #FF4747;"  <!-- vermelho claro -->
                                    </c:when>
                                    <c:when test="${p.status.rejectedOnExtractionPhase()}">
                                        style="color: #FF0000;" <!-- vermelho escuro -->
                                    </c:when>
                                </c:choose>
                            >
                                <a href="${linkTo[PaperController].view(p)}" alt="Click to View">
                                ${status.index+1} | 
                                ${p.title} | ${p.publicationYear} | 
                                Click to View
                                </a>
                            </span>
                        </li>
                        </c:forEach>
                    </ul>
                    </c:if>
                </li>
                </c:forEach>
            </ul>
            </c:if>
        </li>
        </c:forEach>
    </ul>
</div>

<hr/>

<a href="${linkTo[PaperController].search(project)}" class="btn btn-primary">Search Paper</a>
<a href="${linkTo[ProjectController].fieldGroups(project)}" class="btn btn-primary">Fields Groups</a>
<a href="${linkTo[ProjectController].fields(project)}" class="btn btn-primary">Fields</a>
<a href="${linkTo[SearchSessionController].index(project)}" class="btn btn-primary">Search Sessions</a>
<a href="${linkTo[ProjectController].index}" class="btn btn-default">Back</a>

<%@ include file="/footer.jsp" %>

<script type='text/javascript' src="http://jqwidgets.com/public/jqwidgets/jqx-all.js"></script>
<script type='text/javascript'>//<![CDATA[
$(window).load(function(){
   $('#jqxTree').jqxTree({
       theme: 'energyblue'

   });
   
   $('#checkedItemsBtn').bind('checked', function (event) {
        $('#jqxTree').jqxTree({
            submitCheckedItems: true
        });
   });   
});//]]> 

</script>