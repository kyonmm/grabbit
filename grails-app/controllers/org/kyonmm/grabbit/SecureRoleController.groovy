package org.kyonmm.grabbit

import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED', 'ROLE_ADMIN'])
class SecureRoleController {

    static allowedMethods = [
        index:'GET',
        content:'GET',
        list:'GET',
        create:'GET',
        save:'POST',
        edit:'GET',
        update:'POST',
        delete:'POST'
    ]

    def secureRoleService
    def crackingService

    def index() {
        redirect( action:'content', params:params )
    }

    def content() {
        renderList( 'content' )
    }

    def list() {
        renderList( 'list' )
    }

    def create() {

        def model = [ secureRoleInstance:new SecureRole( params ) ]
        render( template:'form', model:model )

    }

    def save() {

        def secureRole = new SecureRole( params )
        saveOnDb( secureRole, 'create' )

    }

    def edit( Long id ) {

        def map = get( id )
        if ( !map ) return
        map.edit = true
        render( template:'form', model:map )

    }

    def update( Long id ) {

        def map = get( id )
        if ( !map ) return
        map.secureRoleInstance.properties = params
        map.edit = true
        saveOnDb( map.secureRoleInstance, 'update', true )

    }

    def delete( Long id ) {

        def map = get( id )
        if ( !map ) return
        secureRoleService.delete( map.secureRoleInstance )
        flash.listMessage = message( code:'default.deleted.message',
            args:[ message( code:'secureRole.label',
            default:'SecureRole' ), id ] )
        redirect( action:'content' )

    }

    private void renderList( template ) {

        def model = [:]
        def result = secureRoleService.list( params )
        model.items = result.items
        model.total = result.total
        render( template:template, model:model )

    }

    private Map get( Long id ) {

        if ( id == null ) {
            notifyCrack()
            return null
        }
        def secureRole = secureRoleService.get( id )
        if ( !secureRole ) {
            notifyCrack()
            return null
        }
        [ secureRoleInstance:secureRole ]

    }

    private void saveOnDb( secureRole, method, edit = false ) {

        try {
            secureRoleService."${method}"( secureRole )
        } catch ( IllegalArgumentException e ) {
            response.status = 400
            render( template:'form', model:[ secureRoleInstance:secureRole,
                edit:edit ] )
            return
        }
        flash.formMessage = message(
            code:"default.${edit?'updated':'created'}.message",
            args:[ message( code:'secureRole.label',
            default:'SecureRole' ), secureRole.id])
        redirect( action:'edit', id:secureRole.id )

    }

    private void notifyCrack() {

        crackingService.notify( request, params )
        redirect( controller:'logout' )

    }

}