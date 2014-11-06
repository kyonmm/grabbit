<g:set var="entityName" value="${message(code: 'secureUser.label', default: 'SecureUser')}"/>
<div class="panel panel-default">
    <div class="panel-heading">
        <g:if test="${edit}">
            <h1><g:message code="default.show.label" args="[entityName]"/></h1>
        </g:if>
        <g:else>
            <h1><g:message code="default.create.label" args="[entityName]"/></h1>
        </g:else>
    </div>

    <div class="panel-body">
        <g:if test="${flash.formMessage}">
            <div class="alert alert-info alert-dismissable" role="status"><button type="button" class="close"
                                                                                  data-dismiss="alert"
                                                                                  aria-hidden="true">&times;</button>${flash.formMessage}
            </div>
        </g:if>
        <form role="form">

            <div class="form-group ${hasErrors(bean: secureUserInstance, field: 'username', 'has-error')}">
                <label for="username" class="control-label">
                    <g:message code="secureUser.username.label" default="Username"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:textField class="form-control" placeholder="Enter Username" name="username" required=""
                             value="${secureUserInstance?.username}"/>

                <g:hasErrors bean="${secureUserInstance}" field="username">
                    <g:eachError bean="${secureUserInstance}" field="username">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-group ${hasErrors(bean: secureUserInstance, field: 'password', 'has-error')}">
                <label for="password" class="control-label">
                    <g:message code="secureUser.password.label" default="Password"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:passwordField class="form-control" placeholder="Enter Password" name="password" required=""
                                 value="${secureUserInstance?.password}"/>

                <g:hasErrors bean="${secureUserInstance}" field="password">
                    <g:eachError bean="${secureUserInstance}" field="password">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-group ${hasErrors(bean: secureUserInstance, field: 'email', 'has-error')}">
                <label for="email" class="control-label">
                    <g:message code="secureUser.email.label" default="Email"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:textField class="form-control" placeholder="Enter Email" name="email" required=""
                             value="${secureUserInstance?.email}"/>

                <g:hasErrors bean="${secureUserInstance}" field="email">
                    <g:eachError bean="${secureUserInstance}" field="email">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-group ${hasErrors(bean: secureUserInstance, field: 'accountExpired', 'has-error')}">
                <label for="accountExpired" class="control-label">
                    <g:message code="secureUser.accountExpired.label" default="Account Expired"/>

                </label>
                <g:checkBox name="accountExpired" value="${secureUserInstance?.accountExpired}"/>

                <g:hasErrors bean="${secureUserInstance}" field="accountExpired">
                    <g:eachError bean="${secureUserInstance}" field="accountExpired">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-group ${hasErrors(bean: secureUserInstance, field: 'accountLocked', 'has-error')}">
                <label for="accountLocked" class="control-label">
                    <g:message code="secureUser.accountLocked.label" default="Account Locked"/>

                </label>
                <g:checkBox name="accountLocked" value="${secureUserInstance?.accountLocked}"/>

                <g:hasErrors bean="${secureUserInstance}" field="accountLocked">
                    <g:eachError bean="${secureUserInstance}" field="accountLocked">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-group ${hasErrors(bean: secureUserInstance, field: 'enabled', 'has-error')}">
                <label for="enabled" class="control-label">
                    <g:message code="secureUser.enabled.label" default="Enabled"/>

                </label>
                <g:checkBox name="enabled" value="${secureUserInstance?.enabled}"/>

                <g:hasErrors bean="${secureUserInstance}" field="enabled">
                    <g:eachError bean="${secureUserInstance}" field="enabled">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-group ${hasErrors(bean: secureUserInstance, field: 'passwordExpired', 'has-error')}">
                <label for="passwordExpired" class="control-label">
                    <g:message code="secureUser.passwordExpired.label" default="Password Expired"/>

                </label>
                <g:checkBox name="passwordExpired" value="${secureUserInstance?.passwordExpired}"/>

                <g:hasErrors bean="${secureUserInstance}" field="passwordExpired">
                    <g:eachError bean="${secureUserInstance}" field="passwordExpired">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <g:if test="${edit}">
                <g:hiddenField name="id" value="${secureUserInstance?.id}"/>
                <g:hiddenField name="version" value="${secureUserInstance?.version}"/>
                <g:submitToRemote class="btn btn-primary" url="[action: 'update']"
                                  update="[success: 'form', failure: 'form']" name="update"
                                  value="${message(code: 'default.button.update.label', default: 'Update')}"
                                  before="\$('form').find('.loading').show()" onComplete="\$('.loading').hide();"
                                  onSuccess="${remoteFunction(action: 'list', update: 'list', method: 'GET')}"/>
                <g:field class="btn btn-default" type="reset" name="reset"
                         value="${message(code: 'default.button.reset.label', default: 'Reset')}"/>
                <g:remoteLink class="btn btn-success" action="create" update="form" method="GET"
                              before="\$('form').find('.loading').show()" onComplete="\$('.loading').hide();"><g:message
                        code="default.button.new.label" default="New"/></g:remoteLink>
            </g:if>
            <g:else>
                <g:submitToRemote class="btn btn-primary" url="[action: 'save']"
                                  update="[success: 'form', failure: 'form']" name="create"
                                  value="${message(code: 'default.button.create.label', default: 'Create')}"
                                  before="\$('form').find('.loading').show()" onComplete="\$('.loading').hide();"
                                  onSuccess="${remoteFunction(action: 'list', update: 'list', method: 'GET')}"/>
                <g:field class="btn btn-default" type="reset" name="reset"
                         value="${message(code: 'default.button.reset.label', default: 'Reset')}"/>
            </g:else>
            <span class="loading">
                <span class="glyphicon glyphicon-refresh spinner"/>
            </span>
        </form>
    </div>
</div>
