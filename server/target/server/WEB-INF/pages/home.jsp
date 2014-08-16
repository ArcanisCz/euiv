<html>
<head>
    <script src="static/lib/require/require.js"></script>
    <script type="text/javascript">
        require(['./static/app/config'], function (config) {
            require(['app/main']);
        });
    </script>
</head>

<body>
    <div app-view-segment="0"></div>
    <div ng-show="loader.show">Loading...</div>
</body>
</html>