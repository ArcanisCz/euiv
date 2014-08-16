define([
    'angular',
    "./service/EuropaSaveService"
], function (angular, EuropaSaveService) {
    var module = angular.module('Services', []);
    module.factory("europaSave", EuropaSaveService);
    return module;
});