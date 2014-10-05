package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(TagService)
@Mock(Tag)
class TagServiceCreateSpec extends Specification {

    def "test ok"() {

        when:
            def instance = TagMock.mock( 0 )
            service.create( instance )
        then:
            Tag.count() == 1

    }

    def "test Tag null"() {

        when:
            def instance = null
            service.create( instance )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'tag' is null"

    }

    def "test Tag invalid"() {

        when:
            def instance = TagMock.mock( 0 )
            instance.description = description
            service.create( instance )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'tag' is invalid"
        where:
            description = null

    }

}