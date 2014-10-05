package org.kyonmm.grabbit

import javax.servlet.http.HttpServletRequest
import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(TestCaseController)
@Mock(TestCase)
class TestCaseControllerUpdateSpec extends Specification {

    def setup() {

        TestCaseMock.mock( 0 ).save( failOnError:true )
        views[ '/testCase/_form.gsp' ] = getTemplate()

    }

    def "test ok"() {

        when:
            def control = mockTestCaseService()
            request.method = 'POST'
            setUpParams()
            controller.update( 1 )
            control.verify()
        then:
            flash.formMessage == 'default.updated.message'
            response.redirectedUrl == "/testCase/edit/${1}"
            response.status == 302

    }

    def "test id null"() {

        when:
            def control = mockCrackingService()
            request.method = 'GET'
            controller.update( null )
            control.verify()
        then:
            response.redirectedUrl == '/logout'
            response.status == 302

    }

    def "test not found"() {

        when:
            def control = mockTestCaseService( true, 0 )
            def control2 = mockCrackingService()
            request.method = 'GET'
            controller.update( 2 )
            control.verify()
            control2.verify()
        then:
            response.redirectedUrl == '/logout'
            response.status == 302

    }

    def "test params invalid"() {

        when:
            def control = mockTestCaseService( false )
            request.method = 'POST'
            setUpParams()
            params.name = null
            controller.update( 1 )
            control.verify()
        then:
            response.text == 'OK'
            response.status == 400

    }

    @Ignore( 'See http://jira.grails.org/browse/GRAILS-8426' )
    def "test request method invalid"() {

        when:
            request.method = 'GET'
            controller.update( 1 )
        then:
            response.status == 405

    }

    private String getTemplate() {
        '<g:if test="${testCaseInstance && edit}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockTestCaseService( update = true, updateTimes = 1 ) {

        def control = mockFor( TestCaseService )
        control.demand.get( 1 ) { Long id ->
            TestCase.findById( id )
        }
        control.demand.update( updateTimes ) { TestCase instance ->
            if ( update ) {
                instance.save( failOnError:true )
            } else throw new IllegalArgumentException( 'error' )
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

    private void setUpParams() {

        def mock = TestCaseMock.mock( 0 )
        mock.properties.each{ params."${it.key}" = it.value }

    }

}