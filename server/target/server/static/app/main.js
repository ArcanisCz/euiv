require([
    "angular",
    "app/euiv/App",
    "app/euiv/Routes"
], function (angular, App, Routes) {
    angular.element(document.getElementsByTagName('html')[0]);
    angular.element().ready(function () {
        angular.bootstrap(document, [App.name]);
    });
});