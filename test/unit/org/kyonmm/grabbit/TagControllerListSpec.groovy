package org.kyonmm.grabbit

import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(TagController)
class TagControllerListSpec extends Specification {

    def setup() {
        views[ '/tag/_list.gsp' ] = getTemplate()
    }

    def "test ok"() {

        when:
            def control = mockTagService()
            request.method = 'GET'
            def model = controller.list()
        control.verify()

        then:
            response.text == 'OK'
            response.status == 200

    }

    @Ignore( 'See http://jira.grails.org/browse/GRAILS-8426' )
    def "test request method invalid"() {

        when:
            request.method = 'POST'
            controller.list()
        then:
            response.status == 405

    }

    private String getTemplate() {
        '<g:if test="${items && total}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockTagService() {

        def control = mockFor( TagService )
        control.demand.list( 1 ) { Map params ->
            [ items:[ new Tag() ], total:1 ] }
        controller.tagService = control.createMock()
        control

    }

}