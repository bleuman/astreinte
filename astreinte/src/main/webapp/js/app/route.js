$app.config(function($routeProvider, $httpProvider) {
	
	$routeProvider
    .when("/astreinte",
    {
      templateUrl: "view/astreinte.html",
      controller: AstreinteController
      
    })
    .when("/login",
    {
    	templateUrl: "view/login.html",
      controller : LoginController
    })
    .when("/ticket",
    {
    	templateUrl: "view/ticket.html",
      controller : TicketController
    })
    .when("/manager",
    {
    	templateUrl: "view/manager.html",
      controller : ManagerController
    })
     .when("/ressource",
    {
    	templateUrl: "view/ressource.html",
      controller : RessourceController
    })
    .otherwise({
      template: "This doesn't exist!"
    });
    
    
	
	$httpProvider.responseInterceptors.push(interceptor);

});