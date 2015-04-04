$app.factory('ManagerService', [ '$resource', function($resource) {
	return $resource('rest/manager/:id', {}, {
		
		create : {
			method : 'POST'
		},
		list : {
			method : 'GET',
			isArray : true
		},
		update :{
			method : "PUT"
		},
		remove :{
			method: "DELETE" 
		}		
	}

	);

} ]);

