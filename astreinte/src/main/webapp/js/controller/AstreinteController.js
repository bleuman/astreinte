var AstreinteController = function($http, $scope, $log, AstreinteService) {

	$scope.listAstreinte = $scope.byressource();

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
	};
	
	$scope.byressource=function(){
		var res=null;
		if(!!AstreinteService.ressource.id){
			$http.get('rest/byressource/',{id:AstreinteService.ressource.id}).success(function(data) {
				res = data;
			});
			return res;
		}
			
			
		else
			return AstreinteService.list();
	};

	// save or update
	$scope.save = function() {
		if ($scope.astreinte.id > 0) {
			$scope.update();
		} else {
			$scope.astreinte.$create(function() {
				$scope.listAstreinte = $scope.byressource();
				$scope.reset();
			});
		}
	};

	$scope.update = function() {
		$scope.astreinte.$update(function() {
			$scope.listAstreinte =  $scope.byressource();
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
			$scope.listAstreinte = $scope.byressource();
		});
	};
	

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

AstreinteController.$inject = [ '$http', '$scope', '$log', 'AstreinteService' ];
