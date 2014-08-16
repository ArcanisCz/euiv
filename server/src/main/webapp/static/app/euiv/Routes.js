define([
    "./App",
    "./controller/MainController",
    "text!./view/LayoutView.html",
    "text!./view/ContentView.html",
    "less!./view/LayoutView.less",
    "less!./view/ContentView.less",
    "angular",
    "angularRoute",
    "angularSegment",
    "angularView"
], function (app, MainController, LayoutView, ContentView) {
    return app.config(['$routeProvider', "$routeSegmentProvider", function ($routeProvider, $routeSegmentProvider) {
        $routeSegmentProvider
            .when('/', 's1')
            .segment('s1', {
                template: LayoutView,
                controller: MainController.name
            })
            .within()
            .segment("home", {
                default: true,
                template: ContentView
            })
            .up();
        $routeProvider.otherwise({redirectTo: '/'});
    }]);
});