package org.kyonmm.grabbit

import grails.test.mixin.*
import spock.lang.*

@TestFor(TagService)
@Mock(Tag)
class TagServiceListOffsetSpec extends Specification {

    def setup() {

        20.times {
            TagMock.mock( it ).save( failOnError:true )
        }

    }

    def "test ok"() {

        when:
            def result = service.list( params )
        then:
            result.items.size() == 5
        where:
            params = [ offset:'15' ]

    }

    def "test null"() {

        when:
            def result = service.list( params )
        then:
            result.items.size() == 10
        where:
            params = [ offset:null ]

    }

    def "test blank"() {

        when:
            def result = service.list( params )
        then:
            result.items.size() == 10
        where:
            params = [ offset:'' ]

    }

    def "test invalid"() {

        when:
            def result = service.list( params )
        then:
            result.items.size() == 10
        where:
            params = [ offset:'A' ]

    }

}