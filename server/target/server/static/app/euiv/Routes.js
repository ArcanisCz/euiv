define([
    "./App",
    "text!./view/MainLayout.html",
    "text!./view/Content.html",
    "./controller/MainController",
    "angular",
    "angularRoute",
    "angularSegment",
    "angularView"
], function (app, MainLayout, Content, MainController) {
    return app.config(['$routeProvider', "$routeSegmentProvider", function ($routeProvider, $routeSegmentProvider) {
        $routeSegmentProvider
            .when('/', 's1')
            .segment('s1', {
                template: MainLayout,
                controller: MainController.name
            })
            .within()
            .segment("home", {
                default: true,
                template: Content
            })
            .up();
        $routeProvider.otherwise({redirectTo: '/'});
    }]);
});