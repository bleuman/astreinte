'use strict';

angular.module('mytodoApp').factory('AuthService', [
		'$http',
		'$rootScope',
		function($http, $rootScope) {
			

			return {

				signin : function(data, success, error) {
					$http.post(baseUrl + 'rest/login/authenticate', data).success(success)
							.error(error);

				},

				logout : function() {
					$http.get(baseUrl + 'rest/login/logout');
					changeUser({});
					$rootScope.token = null;
					window.sessionStorage.removeItem('token');
					window.location = "/";

				}
			};
		} ]);