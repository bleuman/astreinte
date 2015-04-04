$app.factory('RessourceService', [ '$resource', function($resource) {
	return $resource('rest/ressource/:id', {}, {
		
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

