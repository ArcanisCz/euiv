define([
    'angular',
    "./Controllers"
], function (angular, Controllers) {
    return angular.module('euiv', [
        "ngRoute",
        'route-segment',
        'view-segment',
        Controllers.name
    ]);
});