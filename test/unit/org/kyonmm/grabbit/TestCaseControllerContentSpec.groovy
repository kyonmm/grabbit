package org.kyonmm.grabbit

import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(TestCaseController)
class TestCaseControllerContentSpec extends Specification {

    def setup() {
        views[ '/testCase/_content.gsp' ] = getTemplate()
    }

    def "test ok"() {

        when:
            def control = mockTestCaseService()
            request.method = 'GET'
            def model = controller.content()
            control.verify()

        then:
            response.text == 'OK'
            response.status == 200

    }

    @Ignore( 'See http://jira.grails.org/browse/GRAILS-8426' )
    def "test request method invalid"() {

        when:
            request.method = 'POST'
            controller.content()
        then:
            response.status == 405

    }

    private String getTemplate() {
        '<g:if test="${items && total}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockTestCaseService() {

        def control = mockFor( TestCaseService )
        control.demand.list( 1 ) { Map params ->
            [ items:[ new TestCase() ], total:1 ] }
        controller.testCaseService = control.createMock()
        control

    }

}