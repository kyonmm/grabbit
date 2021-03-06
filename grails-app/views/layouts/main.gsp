<!DOCTYPE html>
<html>
<head>

    <sec:ifNotLoggedIn>
        <title>Grabbit</title>
    </sec:ifNotLoggedIn>
    <sec:ifLoggedIn>
        <title>Grabbit ${controllerName[0].toUpperCase() + controllerName.substring(1)}</title>
    </sec:ifLoggedIn>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, charset=UTF-8">
    <link rel="stylesheet" href="${resource(plugin: 'optimus', dir: 'css', file: 'bootstrap.min.css')}" type="text/css"
          media="screen">
    <link rel="stylesheet" href="${resource(plugin: 'optimus', dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'ext.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(plugin: 'filterpane', dir: 'css', file: 'fp.css')}" type="text/css">

<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
    <sec:ifNotLoggedIn>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'carousel.css')}" type="text/css">
    </sec:ifNotLoggedIn>
    <g:layoutHead/>
</head>

<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <g:render template="/header"/>
</div>
<sec:ifLoggedIn>
    <div id="content" class="col-sm-12 col-lg-12" role="main">
        <g:layoutBody/>
    </div>
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <div class="carousel-inner">
            <div class="item active">
                <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                     alt="First slide">

                <div class="container">
                    <div class="carousel-caption">
                        <h1>Example headline.</h1>

                        <p>Note: If you're viewing this page via a <code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.
                        </p>

                        <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
                    </div>
                </div>
            </div>

            <div class="item">
                <img src="data:image/gif;base64,R0lGODlhAQABAIAAAGZmZgAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                     alt="Second slide">

                <div class="container">
                    <div class="carousel-caption">
                        <h1>Another example headline.</h1>

                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>

                        <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
                    </div>
                </div>
            </div>

            <div class="item">
                <img src="data:image/gif;base64,R0lGODlhAQABAIAAAFVVVQAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                     alt="Third slide">

                <div class="container">
                    <div class="carousel-caption">
                        <h1>One more for good measure.</h1>

                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>

                        <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p>
                    </div>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"><span
                class="glyphicon glyphicon-chevron-left"></span></a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"><span
                class="glyphicon glyphicon-chevron-right"></span></a>
    </div><!-- /.carousel -->
</sec:ifNotLoggedIn>
<script src="${resource(plugin: 'jquery', dir: 'js/jquery', file: 'jquery-1.11.1.min.js')}"></script>
<script src="${resource(plugin: 'optimus', dir: 'js', file: 'bootstrap.min.js')}"></script>
<script src="${resource(plugin: 'filterpane', dir: 'js', file: 'fp.js')}"></script>
</body>
</html>
