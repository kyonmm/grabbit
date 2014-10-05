package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(Tag)
class TagNameConstraintsSpec extends Specification {

    def setup() {
        mockForConstraintsTests( Tag, [ new Tag() ] )
    }

    def "test 'nullable' constraint"() {

        when:
            def instance = new Tag( name:name )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'name' ] != null
            instance.errors[ 'name' ] == 'nullable'
        where:
            name = null

    }

}