//'use strict';

$app.factory('AuthService', [
		'$http',
		'$rootScope',
		function($http, $rootScope) {
			var baseUrl = "rest/ressource/";

			function urlBase64Decode(str) {
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

			function getUserFromToken() {
				var token = $rootScope.token;
				var user = {};
				if (!!token) {
					var encoded = token.split('.')[1];
					user = JSON.parse(urlBase64Decode(encoded));
				}
				return user;
			}

			var currentUser = getUserFromToken();
			function changeUser(user) {
				angular.extend(currentUser, user);
			}

			return {

				signin : function(data, success, error) {
					$http.post(baseUrl + 'authenticate', data).success(success)
							.error(error);
				},

				logout : function() {
					$http.get(baseUrl + 'logout');
					changeUser({});
					$rootScope.token = null;
					window.sessionStorage.removeItem('token');
					window.location="/";
					
				}
			};
		} ]);