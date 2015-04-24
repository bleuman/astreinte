'use strict';

angular.module('mytodoApp').factory('ChangePassWordService', [
		'$http',
		'$rootScope',
		function($http, $rootScope) {
			

			return {

				change : function(data, success, error) {
					$http.post(baseUrl + 'rest/login/change', data).success(success)
							.error(error);

				}
			};
		} ]);