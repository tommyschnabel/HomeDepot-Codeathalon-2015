var app = angular.module('MainApp', []);

app.controller('main-controller', ['$scope', function($scope) {
  
  function codeAddress(zipCode) {
    geocoder.geocode( { 'address': zipCode}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        //Got result, center the map and put it out there
        map.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
      } else {
        alert("Geocode was not successful for the following reason: " + status);
      }
    });
  }

  $scope.zipUpdate = function(){
  console.log($scope.zip);
  codeAddress($scope.zip);
  
  };

}]);



app.directive('storediv', function()
{
	return {
		restrict: 'E',
		transclude: true,
		template: '<td><h4><a>The Home Depot</a></h4><h5>City: {{store.name}} Store id: {{store.storeId}}</h5><td>'
	};
}
);