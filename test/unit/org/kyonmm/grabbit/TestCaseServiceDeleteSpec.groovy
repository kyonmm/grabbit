package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(TestCaseService)
@Mock(TestCase)
class TestCaseServiceDeleteSpec extends Specification {

    def setup() {
        TestCaseMock.mock( 0 ).save( failOnError:true )
    }

    def " test ok"() {

        when:
            def instance = service.get( id )
            service.delete( instance )
        then:
            TestCase.count() == 0
        where:
            id = 1

    }

    void "test TestCase null"() {

        when:
            service.delete( testCase )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'testCase' is null"
        where:
            testCase = null

    }

    def " test invalid"() {

        when:
            def instance = new TestCase()
            service.delete( instance )
        then:
            TestCase.exists( instance.id ) == false

    }

}