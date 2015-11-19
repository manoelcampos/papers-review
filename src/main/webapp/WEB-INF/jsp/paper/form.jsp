<%@ include file="/form-header.jsp" %>
    <form method="post" action="${linkTo[PaperController].save}">
        <input type="hidden" name="paper.id" value="${paper.id}"/>
        <input type="hidden" name="paper.searchSession.id" value="${paper.searchSession.id}"/>
        
        Title <input type="text" name="paper.title" value="${paper.title}" size="120"/><br/>
        Authors <input type="text" name="paper.authors" value="${paper.authors}" size="100" maxlength="400"/><br/>
        Publication Year <input type="text" name="paper.publicationYear"  size="6"  maxlength="6" value="${paper.publicationYear}"/>
        DOI <input type="text" name="paper.doi" size="50" maxlength="50" value="${paper.doi}"/><br/>
        URL <input type="text" name="paper.url" size="70" maxlength="70" value="${paper.url}"/><br/>

        Type
        <select name="paper.paperType.id">
            <option value="0">Not Defined</option>
            <c:forEach items="${paperTypes}" var="t">
                <option value="${t.id}" <c:if test="${paper.paperType.id == t.id}">selected</c:if> >${t.description}</option>
            </c:forEach>
        </select>

        Citation Key <input type="text" name="paper.citationKey"  size="30" value="${paper.citationKey}"/>
        <br/>
        
        Survey 
        <select name="paper.survey" id="survey">
            <option value="-1">Not Defined</option>
            <option value="1" <c:if test="${paper.survey==1}">selected</c:if>>Yes</option>
            <option value="0" <c:if test="${paper.survey==0}">selected</c:if>>No</option>
        </select>
        <br/>
        
        Accepted on Selection Phase: 
        <select name="paper.acceptedOnSelectionPhase" id="acceptedOnSelectionPhase">
            <option value="-1">Not Defined</option>
            <option value="1" <c:if test="${paper.acceptedOnSelectionPhase==1}">selected</c:if>>Yes</option>
            <option value="0" <c:if test="${paper.acceptedOnSelectionPhase==0}">selected</c:if>>No</option>
        </select>
        
        Accepted on Extraction Phase: 
        <select name="paper.acceptedOnExtractionPhase" id="acceptedOnExtractionPhase">
            <option value="-1">Not Defined</option>
            <option value="1" <c:if test="${paper.acceptedOnExtractionPhase==1}">selected</c:if>>Yes</option>
            <option value="0" <c:if test="${paper.acceptedOnExtractionPhase==0}">selected</c:if>>No</option>
        </select>
        <br/>
        Abstract:
        <textarea name="paper.paperAbstract" rows="15" style="width: 100%">${paper.paperAbstract}</textarea>
        
        <hr/>

        <input type="submit" value="Save" class="btn btn-primary"/>
        <a href="${linkTo[PaperController].view(paper)}" class="btn btn-default">Back</a>
        <a href="${linkTo[ProjectController].view(paper.searchSession.project)}" class="btn btn-default">Go to Project</a>
    </form>
<%@ include file="/form-footer.jsp" %>

<script type="text/javascript">
    $("document").ready(function(){
       $("#acceptedOnSelectionPhase").change(function(){
            disableFields();
       });
       
       disableFields();
    });
    
    function disableFields(){
           var selected = $("#acceptedOnSelectionPhase").val();
           var disable = selected!=="1";
           $("#acceptedOnExtractionPhase").prop('disabled', disable);
           if(disable){
               $("#acceptedOnExtractionPhase").val(selected);
           }        
    }
</script>