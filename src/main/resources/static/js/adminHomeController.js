angular.module("app", []).controller("home",
				function($http, $location) {
					console.log("Welcome");
					var self = this;

					$http.get("/user").success(function(data) {
						console.log("success - " + data);
						self.user = data;
						self.authenticated = true;
						console.log(self.user.userRole);
						console.log(self.user.userEmail);
						if (self.user.userRole == 1) {
							self.role1 = true;
						} else {
							/* $location.path("/quizplay"); */
							self.role1 = false;
						}

					}).error(function() {
						console.log("error");
						self.user = "N/A";
						self.authenticated = false;
					});

					self.logout = function() {
						$http.post('/logout', {}).success(function() {
							self.authenticated = false;
							$location.path("/");
						}).error(function(data) {
							console.log("Logout failed")
							self.authenticated = false;
						});
					};
				});