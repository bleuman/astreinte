'use strict';

angular.module('mytodoApp').controller(
		'ChangePassWordCtrl',
		function($scope, $rootScope, ChangePassWordService) {
			$rootScope.token = (window.sessionStorage.getItem('token') + "")
					.split(" ")[0];
			$scope.change = function() {
				$scope.error = null;
				var user = getUserFromToken();
				var formData = {
					login : user.principale.login,
					epassword : $scope.epassword,
					npassword : $scope.npassword,
					cpassword : $scope.cpassword
				};

				ChangePassWordService.change(formData,

				function(data) {
					console.info("Change password OK : " + data);
					if (!!data.errorMsg)
						$scope.error = data.errorMsg;
				}, function(res) {
					console.error("Change password error : " + res);
					if (!!res.errorMsg)
						$scope.error = res.errorMsg;

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
