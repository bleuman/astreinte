var $app = angular.module('astreinte', [ 'ngResource','ngRoute' ]);
$app.directive("modalAstreinte",function(){
    return {
      restrict: 'E',
      templateUrl:'view/modalAstrinte.html'
    };
  });
