define([
    'angular',
    "./Controllers",
    "./Services",
    "./Components"
], function (angular, Controllers, Services, Components) {
    return angular.module('euiv', [
        "ngRoute",
        'route-segment',
        'view-segment',
        Controllers.name,
        Services.name,
        Components.name
    ]);
});