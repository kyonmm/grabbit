package org.kyonmm.grabbit

import javax.servlet.http.HttpServletRequest
import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(TestCaseController)
@Mock(TestCase)
class TestCaseControllerEditSpec extends Specification {

    def setup() {

        TestCaseMock.mock( 0 ).save( failOnError:true )
        views[ '/testCase/_form.gsp' ] = getTemplate()

    }

    def "test ok"() {

        when:
            def control = mockTestCaseService()
            request.method = 'GET'
            def model = controller.edit( 1 )
            control.verify()
        then:
            response.text == 'OK'
            response.status == 200

    }

    def "test id null"() {

        when:
            def control = mockCrackingService()
            request.method = 'GET'
            controller.edit( null )
            control.verify()
        then:
            response.redirectedUrl == '/logout'
            response.status == 302

    }

    def "test not found"() {

        when:
            def control = mockTestCaseService()
            def control2 = mockCrackingService()
            request.method = 'GET'
            controller.edit( 2 )
            control.verify()
            control2.verify()
        then:
            response.redirectedUrl == '/logout'
            response.status == 302

    }

    @Ignore( 'See http://jira.grails.org/browse/GRAILS-8426' )
    def "test request method invalid"() {

        when:
            request.method = 'POST'
            controller.edit( 2 )
        then:
            response.status == 405

    }

    private String getTemplate() {
        '<g:if test="${testCaseInstance}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockTestCaseService() {

        def control = mockFor( TestCaseService )
        control.demand.get( 1 ) { Long id ->
            TestCase.findById( id )
        }
        controller.testCaseService = control.createMock()
        control

    }

    private GrailsMock mockCrackingService() {

        def control = mockFor( CrackingService )
        control.demand.notify( 1 ) { HttpServletRequest request, Map params -> }
        controller.crackingService = control.createMock()
        control

    }

}