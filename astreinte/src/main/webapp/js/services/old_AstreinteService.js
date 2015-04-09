$app.factory('AstreinteService', [ '$resource', function($resource) {
	return $resource('rest/astreinte/:id', {}, {
		
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
		},
		
	}

	);

} ]);

