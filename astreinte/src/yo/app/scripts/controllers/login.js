'use strict';

angular.module('mytodoApp').controller(
		'LoginCtrl',
		function($scope, $rootScope, AuthService) {
			$rootScope.token = (window.sessionStorage.getItem('token') + "")
					.split(" ")[0];
			$scope.signin = function() {
				$scope.error = null;
				var formData = {
					login : $scope.email,
					password : $scope.password
				};

				AuthService.signin(formData, function(res) {

					$rootScope.loginData = res;
					window.sessionStorage.setItem('token', res.token);
					$rootScope.error = null;
					window.location = "/#/astreinte";
				}, function(res) {

					if (!!res.errorMsg)
						$scope.error = res.errorMsg;
				});
			};

			$scope.logout = function() {
				AuthService.logout(function() {
					window.location = "/";
				}, function() {
					alert("Failed to logout!");
				});

			};

		});

function urlBase64Decode(str) {
	if (!!str) {
		var output = str.replace('-', '+').replace('_', '/');
		switch (output.length % 4) {
		case 0:
			break;
		case 2:
			output += '==';
			break;
		case 3:
			output += '=';
			break;
		default:
			throw 'Illegal base64url string!';
		}
		return window.atob(output);
	}
}

function getUserFromToken() {
	var storedToken = window.sessionStorage.getItem('token');
	var user = {};
	if (!!storedToken) {
		var encoded = storedToken.split('.')[1];
		user = JSON.parse(urlBase64Decode(encoded));
	}
	return user;
}

function changeUser(user) {
	angular.extend(currentUser, user);
}
