<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[PaperController].save}">
        <input type="hidden" name="o.id" value="${o.id}"/>
        <input type="hidden" name="o.searchSection.id" value="${o.searchSection.id}"/>
        
        Title <input type="text" name="o.title" value="${o.title}"/>
        Authors <input type="text" name="o.authors" value="${o.authors}"/>
        Publication Year <input type="text" name="o.publicationYear" value="${o.publicationYear}"/>
        DOI <input type="text" name="o.doi" value="${o.doi}"/>
        URL <input type="text" name="o.url" value="${o.url}"/>

        Type
        <select name="o.paperType.id">
            <c:forEach items="${paperTypes}" var="t">
                <option value="${t.id}" <c:if test="${o.paperType.id == t.id}">selected</c:if> >${t.description}</option>
            </c:forEach>
        </select>

        Citation Key <input type="text" name="o.citationKey" value="${o.citationKey}"/>
        <br/>
        
        Survey <input type="checkbox" name="o.survey" <c:if test="${o.survey==true}">checked="true"</c:if> /><br/>
        
        Accepted on Selection Phase: 
        <input type="checkbox" name="o.acceptedOnSelectionPhase" id="acceptedOnSelectionPhase"  <c:if test="${o.acceptedOnSelectionPhase==true}">checked="true"</c:if> /><br/>
        
        Accepted on Extraction Phase: 
        <input type="checkbox" name="o.acceptedOnExtractionPhase" id="acceptedOnExtractionPhase" <c:if test="${o.acceptedOnExtractionPhase==true}">checked="true"</c:if> />
        
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[PaperController].view}?id=${o.id}" class="btn btn-default">Back</a>
        <a href="${linkTo[ProjectController].view}?id=${o.searchSection.project.id}" class="btn btn-default">Go to Project</a>
    </form>
<%@ include file="/form-footer.jsp" %>

<script type="text/javascript">
    $("document").ready(function(){
       $("#acceptedOnSelectionPhase").change(function(){
           var disable = !this.checked;
           $("#acceptedOnExtractionPhase").prop('disabled', disable);
           if(disable){
               $("#acceptedOnExtractionPhase").prop('checked', false);
           }
       });
    });
</script>