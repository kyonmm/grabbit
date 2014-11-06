<g:set var="entityName" value="${message(code: 'testCase.label', default: 'TestCase')}"/>
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

            <div class="form-group ${hasErrors(bean: testCaseInstance, field: 'name', 'has-error')}">
                <label for="name" class="control-label">
                    <g:message code="testCase.name.label" default="Name"/>

                </label>
                <g:textArea class="form-control" placeholder="Enter Name" name="name" cols="40" rows="2" maxlength="250"
                            value="${testCaseInstance?.name}"/>

                <g:hasErrors bean="${testCaseInstance}" field="name">
                    <g:eachError bean="${testCaseInstance}" field="name">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-group ${hasErrors(bean: testCaseInstance, field: 'scenario', 'has-error')}">
                <label for="scenario" class="control-label">
                    <g:message code="testCase.scenario.label" default="Scenario"/>

                </label>
                <g:textArea class="form-control" placeholder="Enter Scenario" name="scenario" cols="40" rows="5"
                            maxlength="1000" value="${testCaseInstance?.scenario}"/>

                <g:hasErrors bean="${testCaseInstance}" field="scenario">
                    <g:eachError bean="${testCaseInstance}" field="scenario">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-group ${hasErrors(bean: testCaseInstance, field: 'tags', 'has-error')}">
                <label for="tags" class="control-label">
                    <g:message code="testCase.tags.label" default="Tags"/>

                </label>
                <g:select class="form-control" name="tags" from="${org.kyonmm.grabbit.Tag.list()}" multiple="multiple"
                          optionKey="id" optionValue="name" value="${testCaseInstance?.tags?.name}"/>

                <g:hasErrors bean="${testCaseInstance}" field="tags">
                    <g:eachError bean="${testCaseInstance}" field="tags">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <g:if test="${edit}">
                <g:hiddenField name="id" value="${testCaseInstance?.id}"/>
                <g:hiddenField name="version" value="${testCaseInstance?.version}"/>
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
