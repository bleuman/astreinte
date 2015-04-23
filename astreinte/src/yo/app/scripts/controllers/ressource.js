'use strict';

angular.module('mytodoApp').controller('RessourceCtrl',
		function($http, $scope, $log, $rootScope, RessourceService) {

			
			$scope.getRessources = function() {
				RessourceService.list($scope.ressource, function(data) {
					$scope.resultats = data;
				});
			};
			
			

			$scope.getRessources();
			$scope.reset = function() {
				$scope.resultats = {};
			};

			$scope.save = function() {

				if ($scope.ressource.id > 0) {
					$scope.update();
				} else {

					RessourceService.create($scope.ressource, function() {
						$scope.getTickets();
						$scope.reset();
					});

				}
			};

			$scope.update = function() {
				RessourceService.update($scope.ressource, function() {
					$scope.getRessources();
					$scope.reset();
				});

			};

			$scope.edit = function(ressource) {
				$scope.ressource = ressource;
			};

			$scope.remove = function(ressource) {
				RessourceService.remove({
					id : ressource.id
				}, function(res) {
					$scope.getRessources();
					$scope.reset();
				});
			};

		});