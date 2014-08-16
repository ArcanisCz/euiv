define([
    'angular',
    "./Controllers"
], function (angular, Controllers) {
    console.log(less);
    return angular.module('euiv', [
        "ngRoute",
        'route-segment',
        'view-segment',
        Controllers.name
    ]);
});