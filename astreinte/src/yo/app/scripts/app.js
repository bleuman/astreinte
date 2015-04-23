'use strict';

/**
 * @ngdoc overview
 * @name mytodoApp
 * @description # mytodoApp
 * 
 * Main module of the application.
 */
var baseUrl = "";
angular.module(
		'mytodoApp',
		[ 'ngAnimate', 'ngCookies', 'ngResource', 'ngRoute', 'ngSanitize',
				'ngTouch', 'ui.sortable', 'LocalStorageModule' ]).config(
		[ 'localStorageServiceProvider', function(localStorageServiceProvider) {
			localStorageServiceProvider.setPrefix('ls');
		} ]).config(
		function($routeProvider, $httpProvider) {
			$routeProvider.when('/about', {
				templateUrl : 'views/about.html',
				controller : 'AboutCtrl'
			}).when('/', {
				templateUrl : 'views/login.html',
				controller : 'LoginCtrl'
			}).when('/astreinte', {
				templateUrl : 'views/astreinte.html',
				controller : 'AstreinteCtrl'
			}).when('/ticket', {
				templateUrl : 'views/ticket.html',
				controller : 'TicketCtrl'
			}).when('/ressource', {
				templateUrl : 'views/ressource.html',
				controller : 'RessourceCtrl'
			}).otherwise({
				template : "This doesn't exist!"
			});
			$httpProvider.interceptors.push([
					'$q',
					'$location',
					'$rootScope',
					function($q, $location, $rootScope) {
						return {
							'request' : function(config) {
								config.headers = config.headers || {};
								config.headers.Authorization = 'Bearer '
										+ window.sessionStorage
												.getItem("token");
								return config;
							},
							'responseError' : function(response) {
								if (response.status === 401
										|| response.status === 403) {
									$location.path('/');
								}
								if (response.status === 404
										|| response.status === 412) {
									$rootScope.error = response.data.errorMsg;
									$location.path('/');
								}

								return $q.reject(response);
							},
							'response' : function(response) {
								if (!window.sessionStorage.getItem("token"))
									$location.path('/');
								$rootScope.currentUser = getUserFromToken();
								return response;
							}

						};
					} ]);
		});
