var ManagerController = function($scope,$log, ManagerService) {

	$scope.manager = new ManagerService();

	$scope.listManagers = ManagerService.list();

	$scope.reset = function() {
		$scope.manager = new ManagerService();
	};

	// save or update
	$scope.save = function() {

		if ($scope.manager.id > 0) {
			$scope.update();
		} else {

			$scope.manager.$create(function() {
				// depois que salvamos atualizamos a lista
				$scope.listManagers = ManagerService.list();
				$scope.reset();
			});
		}
	};

	$scope.update = function() {
		$scope.manager.$update(function() {
			$scope.listManagers = ManagerService.list();
			$scope.reset();
		});
	};

	$scope.edit = function(manager) {
		$scope.manager = manager;
	};

	$scope.remove = function(manager) {
		manager.$remove({
			id : manager.id
		}, function(res) {
			$scope.listManagers = ManagerService.list();
		});
	};

};
// isso aqui pe dependency injection
ManagerController.$inject = [ '$scope','$log', 'ManagerService' ];
