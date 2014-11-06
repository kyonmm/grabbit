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
                <li class="nav-item item-home ${controllerName == "home" ? 'active' : ''}">
                    <g:link controller="home" onclick="\$(this).find('.loading').show()">
                        <g:message code="default.home.label"/>
                        <span class="loading">
                            <span class="glyphicon glyphicon-refresh spinner"/>
                        </span>
                    </g:link>
                </li>
                <li class="nav-item item-tag ${controllerName == "tag" ? 'active' : ''}">
                    <g:link controller="tag" onclick="\$(this).find('.loading').show()">
                        TestTag
                        <span class="loading">
                            <span class="glyphicon glyphicon-refresh spinner"/>
                        </span>
                    </g:link>
                </li>
                <li class="nav-item item-testCase ${controllerName == "testCase" ? 'active' : ''}">
                    <g:link controller="testCase" onclick="\$(this).find('.loading').show()">
                        TestCase
                        <span class="loading">
                            <span class="glyphicon glyphicon-refresh spinner"/>
                        </span>
                    </g:link>
                </li>
                <sec:access url="/secureUser">
                    <li class="dropdown nav-item item-admin ${controllerName == "secureUser" ? ' active' : ''}">
                        <a data-target="#" href="#" class="dropdown-toggle" data-toggle="dropdown">Admin <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li class="item-secureUser">
                                <g:link controller="secureUser" action="index"
                                        onclick="\$(this).find('.loading').show()">
                                    User
                                    <span class="loading">
                                        <span class="glyphicon glyphicon-refresh spinner"/>
                                    </span>
                                </g:link>
                            </li>
                            <li class="divider"></li>
                            <li class="dropdown-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </sec:access>
            </ul>

            <div class=" navbar-form navbar-right" id="logout">
                <g:link controller="logout" class="btn btn-default">Logout</g:link>
            </div>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <form action="${resource('file': 'j_spring_security_check')}" method='POST' id='loginForm'
                  class='navbar-form navbar-right' autocomplete='off' role="form">
                <g:if test='${flash.message}'>
                    <div class='login_message'>${flash.message}</div>
                </g:if>
                <div class="form-group">
                    <input type='text' placeholder="User Name" class='text_' name='j_username' id='username'
                           class="form-control"/>
                </div>

                <div class="form-group">
                    <input type='password' placeholder="Password" class='text_' name='j_password' id='password'
                           class="form-control"/>
                </div>

                <div class="form-group">
                    <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me'
                           <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                    <label for='remember_me'><g:message
                            code="springSecurity.login.remember.me.label"/></label>                        </p>
                </div>
                <input type='submit' id="submit" class="btn btn-success"
                       value='${message(code: "springSecurity.login.button")}'/>
            </form>
        </sec:ifNotLoggedIn>
    </div><!--/.nav-collapse -->
</div>
<script>
    $(document).ready(function(){
        $('.dropdown-toggle').dropdown()
    });
</script>