$app.factory('AstreinteService', [
		'$http',
		'$rootScope',
		function($http, $rootScope) {
			return {

				create : function(data, success, error) {
					$http.post('rest/astreinte/', data).success(
							success).error(error);
				},
				list : function(data, success, error) {
					$http.get('rest/astreinte/', data).success(
							success).error(error);
				},
				update : function(data, success, error) {
					$http.put('rest/astreinte/', data).success(
							success).error(error);
				},
				remove : function(data, success, error) {
					$http.delete('rest/astreinte/'+data.id, data).success(
							success).error(error);
				}
			};
		} ]);
