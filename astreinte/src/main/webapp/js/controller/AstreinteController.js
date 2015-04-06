var AstreinteController = function($http, $scope, $log, AstreinteService) {

	$scope.byressource = function() {
		idqc = -1;
		idressource = -1;
		if (!!$scope.astreinte && !!$scope.astreinte.ressource
				&& !!$scope.astreinte.ressource.id) {
			idressource = $scope.astreinte.ressource.id;
		}
		if (!!$scope.astreinte && !!$scope.astreinte.ticket
				&& !!$scope.astreinte.ticket.id) {
			idqc = $scope.astreinte.ticket.id;
		}
		$http.post('rest/astreinte/byressource/', {
			idressource : idressource,
			idqc : idqc
		}).success(function(data) {
			$scope.listAstreinte = data;
		});
	};

	$scope.checkChevechmentList = [];
	$scope.checkChevechment = function() {
		$http.post('rest/astreinte/check/', $scope.astreinte).success(function(data) {
			$scope.checkChevechmentList = data;
		});
	}

	$scope.byressource();

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
		var today = new Date();
		var offsetday = "";
		var offsetmon = "";
		if (today.getDate() < 10)
			offsetday = "0";
		if (today.getMonth() < 10)
			offsetmon = "0";
		var m = today.getMonth() + 1;
		$scope.astreinte.dateAstreinte = today.getFullYear() + "-" + offsetmon
				+ m + "-" + offsetday + today.getDate();
		$scope.byressource();
	};

	// save or update
	$scope.save = function() {
		if ($scope.astreinte.id > 0) {
			$scope.update();
		} else {
			$scope.astreinte.$create(function() {
				$scope.byressource();
				$scope.reset();
			});
		}
	};

	$scope.update = function() {
		$scope.astreinte.$update(function() {
			$scope.byressource();
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
			$scope.byressource();
		});
	};

	$scope.calculate = function(alist) {
		if (!!alist && !!alist.length) {
			var tmp = 0;
			for (var i = 0, size = alist.length; i < size; i++) {
				var item = alist[i];
				tmp += item.charge;
			}
			return tmp;
		} else {
			return -1;
		}
	};

	$scope.reset();

};

AstreinteController.$inject = [ '$http', '$scope', '$log', 'AstreinteService' ];
