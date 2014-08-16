requirejs.config({
    baseUrl: 'static/lib',
    paths: {
        app: '../app',
        nls: "../nls",

        //libs
        text: "require/text",
        jquery: "jquery/jquery-2.1.1",
        i18n: "require/i18n",
        angular: "angular/angular",
        angularRoute: 'angular/angular-route',
        angularSegment: 'angular/angular-route-segment',
        angularView: 'angular/angular-view-segment'
    },
    shim: {
        'angular': {'exports': 'angular'},
        'angularRoute': ['angular'],
        'angularSegment': ['angular'],
        'angularView': ['angular']
    }
});