require([
    "jquery",
    "angular",
    "app/euiv/App",
    "app/euiv/Routes"
], function ($, angular, App) {
    $(document).ready(function () {
        angular.bootstrap(document, [App.name]);
    });
});