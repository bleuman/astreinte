'use strict';

angular.module('mytodoApp').factory('TicketService',
		[ '$http', '$rootScope', function($http, $rootScope) {
			return {
				create : function(data, success, error) {
					$http.post(baseUrl+'rest/ticket/', data).success(
							success).error(error);
				},
				list : function(data, success, error) {
					$http.get(baseUrl+'rest/ticket/', data).success(
							success).error(error);
				},
				update : function(data, success, error) {
					$http.put(baseUrl+'rest/ticket/', data).success(
							success).error(error);
				},
				remove : function(data, success, error) {
					$http.delete(baseUrl+'rest/ticket/'+data.id, data).success(
							success).error(error);
				}

			};
		} ]);