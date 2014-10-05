package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(TestCaseService)
@Mock(TestCase)
class TestCaseServiceListSpec extends Specification {

    def "test ok"() {

        when:
            def result = service.list( params )
        then:
            result.items != null
            result.total != null
        where:
            params = [:]

    }

}