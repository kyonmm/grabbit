<script>
    $(function () {
        $('.nav-item').removeClass('active');
        $('.item-tag').addClass('active');
    });
</script>
<g:set var="entityName" value="${message(code: 'tag.label', default: 'Tag')}"/>
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
                    <g:sortableColumn property="name" title="${message(code: 'tag.name.label', default: 'Name')}"
                                      action="filter" method="GET" params="${filterParams}"
                                      before="\$('.panel-heading').find('.loading').show()"
                                      onComplete="\$('.loading').hide();"/>
                    <g:sortableColumn property="description"
                                      title="${message(code: 'tag.description.label', default: 'Description')}"
                                      action="filter" method="GET" params="${filterParams}"
                                      before="\$('.panel-heading').find('.loading').show()"
                                      onComplete="\$('.loading').hide();"/>
                    <th><g:message code="default.options.label" default="Options"/></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${items}" status="i" var="tagInstance">
                    <tr>
                        <td>${fieldValue(bean: tagInstance, field: "name")}</td>
                        <td>${raw(tagInstance.description?.encodeAsHTML()?.replaceAll("\n", "<br />"))}</td>
                        <td>
                            <g:remoteLink action="edit" id="${tagInstance.id}" update="form" method="GET"
                                          before="\$('.panel-heading').find('.loading').show()"
                                          onComplete="\$('.loading').hide();"><span class="label label-success"><span
                                    class="glyphicon glyphicon-eye-open"></span></span></g:remoteLink>
                            <g:remoteLink action="delete" id="${tagInstance.id}" update="content"
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
        <filterpane:filterPane domain="Tag" listDistinct="true" showTitle="n"/>
    </div>
</div>
