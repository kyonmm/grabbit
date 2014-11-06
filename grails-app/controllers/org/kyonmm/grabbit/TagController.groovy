package org.kyonmm.grabbit

import grails.plugin.springsecurity.annotation.Secured

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
        tagService.delete(map.tagInstance)
        flash.listMessage = message(code: 'default.deleted.message',
                args: [message(code: 'tag.label',
                        default: 'Tag'), id])
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