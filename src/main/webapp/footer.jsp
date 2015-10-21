        </div><!--conteudo-->
</body>
</html>

<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/util.js"></script>

<script>
    $(document).ready(function() {
        $("#info-alert").fadeTo(2000, 500).slideUp(500, function(){
            $("#info-alert").alert('close');
        });    

        $("#error-alert").fadeTo(2000, 500).slideUp(500, function(){
            $("#error-alert").alert('close');
        });    
    });  
</script>
