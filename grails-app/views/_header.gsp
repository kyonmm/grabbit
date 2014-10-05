<div class="container">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Grabbit</a>
    </div>
    <div class="navbar-collapse collapse">
        <sec:ifLoggedIn>
            <ul class="nav navbar-nav">
                <li class="nav-item item-home active">
                    <g:remoteLink class="" controller="home" method="GET" update="content" before="\$(this).find('.loading').show()" onComplete="\$('.loading').hide();\$('.nav-item').removeClass('active');\$('.item-home').addClass('active');">
                        <g:message code="default.home.label"/>
                        <span class="loading">
                            <span class="glyphicon glyphicon-refresh spinner"/>
                        </span>
                    </g:remoteLink>
                </li>
                <li class="nav-item item-tag" >
                    <g:remoteLink controller="tag" method="GET" update="content" before="\$(this).find('.loading').show()" onComplete="\$('.loading').hide();\$('.nav-item').removeClass('active');\$('.item-tag').addClass('active');">
                        TestTag
                        <span class="loading">
                            <span class="glyphicon glyphicon-refresh spinner"/>
                        </span>
                    </g:remoteLink>
                </li>
                <li class="nav-item item-testCase">
                    <g:remoteLink controller="testCase" method="GET" update="content" before="\$(this).find('.loading').show()" onComplete="\$('.loading').hide();\$('.nav-item').removeClass('active');\$('.item-testCase').addClass('active');">
                        TestCase
                        <span class="loading">
                            <span class="glyphicon glyphicon-refresh spinner"/>
                        </span>
                    </g:remoteLink>
                </li>
            </ul>
            <div class=" navbar-form navbar-right">
                <g:link controller="logout" class="btn btn-default">Logout</g:link>
            </div>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <form action="${resource('file': 'j_spring_security_check')}" method='POST' id='loginForm' class='navbar-form navbar-right' autocomplete='off' role="form">
                <g:if test='${flash.message}'>
                    <div class='login_message'>${flash.message}</div>
                </g:if>
                <div class="form-group">
                    <input type='text' placeholder="User Name" class='text_' name='j_username' id='username'class="form-control"/>
                </div>
                <div class="form-group">
                    <input type='password' placeholder="Password" class='text_' name='j_password' id='password' class="form-control"/>
                </div>
                <div class="form-group">
                    <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                    <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>                        </p>
                </div>
                <input type='submit' id="submit" class="btn btn-success" value='${message(code: "springSecurity.login.button")}'/>
            </form>
        </sec:ifNotLoggedIn>
    </div><!--/.nav-collapse -->
</div>
%{--<div class="jumbotron hidden-xs">--}%
  %{--<div class="container">--}%
    %{--<div class="col-xs-4">--}%
      %{--<img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/>--}%
    %{--</div>--}%
    %{--<div class="col-xs-8">--}%
      %{--<h1>Grabbit</h1>--}%
    %{--</div>--}%
  %{--</div>--}%
%{--</div>--}%
