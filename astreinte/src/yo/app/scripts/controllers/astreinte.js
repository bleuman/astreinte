'use strict';

angular.module('mytodoApp').controller(
		'AstreinteCtrl', function($http, $scope, $log, $rootScope,
		AstreinteService) {

	$scope.byressource = function(idress) {

		var idqc = -1;
		var idressource = -1;

		if (!!$scope.astreinte && !!$scope.astreinte.ressource
				&& !!$scope.astreinte.ressource.id) {
			idressource = $scope.astreinte.ressource.id;
		}
		if (!!$scope.astreinte && !!$scope.astreinte.ticket
				&& !!$scope.astreinte.ticket.id) {
			idqc = $scope.astreinte.ticket.id;
		}
		/*$http.post('rest/astreinte/byressource/', {
			idressource : idressource,
			idqc : idqc
		}).success(function(data) {
			$scope.listAstreinte = data;
		});*/
		AstreinteService.byressource({
			idressource : idressource,
			idqc : idqc
		}, function(res) {
			$scope.listAstreinte = res;
		}, function(res) {
			$log.info(res);
		});
	};

	$scope.checkChevechmentList = [];
	$scope.checkChevechment = function() {
		AstreinteService.check($scope.astreinte, function(data) {
			$scope.checkChevechmentList = data;
		}, function(res) {
			$log.info(res);
		});
		if ($scope.checkChevechmentList.length > 0) {
			$('#chevModal').modal('show');
		}
	};
	$scope.valide = function(astr) {
		AstreinteService.valide(astr, function(data) {
			$scope.byressource();
		}, function(res) {
			$log.info(res);
		});
	};
	$scope.ongoing = function(astr) {
		AstreinteService.ongoing(astr, function(data) {
			$scope.byressource();
		}, function(res) {
			$log.info(res);
		});
	};
	$scope.rejected = function(astr) {
		AstreinteService.rejected(astr, function(data) {
			$scope.byressource();
		}, function(res) {
			$log.info(res);
		});
	};

	$scope.byressource(null);

	AstreinteService.listRessources({}, function(res) {
		$scope.listeRessources = res;
	}, function(res) {
		$log.info(res);
	});
	AstreinteService.tickets({}, function(res) {
		$scope.listeTickets = res;
	}, function(res) {
		$log.info(res);
	});

	AstreinteService.typeAstreintes({}, function(res) {
		$scope.typesAstreinteList = res;
	}, function(res) {
		$log.info(res);
	});

	$scope.reset = function() {
		$scope.astreinte = {};
		$scope.astreinte.hdebut = "00:00";
		$scope.astreinte.hfin = "23:59";
		$scope.astreinte.statutAstreinte = {};
		$scope.astreinte.statutAstreinte.id = 1;
		if ($rootScope.loginData && !!$rootScope.currentUser.principale.id) {

			$scope.astreinte.ressource = {};
			if ($rootScope.currentUser.principale.role != 'CP')
				$scope.astreinte.ressource.id = $rootScope.currentUser.principale.id;
		}
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
		$scope.byressource(null);
	};

	// save or update
	$scope.save = function() {

		if ($scope.astreinte.id > 0) {
			$scope.update();
		} else {
			$scope.checkChevechment();
			if ($scope.checkChevechmentList.length > 0) {
				return;
			}
			AstreinteService.create($scope.astreinte, function() {
				$scope.byressource(null);
				$scope.reset();
			});
		}
	};

	$scope.update = function() {
		$scope.checkChevechment();
//		if ($scope.checkChevechmentList.length > 0) {
//			return;
//		}
		AstreinteService.update($scope.astreinte, function() {
			$scope.byressource(null);
			$scope.reset();
		});
	};

	$scope.edit = function(astreinte) {
		$scope.astreinte = astreinte;
	};

	$scope.remove = function(ida) {
		AstreinteService.remove({
			id : ida
		}, function(res) {
			$scope.byressource(null);
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
	$scope.calculateField = function(alist,field) {
		if (!!alist && !!alist.length) {
			var tmp = 0;
			for (var i = 0, size = alist.length; i < size; i++) {
				var item = alist[i];
				tmp += item[field];
			}
			return tmp;
		} else {
			return -1;
		}
	};
	$scope.logout = function() {
		$http.get('rest/ressource/logout/');
		$rootScope.token = null;
		window.sessionStorage.removeItem('token');
		window.location = "/";
	};
	$rootScope.authentecated = "false";
	$scope.isauth = function() {

		$http.get('rest/ressource/isauth/').success(
				function(data, status, headers, config) {
					$rootScope.authentecated = data;
					console.info("success : data:" + data + "|status: "
							+ status + "|headers: " + headers + "|config: "
							+ config);
					if ($rootScope.authentecated != "true") {
						console.info("disconnected");
						// window.sessionStorage.removeItem('token');
						window.location = "/";
					}
				}).error(
				function(data, status, headers, config) {
					console.info("error : data:" + data + "|status: " + status
							+ "|headers: " + headers + "|config: " + config);
					if ($rootScope.authentecated != "true") {
						console.info("2 disconnected");
						// window.sessionStorage.removeItem('token');
						window.location = "/";
					}
				});

		if (!$rootScope.authentecated) {
			console.error("3 disconnected");
			// window.sessionStorage.removeItem('token');
			// window.location = "/";
		}

	};

	// isauth
	$scope.reset();
	$rootScope.token = window.sessionStorage.getItem('token');
	if (!$rootScope.token) {
		window.location = "/";
	}

});


