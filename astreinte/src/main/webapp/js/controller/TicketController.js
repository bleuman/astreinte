var TicketController = function($scope, $http,$log, TicketService) {

	// $scope.dateTicket=null;
	$scope.ticket = new TicketService();

	// calling restful service
	$scope.listTicket = TicketService.list();
	// $scope.mangersList = ManagerService.list();

	$http.get('rest/manager/').success(function(data){$scope.mangersList =data;});
	// $http.get('rest/ticket/').success(function(data){$scope.listeTickets
	// =data;});

	$scope.reset = function() {
		$scope.ticket = new TicketService();
	};

	// save or update
	$scope.save = function() {

		if ($scope.ticket.id > 0) {
			$scope.update();
		} else {

			// $scope.ticket.hfin="01 01 1900 "+$scope.ticket.hfin +":00 000";
			$scope.ticket.$create(function() {
				// depois que salvamos atualizamos a lista
				$scope.listTicket = TicketService.list();
				// $scope.reset();
			});
		}
	};

	$scope.update = function() {
		$scope.ticket.$update(function() {
			$scope.listTicket = TicketService.list();
			// $scope.reset();
		});
	};

	$scope.edit = function(ticket) {
		$scope.ticket = ticket;
	};

	$scope.remove = function(ticket) {
		ticket.$remove({
			id : ticket.id
		}, function(res) {
			$scope.listTicket = TicketService.list();
		});
	};
	

	$scope.calculateTotalCharge = function(listastrinte) {
		var tmp = 0;
		for (var i = 0, size = listastrinte.length; i < size; i++) {
			var item = listastrinte[i];
			tmp += item.charge;
		}
		return tmp;
	};

};
// isso aqui pe dependency injection
TicketController.$inject = [ '$scope', '$http', '$log', 'TicketService' ];
