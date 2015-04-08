var LoginController=function($scope, $rootScope,
		AuthService) {
	$rootScope.token = window.sessionStorage.getItem('token');
	$scope.signin = function() {
		var formData = {
			login : $scope.email,
			password : $scope.password
		};

		AuthService.signin(formData, function(res) {
			if (res.statut == false) {
				alert(res.motif);
			} else {
				window.sessionStorage.setItem('token', res.token);
				window.location = "/#/astreinte";
			}
		}, function() {
			$rootScope.error = 'Failed to signin';
		});
	};

	$scope.logout = function() {
		AuthService.logout(function() {
			window.location = "/";
		}, function() {
			alert("Failed to logout!");
		});

	};

};

LoginController.$inject = [ '$scope', '$rootScope', 'AuthService' ];
