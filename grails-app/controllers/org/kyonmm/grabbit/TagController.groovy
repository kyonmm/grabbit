package org.kyonmm.grabbit

import grails.plugin.springsecurity.annotation.Secured
import org.grails.plugin.filterpane.FilterPaneUtils

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class TagController {

    static allowedMethods = [
            index  : 'GET',
            content: 'GET',
            list   : 'GET',
            create : 'GET',
            save   : 'POST',
            edit   : 'GET',
            update : 'POST',
            delete : 'POST'
    ]

    def tagService
    def crackingService
    def filterPaneService
    def filter = {
        if (!params.max) params.max = 10
        flash.navigation = "tag"
        render(template: 'content',
                model: [items       : filterPaneService.filter(params, Tag),
                        total       : filterPaneService.count(params, Tag),
                        filterParams: FilterPaneUtils.extractFilterParams(params),
                        params      : params],
                layout: 'main'
        )
    }


    def index() {
        renderList('content')
    }

    def content() {
        renderList('content')
    }

    def list() {
        renderList('list')
    }

    def create() {

        def model = [tagInstance: new Tag(params)]
        render(template: 'form', model: model)

    }

    def save() {

        def tag = new Tag(params)
        saveOnDb(tag, 'create')

    }

    def edit(Long id) {

        def map = get(id)
        if (!map) return
        map.edit = true
        render(template: 'form', model: map)

    }

    def update(Long id) {

        def map = get(id)
        if (!map) return
        map.tagInstance.properties = params
        map.edit = true
        saveOnDb(map.tagInstance, 'update', true)

    }

    def delete(Long id) {

        def map = get(id)
        if (!map) return
        try{
            tagService.delete(map.tagInstance)
            flash.listMessage = message(code: 'default.deleted.message',
                    args: [message(code: 'tag.label',
                            default: 'Tag'), id])
        }
        catch (Exception e){
            log.error(e.message)
            response.status = 400
            flash.listMessage = message(code: 'default.not.deleted.message',
                    args: [message(code: 'tag.label',
                            default: 'Tag'), id])
        }
        redirect(action: 'content')

    }

    private void renderList(template) {

        def model = [:]
        def result = tagService.list(params)
        model.items = result.items
        model.total = result.total
        render(template: template, model: model, layout: 'main')

    }

    private Map get(Long id) {

        if (id == null) {
            notifyCrack()
            return null
        }
        def tag = tagService.get(id)
        if (!tag) {
            notifyCrack()
            return null
        }
        [tagInstance: tag]

    }

    private void saveOnDb(tag, method, edit = false) {

        try {
            tagService."${method}"(tag)
        } catch (IllegalArgumentException e) {
            response.status = 400
            render(template: 'form', model: [tagInstance: tag,
                                             edit       : edit])
            return
        }
        flash.formMessage = message(
                code: "default.${edit ? 'updated' : 'created'}.message",
                args: [message(code: 'tag.label',
                        default: 'Tag'), tag.id])
        redirect(action: 'edit', id: tag.id)

    }

    private void notifyCrack() {

        crackingService.notify(request, params)
        redirect(controller: 'logout')

    }

}