define([
    "angular",
    "./component/CountryFlagComponent/component"
], function (angular, CountryFlagComponent) {
    return angular.module('Components', [
        CountryFlagComponent.name
    ]);
});