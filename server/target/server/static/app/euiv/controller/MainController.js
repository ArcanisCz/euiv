define([
    "angular",
    "async"
], function (angular, async) {
    return angular.module('MainController', []).controller('MainController', ['$scope', "$http", "europaSave", function ($scope, $http, europaSave) {
        $scope.save = {
            date: "",
            player: "",
            countries: {}
        };

        europaSave.load("aaa", function (data) {
            console.log(data);
            $scope.save.date = data.date;
            $scope.save.player = data.player;
            async.reduce(Object.keys(data.countries), {}, function (memo, code, callback) {
                //                console.log(code, code == "---", code.match(/C[0-9]{2}]/) != null, code.match(/O[0-9]{2}]/) != null);
                if (code != "---" && !code.match(/C[0-9]{2}/) && !code.match(/O[0-9]{2}/)) {
                    memo[code] = {
                        code: code
                    }
                }
                callback(null, memo);
            }, function (err, result) {
                $scope.save.countries = result;
            });
        });
    }]);
});