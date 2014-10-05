package org.kyonmm.grabbit

import grails.test.GrailsMock
import grails.test.mixin.*
import spock.lang.*

@TestFor(TagController)
@Mock(Tag)
class TagControllerSaveSpec extends Specification {

    def setup() {
        views[ '/tag/_form.gsp' ] = getTemplate()
    }

    def "test ok"() {

        when:
            def control = mockTagService()
            request.method = 'POST'
            setUpParams()
            controller.save()
            control.verify()
        then:
            flash.formMessage == 'default.created.message'
            response.redirectedUrl == "/tag/edit/1"
            response.status == 302

    }

    def "test params invalid"() {

        when:
            def control = mockTagService( false )
            request.method = 'POST'
            setUpParams()
            params.description = null
            controller.save()
            control.verify()
        then:
            response.text == 'OK'
            response.status == 400

    }

    @Ignore( 'See http://jira.grails.org/browse/GRAILS-8426' )
    def "test request method invalid"() {

        when:
            request.method = 'GET'
            controller.save()
        then:
            response.status == 405

    }

    private String getTemplate() {
        '<g:if test="${tagInstance}">OK</g:if><g:else>ERROR</g:else>'
    }

    private GrailsMock mockTagService( save = true ) {

        def control = mockFor( TagService )
        control.demand.create( 1 ) { Tag instance ->
            if ( save ) {
                instance.id = 1
                instance.save( failOnError:true )
            } else throw new IllegalArgumentException( 'error' )
        }
        controller.tagService = control.createMock()
        control

    }

    private void setUpParams() {

        def mock = TagMock.mock( 0 )
        mock.properties.each{ params."${it.key}" = it.value }

    }

}