//this is for intercept URL and use route
var interceptor = function($rootScope, $q, $location) {

	function success(response) {
		// $log.info('$scope.connected');
		
		//if ($rootScope.connected != true)
		//	$location.url("login");
		

		return response;
		// return $q.reject(response);
	}

	function error(response) {
		
		var status = response.status;
		var config = response.config;
		var method = config.method;
		var url = config.url;

		if (status == 401) {
			$location.path("/");
		} else {
			$rootScope.error = method + " on " + url + " failed with status "
					+ status;
		}

		return $q.reject(response);
	}

	return function(promise) {
		return promise.then(success, error);
	};

};

interceptor.$inject = [ '$rootScope', '$q', '$location' ];