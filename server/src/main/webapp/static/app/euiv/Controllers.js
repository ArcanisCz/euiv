define([
    "./controller/MainController"
], function (MainController) {
    return angular.module('Controllers', [
        MainController.name
    ]);
});