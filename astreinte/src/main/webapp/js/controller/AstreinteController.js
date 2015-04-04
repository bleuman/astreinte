var AstreinteController = function($http, $scope, $log, AstreinteService) {

	// $scope.dateAstreinte=null;
	// $scope.astreinte = new AstreinteService();
	// $scope.astreinte.hdebut="00:00";
	// $scope.astreinte.hfin="23:59";

	// calling restful service
	$scope.listAstreinte = AstreinteService.list();

	$http.get('rest/ressource/').success(function(data) {
		$scope.listeRessources = data;
	});
	$http.get('rest/ticket/').success(function(data) {
		$scope.listeTickets = data;
	});
	$http.get('rest/typeAstreinte/').success(function(data) {
		$scope.typesAstreinteList = data;
	});

	$scope.reset = function() {
		$scope.astreinte = new AstreinteService();
		$scope.astreinte.hdebut = "00:00:00";
		$scope.astreinte.hfin = "23:59:00";
		var today=new Date();
		var offsetday = "";
		var offsetmon = "";
		if(today.getDate()<10)
			offsetday="0";
		if(today.getMonth()<10)
			offsetmon="0";
		$scope.astreinte.dateAstreinte = today.getFullYear()+"-"+offsetmon+today.getMonth()+"-"+offsetday+today.getDate();
	};
	
	// save or update
	$scope.save = function() {
		if ($scope.astreinte.id > 0) {
			$scope.update();
		} else {

			// $scope.astreinte.hfin="01 01 1900 "+$scope.astreinte.hfin +":00
			// 000";
			$scope.astreinte.$create(function() {
				// depois que salvamos atualizamos a lista
				$scope.listAstreinte = AstreinteService.list();
				$scope.total = $scope.calculate();
				// $scope.reset();
			});
		}
	};

	$scope.update = function() {
		$scope.astreinte.$update(function() {
			$scope.listAstreinte = AstreinteService.list();
			$scope.total = $scope.calculate();
			$scope.reset();
		});
	};

	$scope.edit = function(astreinte) {
		$scope.astreinte = astreinte;
	};

	$scope.remove = function(astreinte) {
		astreinte.$remove({
			id : astreinte.id
		}, function(res) {
			$scope.listAstreinte = AstreinteService.list();
			$scope.total = $scope.calculate();
		});
	};
//	$scope.formatDate = function(val) {
//		if (val != null) {
//			if (val.length == 5)
//				return "1900-01-01T" + val + ":00.000+0000";
//			if (val.length == 8)
//				return "1900-01-01T" + val + ".000+0000";
//		} else {
//			return val;
//		}
//		return val;
//	};

	$scope.calculate = function(alist) {
		var tmp = 0;
		for (var i = 0, size = alist.length; i < size; i++) {
			var item = alist[i];
			tmp += item.charge;
		}
		return tmp;
	};

	
	$scope.reset();

};

// isso aqui pe dependency injection
AstreinteController.$inject = [ '$http', '$scope', '$log', 'AstreinteService' ];
