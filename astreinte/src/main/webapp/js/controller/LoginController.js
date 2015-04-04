var LoginController = function($location,$rootScope,$scope, $http, $log) {

	$scope.ressource = [];
	$scope.ressource.login = "test";
	$scope.ressource.password = "test";
	$rootScope.connected = false;
	$scope.checklogin = function(ologin, opassword) {

		// Simple POST request example (passing data) :
		$http.post('/rest/ressource/checklogin', {
			'login' : ologin,
			'password' : opassword
		}).success(function(data, status, headers, config) {
			$rootScope.connected = data;
			
			
				
		}).error(function(data, status, headers, config) {
			$rootScope.connected = false;
			$location.url("manager");
		});
	};

};
// isso aqui pe dependency injection
ManagerController.$inject = [ '$location','$rootScope','$scope', '$http', '$log' ];
