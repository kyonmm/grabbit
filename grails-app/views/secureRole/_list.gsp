<g:set var="entityName" value="${message(code: 'secureRole.label', default: 'SecureRole')}"/>
<div class="panel panel-default">
    <div class="panel-heading">
        <h1>
            <g:message code="default.list.label" args="[entityName]"/>
            <span class="loading">
                <small><span class="glyphicon glyphicon-refresh spinner"/></small>
            </span>
        </h1>
    </div>

    <div class="panel-body">
        <g:if test="${flash.listMessage}">
            <div class="alert alert-info alert-dismissable" role="status"><button type="button" class="close"
                                                                                  data-dismiss="alert"
                                                                                  aria-hidden="true">&times;</button>${flash.listMessage}
            </div>
        </g:if>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>

                    <util:remoteSortableColumn property="authority"
                                               title="${message(code: 'secureRole.authority.label', default: 'Authority')}"
                                               action="list" update="list" method="GET" params="${params}"
                                               before="\$('.panel-heading').find('.loading').show()"
                                               onComplete="\$('.loading').hide();"/>

                    <th><g:message code="default.options.label" default="Options"/></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${items}" status="i" var="secureRoleInstance">
                    <tr>

                        <td>${fieldValue(bean: secureRoleInstance, field: "authority")}</td>

                        <td>
                            <g:remoteLink action="edit" id="${secureRoleInstance.id}" update="form" method="GET"
                                          before="\$('.panel-heading').find('.loading').show()"
                                          onComplete="\$('.loading').hide();"><span class="label label-success"><span
                                    class="glyphicon glyphicon-eye-open"></span></span></g:remoteLink>
                            <g:remoteLink action="delete" id="${secureRoleInstance.id}" update="content"
                                          before="if(confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}') == false) return false"><span
                                    class="label label-danger"><span class="glyphicon glyphicon-remove"></span>
                            </span></g:remoteLink>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
        <util:remotePaginate total="${total}" action="list" update="list" method="GET"
                             before="\$('.panel-heading').find('.loading').show()" onComplete="\$('.loading').hide();"/>
    </div>
</div>
