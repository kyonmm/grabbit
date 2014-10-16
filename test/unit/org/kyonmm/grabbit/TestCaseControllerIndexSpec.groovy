package org.kyonmm.grabbit

import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(TestCaseController)
@Mock([TestCase, Tag])
class TestCaseControllerIndexSpec extends Specification {

    def "test ok"() {

        when:
            def control = mockTestCaseService()
            request.method = 'GET'
            controller.index()
            control.verify()
        then:
            response.status == 200

    }

    def "test ok with params"() {

        when:
            def control = mockTestCaseService()
            request.method = 'GET'
            params.name = 'value'
            controller.index()
            control.verify()
        then:
            response.status == 200

    }

    @Ignore( 'See http://jira.grails.org/browse/GRAILS-8426' )
    def "test request method invalid"() {

        when:
            request.method = 'POST'
            controller.index()
        then:
            response.status == 405

    }
    private GrailsMock mockTestCaseService() {

        def control = mockFor( TestCaseService )
        control.demand.list { params ->
            [list:TestCase.list(), total:TestCase.list().size()]
        }
        controller.testCaseService = control.createMock()
        control

    }

}