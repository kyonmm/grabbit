<g:set var="entityName" value="${message(code: 'testCase.label', default: 'TestCase')}" />
<div class="panel panel-default">
  <div class="panel-heading">
    <h1>
      <g:message code="default.list.label" args="[entityName]" />
      <span class="loading">
        <small><span class="glyphicon glyphicon-refresh spinner"/></small>
      </span>
    </h1>
  </div>
  <div class="panel-body">
    <g:if test="${flash.listMessage}">
    <div class="alert alert-info alert-dismissable" role="status"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>${flash.listMessage}</div>
    </g:if>
      <g:formRemote name="search" url="[action: 'search']" method="GET" update="list" before="\$('.panel-heading').find('.loading').show()" onComplete="\$('.loading').hide();" >
          Filter: <input type="text" name="q" value="${params.q}"/>
          <input type="submit" class="btn btn-info" value="search" id="submit" />
      </g:formRemote>
      <div class="table-responsive">
      <table class="table table-striped">
        <thead>
          <tr>
            
            <util:remoteSortableColumn property="name" title="${message(code: 'testCase.name.label', default: 'Name')}" action="search" update="list" method="GET" params="${params}" before="\$('.panel-heading').find('.loading').show()" onComplete="\$('.loading').hide();"/>

            <util:remoteSortableColumn property="scenario" title="${message(code: 'testCase.scenario.label', default: 'Scenario')}" action="search" update="list" method="GET" params="${params}" before="\$('.panel-heading').find('.loading').show()" onComplete="\$('.loading').hide();"/>

            <util:remoteSortableColumn property="tag" title="${message(code: 'testCase.tags.label', default: 'Tags')}" action="search" update="list" method="GET" params="${params}" before="\$('.panel-heading').find('.loading').show()" onComplete="\$('.loading').hide();"/>

            <th><g:message code="default.options.label" default="Options" /></th>
          </tr>
        </thead>
        <tbody>
        <g:each in="${items}" status="i" var="testCaseInstance">
          <tr>
            
            <td>${fieldValue(bean: testCaseInstance, field: "name")}</td>

              <td>${fieldValue(bean: testCaseInstance, field: "scenario")}</td>

              <td>
                  <g:each in="${testCaseInstance.tags}" var="tag">
                      <g:remoteLink controller="tag" action="edit" method="GET" update="content" id="${tag.id}">${tag.name} </g:remoteLink>
                  </g:each>
              </td>

            <td>
              <g:remoteLink action="edit" id="${testCaseInstance.id}" update="form" method="GET" before="\$('.panel-heading').find('.loading').show()" onComplete="\$('.loading').hide();"><span class="label label-success"><span class="glyphicon glyphicon-eye-open"></span></span></g:remoteLink>
              <g:remoteLink action="delete" id="${testCaseInstance.id}" update="content" before="if(confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}') == false) return false"><span class="label label-danger"><span class="glyphicon glyphicon-remove"></span></span></g:remoteLink>
            </td>
          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
    <util:remotePaginate total="${total}" action="list" update="list" method="GET" before="\$('.panel-heading').find('.loading').show()" onComplete="\$('.loading').hide();"/>
  </div>
</div>
