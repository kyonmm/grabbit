package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(Tag)
class TagDescriptionConstraintsSpec extends Specification {

    def setup() {
        mockForConstraintsTests( Tag, [ new Tag() ] )
    }

    def "test 'nullable' constraint"() {

        when:
            def instance = new Tag( description:description )
            def result = instance.validate()
        then:
            result == false
            instance.errors[ 'description' ] != null
            instance.errors[ 'description' ] == 'nullable'
        where:
            description = null

    }

}