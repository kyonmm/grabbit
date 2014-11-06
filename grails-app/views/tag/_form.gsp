<g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}"/>
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

            <div class="form-group ${hasErrors(bean: tagInstance, field: 'name', 'has-error')}">
                <label for="name" class="control-label">
                    <g:message code="tag.name.label" default="Name"/>

                </label>
                <g:textArea class="form-control" placeholder="Enter Name" name="name" cols="40" rows="2" maxlength="250"
                            value="${tagInstance?.name}"/>

                <g:hasErrors bean="${tagInstance}" field="name">
                    <g:eachError bean="${tagInstance}" field="name">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-group ${hasErrors(bean: tagInstance, field: 'description', 'has-error')}">
                <label for="description" class="control-label">
                    <g:message code="tag.description.label" default="Description"/>

                </label>
                <g:textArea class="form-control" placeholder="Enter Description" name="description" cols="40" rows="5"
                            maxlength="1000" value="${tagInstance?.description}"/>

                <g:hasErrors bean="${tagInstance}" field="description">
                    <g:eachError bean="${tagInstance}" field="description">
                        <span class="help-block"><g:message error="${it}"/></span>
                    </g:eachError>
                </g:hasErrors>
            </div>


            <g:if test="${edit}">
                <g:hiddenField name="id" value="${tagInstance?.id}"/>
                <g:hiddenField name="version" value="${tagInstance?.version}"/>
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
