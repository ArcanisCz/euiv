require([
    "jquery",
    "angular",
    "app/euiv/App",
    "app/euiv/Routes",
    "less!./app/euiv/css/style",
    "less!./app/euiv/css/style1"
], function ($, angular, App) {
    $(document).ready(function () {
        angular.bootstrap(document, [App.name]);
    });
});