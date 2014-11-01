package org.kyonmm.grabbit

import grails.plugin.springsecurity.annotation.Secured
import org.grails.mandrill.MandrillMessage
import org.grails.mandrill.MandrillRecipient

@Secured(['IS_AUTHENTICATED_REMEMBERED', 'ROLE_ADMIN'])
class SecureUserController {

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

    def secureUserService
    def crackingService
    def mandrillService

    def index() {
        renderList( 'content' )
    }

    def content() {
        renderList( 'content' )
    }

    def list() {
        renderList( 'list' )
    }

    def create() {

        def model = [ secureUserInstance:new SecureUser( params ) ]
        render( template:'form', model:model )

    }

    def save() {

        def secureUser = new SecureUser( params )
        saveOnDb( secureUser, 'create' )

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
        map.secureUserInstance.properties = params
        map.edit = true
        saveOnDb( map.secureUserInstance, 'update', true )

    }

    def delete( Long id ) {

        def map = get( id )
        if ( !map ) return
        secureUserService.delete( map.secureUserInstance )
        flash.listMessage = message( code:'default.deleted.message',
            args:[ message( code:'secureUser.label',
            default:'SecureUser' ), id ] )
        redirect( action:'content' )

    }

    private void renderList( template ) {

        def model = [:]
        def result = secureUserService.list( params )
        model.items = result.items
        model.total = result.total
        render( template:template, model:model, layout: 'main' )

    }

    private Map get( Long id ) {

        if ( id == null ) {
            notifyCrack()
            return null
        }
        def secureUser = secureUserService.get( id )
        if ( !secureUser ) {
            notifyCrack()
            return null
        }
        [ secureUserInstance:secureUser ]

    }

    private void saveOnDb( secureUser, method, edit = false ) {

        try {
            secureUserService."${method}"( secureUser )
            def recpts = []
            recpts.add(new MandrillRecipient(name:secureUser.username, email:secureUser.email))
            def message = new MandrillMessage(
                    text:"Updated Your Account",
                    subject:"Grabbit News",
                    from_email:"kyon.mm@gmail.com",
                    to:recpts)
            message.tags.add("account edited")
            def ret = mandrillService.send(message)
            if(ret.any {!it.success}){
                response.status = 400
                render( template:'form', model:[ secureUserInstance:secureUser,
                                                 edit:edit ] )
                return
            }
        } catch ( IllegalArgumentException e ) {
            response.status = 400
            render( template:'form', model:[ secureUserInstance:secureUser,
                edit:edit ] )
            return
        }
        flash.formMessage = message(
            code:"default.${edit?'updated':'created'}.message",
            args:[ message( code:'secureUser.label',
            default:'SecureUser' ), secureUser.id])
        redirect( action:'edit', id:secureUser.id )

    }

    private void notifyCrack() {

        crackingService.notify( request, params )
        redirect( controller:'logout' )

    }

}