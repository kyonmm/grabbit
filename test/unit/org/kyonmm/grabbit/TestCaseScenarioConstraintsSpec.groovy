package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(TestCase)
class TestCaseScenarioConstraintsSpec extends Specification {

    def setup() {
        mockForConstraintsTests( TestCase, [ new TestCase() ] )
    }

    def "test 'maxSize' constraint"() {

        when:
            def instance = new TestCase( scenario:scenario )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'scenario' ] != null
            instance.errors[ 'scenario' ] == 'maxSize'
        where:
            scenario = 'A' * 1001

    }

    def "test 'nullable' constraint"() {

        when:
            def instance = new TestCase( scenario:scenario )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'scenario' ] != null
            instance.errors[ 'scenario' ] == 'nullable'
        where:
            scenario = null

    }

}