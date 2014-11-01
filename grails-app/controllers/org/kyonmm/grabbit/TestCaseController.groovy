package org.kyonmm.grabbit

import grails.plugin.springsecurity.annotation.Secured
import org.codehaus.groovy.grails.web.util.WebUtils
import org.grails.plugin.filterpane.FilterPaneUtils

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class TestCaseController {

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

    def testCaseService
    def crackingService
    def filterPaneService
    def filter = {
        if(!params.max) params.max = 10
        render( template: 'content',
                model:[ items: filterPaneService.filter( params, TestCase ),
                        total: filterPaneService.count( params, TestCase ),
                        filterParams: FilterPaneUtils.extractFilterParams(params),
                        params:params ],
                layout: 'main'
        )
    }
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

        def model = [ testCaseInstance:new TestCase( params ) ]
        render( template:'form', model:model )

    }

    def save() {

        def testCase = new TestCase( params )
        saveOnDb( testCase, 'create' )

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
        map.testCaseInstance.properties = params
        map.edit = true
        saveOnDb( map.testCaseInstance, 'update', true )

    }

    def delete( Long id ) {

        def map = get( id )
        if ( !map ) return
        testCaseService.delete( map.testCaseInstance )
        flash.listMessage = message( code:'default.deleted.message',
            args:[ message( code:'testCase.label',
            default:'TestCase' ), id ] )
        redirect( action:'content' )

    }

    private void renderList( template ) {

        def model = [:]
        def result = testCaseService.list( params )
        model.items = result.items
        model.total = result.total
        render( template:template, model:model, layout: 'main' )

    }

    private Map get( Long id ) {

        if ( id == null ) {
            notifyCrack()
            return null
        }
        def testCase = testCaseService.get( id )
        if ( !testCase ) {
            notifyCrack()
            return null
        }
        [ testCaseInstance:testCase ]

    }

    private void saveOnDb( testCase, method, edit = false ) {

        try {
            testCaseService."${method}"( testCase )
        } catch ( IllegalArgumentException e ) {
            response.status = 400
            render( template:'form', model:[ testCaseInstance:testCase,
                edit:edit ] )
            return
        }
        flash.formMessage = message(
            code:"default.${edit?'updated':'created'}.message",
            args:[ message( code:'testCase.label',
            default:'TestCase' ), testCase.id])
        redirect( action:'edit', id:testCase.id )

    }

    private void notifyCrack() {

        crackingService.notify( request, params )
        redirect( controller:'logout' )

    }

}