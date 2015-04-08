$app
		.config(function($routeProvider, $httpProvider) {

			$routeProvider.when("/astreinte", {
				templateUrl : "view/astreinte.html",
				controller : AstreinteController

			}).when("/", {
				templateUrl : 'view/signin.html',
				controller : LoginController
			}).when("/ticket", {
				templateUrl : "view/ticket.html",
				controller : TicketController
			}).when("/manager", {
				templateUrl : "view/manager.html",
				controller : ManagerController
			}).when("/ressource", {
				templateUrl : "view/ressource.html",
				controller : RessourceController
			}).otherwise({
				template : "This doesn't exist!"
			});

			// $httpProvider.responseInterceptors.push(interceptor);

			$httpProvider.interceptors
					.push([
							'$q',
							'$location',
							'$rootScope',
							function($q, $location, $rootScope) {
								return {
									'request' : function(config) {
										config.headers = config.headers || {};
										config.headers.Authorization = 'Bearer '+window.sessionStorage.getItem("token");
										return config;
									},
									'responseError' : function(response) {
										if (response.status === 401
												|| response.status === 403) {
											$location.path('/');
										}
										return $q.reject(response);
									}
								};
							} ]);

		});
