package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(TestCase)
class TestCaseNameConstraintsSpec extends Specification {

    def setup() {
        mockForConstraintsTests( TestCase, [ new TestCase() ] )
    }

    def "test 'maxSize' constraint"() {

        when:
            def instance = new TestCase( name:name )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'name' ] != null
            instance.errors[ 'name' ] == 'maxSize'
        where:
            name = 'A' * 501

    }

    def "test 'nullable' constraint"() {

        when:
            def instance = new TestCase( name:name )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'name' ] != null
            instance.errors[ 'name' ] == 'nullable'
        where:
            name = null

    }

}