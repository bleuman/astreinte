$app.factory('RechercheService', [ '$resource', function($resource) {
	return $resource('rest/ticket/recherche', {}, {
		list : {
			method : 'POST',
			isArray : true
		}
	}

	);

} ]);

