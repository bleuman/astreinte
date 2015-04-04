$app.factory('TicketService', [ '$resource', function($resource) {
	return $resource('rest/ticket/:id', {}, {
		
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

