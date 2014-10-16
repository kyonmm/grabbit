<!DOCTYPE html>
<html>
<head>
    <title>Grabbit</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0; charset=UTF-8">
    <link rel="stylesheet" href="${resource(plugin:'optimus', dir: 'css', file: 'bootstrap.min.css')}" type="text/css" media="screen">
    <link rel="stylesheet" href="${resource(plugin:'optimus', dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'ext.css')}" type="text/css">
    <script src="${resource(plugin: 'jquery', dir: 'js/jquery', file: 'jquery-1.11.1.min.js')}"></script>
    <script src="${resource(plugin: 'optimus', dir: 'js', file: 'bootstrap.min.js')}"></script>
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
    <sec:ifNotLoggedIn>
        <link rel="stylesheet" href="css/carousel.css" type="text/css">
    </sec:ifNotLoggedIn>
    <g:layoutHead/>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <g:render template="/header"/>
</div>
<div id="content" class="col-sm-12 col-lg-12" role="main">
    <g:layoutBody/>
</div>
</body>
</html>
