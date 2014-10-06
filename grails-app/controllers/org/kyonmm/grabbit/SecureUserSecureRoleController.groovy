package org.kyonmm.grabbit

import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED', 'ROLE_ADMIN'])
class SecureUserSecureRoleController {

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

    def secureUserSecureRoleService
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

        def model = [ secureUserSecureRoleInstance:new SecureUserSecureRole( params ) ]
        render( template:'form', model:model )

    }

    def save() {

        def secureUserSecureRole = new SecureUserSecureRole( params )
        saveOnDb( secureUserSecureRole, 'create' )

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
        map.secureUserSecureRoleInstance.properties = params
        map.edit = true
        saveOnDb( map.secureUserSecureRoleInstance, 'update', true )

    }

    def delete( Long id ) {

        def map = get( id )
        if ( !map ) return
        secureUserSecureRoleService.delete( map.secureUserSecureRoleInstance )
        flash.listMessage = message( code:'default.deleted.message',
            args:[ message( code:'secureUserSecureRole.label',
            default:'SecureUserSecureRole' ), id ] )
        redirect( action:'content' )

    }

    private void renderList( template ) {

        def model = [:]
        def result = secureUserSecureRoleService.list( params )
        model.items = result.items
        model.total = result.total
        render( template:template, model:model )

    }

    private Map get( Long id ) {

        if ( id == null ) {
            notifyCrack()
            return null
        }
        def secureUserSecureRole = secureUserSecureRoleService.get( id )
        if ( !secureUserSecureRole ) {
            notifyCrack()
            return null
        }
        [ secureUserSecureRoleInstance:secureUserSecureRole ]

    }

    private void saveOnDb( secureUserSecureRole, method, edit = false ) {

        try {
            secureUserSecureRoleService."${method}"( secureUserSecureRole )
        } catch ( IllegalArgumentException e ) {
            response.status = 400
            render( template:'form', model:[ secureUserSecureRoleInstance:secureUserSecureRole,
                edit:edit ] )
            return
        }
        flash.formMessage = message(
            code:"default.${edit?'updated':'created'}.message",
            args:[ message( code:'secureUserSecureRole.label',
            default:'SecureUserSecureRole' ), secureUserSecureRole.id])
        redirect( action:'edit', id:secureUserSecureRole.id )

    }

    private void notifyCrack() {

        crackingService.notify( request, params )
        redirect( controller:'logout' )

    }

}