package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(TagService)
@Mock(Tag)
class TagServiceDeleteSpec extends Specification {

    def setup() {
        TagMock.mock( 0 ).save( failOnError:true )
    }

    def " test ok"() {

        when:
            def instance = service.get( id )
            service.delete( instance )
        then:
            Tag.count() == 0
        where:
            id = 1

    }

    void "test Tag null"() {

        when:
            service.delete( tag )
        then:
            IllegalArgumentException e = thrown()
            e.message == "Parameter 'tag' is null"
        where:
            tag = null

    }

    def " test invalid"() {

        when:
            def instance = new Tag()
            service.delete( instance )
        then:
            Tag.exists( instance.id ) == false

    }

}