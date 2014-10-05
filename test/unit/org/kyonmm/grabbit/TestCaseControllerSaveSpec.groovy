package org.kyonmm.grabbit

import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(TestCaseController)
@Mock(TestCase)
class TestCaseControllerSaveSpec extends Specification {

    def setup() {
        views[ '/testCase/_form.gsp' ] = getTemplate()
    }

    def "test ok"() {

        when:
            def control = mockTestCaseService()
            request.method = 'POST'
            setUpParams()
            controller.save()
            control.verify()
        then:
            flash.formMessage == 'default.created.message'
            response.redirectedUrl == "/testCase/edit/1"
            response.status == 302

    }

    def "test params invalid"() {

        when:
            def control = mockTestCaseService( false )
            request.method = 'POST'
            setUpParams()
            params.name = null
            controller.save()
            control.verify()
        then:
            response.text == 'OK'
            response.status == 400

    }

    @Ignore( 'See http://jira.grails.org/browse/GRAILS-8426' )
    def "test request method invalid"() {

        when:
            request.method = 'GET'
            controller.save()
        then:
            response.status == 405

    }

    private String getTemplate() {
        '<g:if test="${testCaseInstance}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockTestCaseService( save = true ) {

        def control = mockFor( TestCaseService )
        control.demand.create( 1 ) { TestCase instance ->
            if ( save ) {
                instance.id = 1
                instance.save( failOnError:true )
            } else throw new IllegalArgumentException( 'error' )
        }
        controller.testCaseService = control.createMock()
        control

    }

    private void setUpParams() {

        def mock = TestCaseMock.mock( 0 )
        mock.properties.each{ params."${it.key}" = it.value }

    }

}