<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/style.css"/>
    <script src="static/lib/require/require.js"></script>
    <%--<script src="static/app-build/main.min.js"></script>--%>
    <script type="text/javascript">
        require(['./static/app/config'], function (config) {
            require(['app/main']);
        });
    </script>
</head>

<body>
<div app-view-segment="0"></div>
<div id="loader" ng-show="loader.show">
    <div>Loading...</div>
</div>
</body>
</html>