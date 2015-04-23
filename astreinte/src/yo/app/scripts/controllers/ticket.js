'use strict';

angular.module('mytodoApp').controller('TicketCtrl',
		function($http, $scope, $log, $rootScope, TicketService) {
		$scope.recherche="%";
			$scope.getTickets = function() {
				TicketService.list({}, function(res) {
					$scope.resultats = res;
				}, function(res) {
					$log.info(res);
				});
			};
			$scope.getTickets();

			$http.get('rest/manager/').success(function(data) {
				$scope.mangersList = data;
			});

			$scope.reset = function() {
				$scope.ticket = {};
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

					TicketService.create($scope.ticket, function() {
						$scope.getTickets();
						$scope.reset();
					});

				}
			};

			$scope.update = function() {
				TicketService.update($scope.ticket, function() {
					$scope.rechercherTicket($scope.ticket.qc);
					$scope.reset();
				});

			};

			$scope.edit = function(ticket) {
				$scope.ticket = ticket;
			};

			$scope.remove = function(ticket) {
				TicketService.remove({
					id : ticket.id
				}, function(res) {
					$scope.getTickets();
					$scope.reset();
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

		});