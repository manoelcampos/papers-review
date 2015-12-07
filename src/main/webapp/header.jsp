<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Paper Review</title>
    <meta name="viewport" content="width=device-width">

    <script src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>        

</head>
<body>
    <nav class="navbar navbar-default" role="navigation">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Papers Review Beta</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <!--<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>-->
            <li><a href="${pageContext.request.contextPath}">Home</a></li>
            <li><a href="${linkTo[ProjectController].index}">Project</a></li>
            <li><a href="${linkTo[RepositoryController].index}">Repositories</a></li>
            <li><a href="${linkTo[PaperController].search}">Search Paper</a></li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
                    
    <div id="conteudo" class="container-fluid">
        <fmt:setBundle basename="messages" />
        <c:if test="${!empty msg}">
            <div id="info-alert" class="alert alert-info alert-dismissible fade in" role="alert">
                <button type="button" class="close" data-dismiss="alert">x</button>
                <c:catch>
                    <p class="message"><fmt:message key="${msg}"/></p>
                </c:catch>
            </div>
        </c:if>
        
        <c:if test="${!empty errors}">
            <div id="error-alert" class="alert alert-danger alert-dismissible fade in" role="alert">
                <button type="button" class="close" data-dismiss="alert">x</button>
                <div class="error">
                    <ul>
                        <c:forEach items="${errors}" var="e">
                            <li>${e.category} ${e.message}</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </c:if>
            