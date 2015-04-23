'use strict';

angular.module('mytodoApp').factory('AstreinteService', [
		'$http',
		'$rootScope',
		function($http, $rootScope) {
			return {

				create : function(data, success, error) {
					$http.post(baseUrl+'rest/astreinte/', data).success(
							success).error(error);
				},
				list : function(data, success, error) {
					$http.get(baseUrl+'rest/astreinte/', data).success(
							success).error(error);
				},
				update : function(data, success, error) {
					$http.put(baseUrl+'rest/astreinte/', data).success(
							success).error(error);
				},
				remove : function(data, success, error) {
					$http.delete(baseUrl+'rest/astreinte/'+data.id, data).success(
							success).error(error);
				},
				listRessources:function(data, success, error) {
					$http.get(baseUrl+'rest/ressource/',data).success(
							success).error(error);
				},
				check:function(data, success, error) {
					$http.post(baseUrl+'rest/astreinte/check/',data).success(
							success).error(error);
				},
				ongoing:function(data, success, error) {
					$http.post(baseUrl+'rest/astreinte/statutongoing/',data).success(
							success).error(error);
				},
				rejected:function(data, success, error) {
					$http.post(baseUrl+'rest/astreinte/statutrejected/',data).success(
							success).error(error);
				},
				valide:function(data, success, error) {
					$http.post(baseUrl+'rest/astreinte/statutvalide/',data).success(
							success).error(error);
				},
				tickets:function(data, success, error) {
					$http.get(baseUrl+'rest/ticket/',data).success(
							success).error(error);
				},
				typeAstreintes:function(data, success, error) {
					$http.get(baseUrl+'rest/typeAstreinte/',data).success(
							success).error(error);
				},
				byressource:function(data, success, error) {
					$http.post(baseUrl+'rest/astreinte/byressource/',data).success(
							success).error(error);
				}
				//typeAstreintes
			};
		} ]);
