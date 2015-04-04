var RessourceController = function($scope,$log,RessourceService) {

	$scope.ressource = new RessourceService();

	$scope.listRessources =RessourceService.list();

	$scope.reset = function() {
		$scope.ressource = new RessourceService();
	};

	// save or update
	$scope.save = function() {

		if ($scope.ressource.id > 0) {
			$scope.update();
		} else {

			$scope.ressource.$create(function() {
				// depois que salvamos atualizamos a lista
				$scope.listRessources = RessourceService.list();
				$scope.reset();
			});
		}
	};

	$scope.update = function() {
		$scope.ressource.$update(function() {
			$scope.listRessources = RessourceService.list();
			$scope.reset();
		});
	};

	$scope.edit = function(ressource) {
		$scope.ressource = ressource;
	};

	$scope.remove = function(ressource) {
		ressource.$remove({
			id : ressource.id
		}, function(res) {
			$scope.listRessources =RessourceService.list();
		});
	};

};
// isso aqui pe dependency injection
RessourceController.$inject = [ '$scope','$log', 'RessourceService' ];
