var TicketController = function($scope, $http, $log, TicketService) {

	$scope.recherche = "";
	$scope.ticket = new TicketService();
	$scope.listTicket = TicketService.list();
	$scope.resultats = {};

	$http.get('rest/manager/').success(function(data) {
		$scope.mangersList = data;
	});

	$scope.reset = function() {
		$scope.ticket = new TicketService();
	};

	$scope.rechercherTicket = function(mquery) {
		$http.post('rest/ticket/recherche/', {
			query : mquery
		}).success(function(data) {
			$scope.resultats = data;
		});

	};

	$scope.save = function() {

		if ($scope.ticket.id > 0) {
			$scope.update();
		} else {

			$scope.ticket.$create(function() {
				$scope.listTicket = TicketService.list();
				$scope.rechercherTicket($scope.ticket.qc);
				$scope.reset();
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

	$scope.calculate = function(listastrinte) {
		var tmp = 0;
		for (var i = 0, size = listastrinte.length; i < size; i++) {
			var item = listastrinte[i];
			tmp += item.charge;
		}
		return tmp;
	};

};

TicketController.$inject = [ '$scope', '$http', '$log', 'TicketService' ];
