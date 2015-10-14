<!DOCTYPE html>

<html>
    <head>
        <title>Repository</title>
        <meta name="viewport" content="width=device-width">
        
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>        
        
        <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
    </head>
    <body>
        <h1>Repositories</h1>
        <div ng-app="app" ng-controller="ctrl" class="container">
            <table class="table-striped">
                <thead>
                  <tr>
                    <th>Number</th>
                    <th>Description</th>
                  </tr>
                </thead>
                <tbody>                
                    <tr ng-repeat="o in list">
                      <td>{{ o.id }}</td>
                      <td>{{ o.description }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>

<script>
var app = angular.module('app', []);

app.controller("ctrl", function($scope, $http){
	$http.get('http://localhost:8080/PapersReview-0.1/repository/index').success(function(response){
		$scope.list = response.list; 
	});
});
</script>