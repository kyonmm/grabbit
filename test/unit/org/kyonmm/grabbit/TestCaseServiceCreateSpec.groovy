package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(TestCaseService)
@Mock(TestCase)
class TestCaseServiceCreateSpec extends Specification {

    def "test ok"() {

        when:
            def instance = TestCaseMock.mock( 0 )
            service.create( instance )
        then:
            TestCase.count() == 1

    }

    def "test TestCase null"() {

        when:
            def instance = null
            service.create( instance )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'testCase' is null"

    }

    def "test TestCase invalid"() {

        when:
            def instance = TestCaseMock.mock( 0 )
            instance.name = name
            service.create( instance )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'testCase' is invalid"
        where:
            name = null

    }

}