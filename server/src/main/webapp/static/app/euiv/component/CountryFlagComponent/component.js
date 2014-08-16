define([
    "angular",
    "text!./template.html",
    "less!./style.less"
], function (angular, template) {
    return angular.module('CountryFlagComponent', []).directive("countryFlag", [function () {
        return {
            restrict: "E",
            template: template,
            scope: {
                code: "="
            }
        }
    }]);
});