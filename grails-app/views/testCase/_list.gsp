<g:set var="entityName" value="${message(code: 'testCase.label', default: 'TestCase')}"/>
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

                    <g:sortableColumn property="name" title="${message(code: 'testCase.name.label', default: 'Name')}"
                                      action="filter" method="GET" params="${filterParams}"
                                      before="\$('.panel-heading').find('.loading').show()"
                                      onComplete="\$('.loading').hide();"/>

                    <g:sortableColumn property="scenario"
                                      title="${message(code: 'testCase.scenario.label', default: 'Scenario')}"
                                      action="filter" method="GET" params="${filterParams}"
                                      before="\$('.panel-heading').find('.loading').show()"
                                      onComplete="\$('.loading').hide();"/>

                    <th><g:message code="testCase.tags.label" default="Tags"/></th>

                    <th><g:message code="default.options.label" default="Options"/></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${items}" status="i" var="testCaseInstance">
                    <tr id="testCaseRow">

                        <td>${fieldValue(bean: testCaseInstance, field: "name")}</td>

                        <td>${raw(testCaseInstance.scenario?.encodeAsHTML()?.replaceAll("\n", "<br />"))}</td>

                        <td>
                            <g:each in="${testCaseInstance.tags}" var="tag">
                                <filterpane:filterLink
                                        values="${['tags.name': tag.name]}">${tag.name}<br/></filterpane:filterLink>
                            %{--<g:remoteLink property="${tag}" action="filter" method="GET" update="content" params="${filterParams}">${tag.name}<br/></g:remoteLink>--}%
                            </g:each>
                        </td>

                        <td>
                            <g:remoteLink action="edit" id="${testCaseInstance.id}" update="form" method="GET"
                                          before="\$('.panel-heading').find('.loading').show()"
                                          onComplete="\$('.loading').hide();"><span class="label label-success"><span
                                    class="glyphicon glyphicon-eye-open"></span></span></g:remoteLink>
                            <g:remoteLink action="delete" id="${testCaseInstance.id}" update="content"
                                          before="if(confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}') == false) return false"><span
                                    class="label label-danger"><span class="glyphicon glyphicon-remove"></span>
                            </span></g:remoteLink>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div class="paginateButtons">
            <util:remotePaginate total="${total}" action="filter" params="${filterParams}" update="content" method="GET"
                                 before="\$('.panel-heading').find('.loading').show()"
                                 onComplete="\$('.loading').hide();"/>
            <filterpane:filterButton text="Filter Me" appliedText="Change Filter"/>
            <filterpane:isNotFiltered>Pure and Unfiltered!</filterpane:isNotFiltered>
            <filterpane:isFiltered>Filter Applied!</filterpane:isFiltered>
        </div>
        <filterpane:filterPane domain="TestCase" listDistinct="true" associatedProperties="tags.name" showTitle="n"
                               excludeProperties="dateCreated"/>
    </div>
</div>
