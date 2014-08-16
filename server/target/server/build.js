({
    appDir: 'static/app',
    dir: 'static/app-build',
    baseUrl: "./",
    fileExclusionRegExp: /(^example)|(.git)|node_modules$/,
    modules: [
        {
            name: 'main.min',
            create: true,
            include: ['main']
        }
    ],
    paths: {
        app: '../app',
        nls: "../nls",

        //libs
        text: "../lib/require/text",
        jquery: "../lib/jquery/jquery-2.1.1",
        i18n: "../lib/require/i18n",
        angular: "../lib/angular/angular",
        angularRoute: '../lib/angular/angular-route',
        angularSegment: '../lib/angular/angular-route-segment',
        angularView: '../lib/angular/angular-view-segment',
        less: "../lib/require-less/less",
        "less-builder": "../lib/require-less/less-builder",
        normalize: "../lib/require-less/normalize"
    },
    shim: {
        'angular': {'exports': 'angular'},
        'angularRoute': ['angular'],
        'angularSegment': ['angular'],
        'angularView': ['angular']
    }
})