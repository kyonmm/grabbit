package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(TestCase)
class TestCaseTagsConstraintsSpec extends Specification {

    def setup() {
        mockForConstraintsTests( TestCase, [ new TestCase() ] )
    }

    def "test 'nullable' constraint"() {

        when:
            def instance = new TestCase( tags:tags )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'tags' ] == null
        where:
            tags = null

    }

}