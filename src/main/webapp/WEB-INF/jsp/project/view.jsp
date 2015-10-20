<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/header.jsp" %>
<link rel="stylesheet" type="text/css" href="http://jqwidgets.com/public/jqwidgets/styles/jqx.base.css">
<link rel="stylesheet" type="text/css" href="http://jqwidgets.com/public/jqwidgets/styles/jqx.energyblue.css">


<h3>Project Description: ${o.description} - User: ${o.endUser.name}</h3>
<div id='jqxTree'>
    <ul>
        <c:forEach items="${repositoriesMap}" var="r">
        <li item-expanded='true'>
            ${r.key.description} Repository  (${fn:length(r.value)} search section(s))
            <c:if test="${fn:length(r.value) > 0}">
            <ul>
                <c:forEach items="${r.value}" var="s">
                <li item-expanded='false'>
                    Search Section ${s.key.id} (${fn:length(s.value)} paper(s))
                    <c:if test="${fn:length(s.value) > 0}">
                    <ul>
                        <c:forEach items="${s.value}" var="p" varStatus="status">
                        <li>
                            <a href="${linkTo[PaperController].view}?id=${p.id}" alt="Click to View">
                            ${status.index+1} | 
                            ${p.title} | ${p.publicationYear} | ${p.citationKey} |
                            Click to View</a>
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

<a href="${linkTo[ProjectController].fields}?id=${o.id}" class="btn btn-primary">View Fields</a>
<a href="${linkTo[SearchSectionController].index}?projectId=${o.id}" class="btn btn-primary">Search Sections</a>
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