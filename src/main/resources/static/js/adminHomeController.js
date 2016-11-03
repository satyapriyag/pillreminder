angular.module("app", []).controller("home",
				function($http, $location) {
					console.log("Welcome");
					var self = this;

					$http.get("user").success(function(data) {
						console.log("success - " + data);
						self.user = data;
						self.authenticated = true;
						console.log(self.user.userRole);
						console.log(self.user.userEmail);
						if (self.user.userRole == 1) {
							self.role1 = true;
						} else {
							self.role1 = false;
						}

					}).error(function() {
						console.log("error");
						self.user = "N/A";
						self.authenticated = false;
						//if (status == 401) {
							self.logout();
						//}
					});

					self.logout = function() {
						$http.post('logout', {}).success(function() {
							self.authenticated = false;
							$location.path("/");
						}).error(function(data) {
							console.log("Logout failed")
							self.authenticated = false;
						});
					};
				});
$(document).ready(function(){
	$(".loader").show();
	$.ajax({
		url: 'feed',
		dataType:'json',
		success: function(data){
			$(".loader").hide();
			$.each(data, function(i, d) {
				var row='<div class="col-md-12"><div class="update-nag"><div class="update-split update-info"><i class="glyphicon glyphicon-hand-right"></i></div>';
				row += '<div class="update-text"><b>'+data[i].title +'</b>'+ data[i].description + '</div>';
          row+='</div></div></div>';
          $("#feed").append(row);
			});
		}
	});
});