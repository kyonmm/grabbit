package org.kyonmm.grabbit

import javax.servlet.http.HttpServletRequest
import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(TagController)
@Mock(Tag)
class TagControllerUpdateSpec extends Specification {

    def setup() {

        TagMock.mock( 0 ).save( failOnError:true )
        views[ '/tag/_form.gsp' ] = getTemplate()

    }

    def "test ok"() {

        when:
            def control = mockTagService()
            request.method = 'POST'
            setUpParams()
            controller.update( 1 )
            control.verify()
        then:
            flash.formMessage == 'default.updated.message'
            response.redirectedUrl == "/tag/edit/${1}"
            response.status == 302

    }

    def "test id null"() {

        when:
            def control = mockCrackingService()
            request.method = 'GET'
            controller.update( null )
            control.verify()
        then:
            response.redirectedUrl == '/logout'
            response.status == 302

    }

    def "test not found"() {

        when:
            def control = mockTagService( true, 0 )
            def control2 = mockCrackingService()
            request.method = 'GET'
            controller.update( 2 )
            control.verify()
            control2.verify()
        then:
            response.redirectedUrl == '/logout'
            response.status == 302

    }

    def "test params invalid"() {

        when:
            def control = mockTagService( false )
            request.method = 'POST'
            setUpParams()
            params.description = null
            controller.update( 1 )
            control.verify()
        then:
            response.text == 'OK'
            response.status == 400

    }

    @Ignore( 'See http://jira.grails.org/browse/GRAILS-8426' )
    def "test request method invalid"() {

        when:
            request.method = 'GET'
            controller.update( 1 )
        then:
            response.status == 405

    }

    private String getTemplate() {
        '<g:if test="${tagInstance && edit}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockTagService( update = true, updateTimes = 1 ) {

        def control = mockFor( TagService )
        control.demand.get( 1 ) { Long id ->
            Tag.findById( id )
        }
        control.demand.update( updateTimes ) { Tag instance ->
            if ( update ) {
                instance.save( failOnError:true )
            } else throw new IllegalArgumentException( 'error' )
        }
        controller.tagService = control.createMock()
        control

    }

    private GrailsMock mockCrackingService() {

        def control = mockFor( CrackingService )
        control.demand.notify( 1 ) { HttpServletRequest request, Map params -> }
        controller.crackingService = control.createMock()
        control

    }

    private void setUpParams() {

        def mock = TagMock.mock( 0 )
        mock.properties.each{ params."${it.key}" = it.value }

    }

}