angular.module('mySpringPOC', [])
	.controller('mySpringPOCController', function($scope,$http) {
		
		$http.get("http://localhost:9100/demoapp/v1/mongo-data")
				.then(function success(resp , status) {
					
					console.log(resp);
					
					$scope.dbRecords = resp.data.data;
												
				},
				function error(data , status) {
					console.log('Internal Server Error ' + status);
				});
		
		$http.get("http://localhost:9100/demoapp/v1/weather-data/524901")
		.then(function success(resp , status) {
			
			console.log(resp);
			
			$scope.longitude = resp.data.coord.lon;
			$scope.latitude = resp.data.coord.lat;
			$scope.cityName = resp.data.name;
			$scope.countryCode = resp.data.sys.country;
			$scope.temperature = resp.data.main.temp;
			$scope.temperatureMin = resp.data.main.temp_min;
			$scope.temperatureMax = resp.data.main.temp_max;
										
		},
		function error(data , status) {
			console.log('Internal Server Error ' + status);
		});
		
	});