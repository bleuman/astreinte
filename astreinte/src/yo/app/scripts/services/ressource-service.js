'use strict';

angular.module('mytodoApp').factory('RessourceService',
		[ '$http', '$rootScope', function($http, $rootScope) {
			return {
				create : function(data, success, error) {
					$http.post(baseUrl+'rest/ressource/', data).success(
							success).error(error);
				},
				list : function(data, success, error) {
					$http.get(baseUrl+'rest/ressource/', data).success(
							success).error(error);
				},
				update : function(data, success, error) {
					$http.put(baseUrl+'rest/ressource/', data).success(
							success).error(error);
				},
				remove : function(data, success, error) {
					$http.delete(baseUrl+'rest/ressource/'+data.id, data).success(
							success).error(error);
				}

			};
		} ]);