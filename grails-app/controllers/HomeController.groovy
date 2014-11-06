import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class HomeController {

    def index() {
        render(template: 'index', layout: 'main')
    }// End of method

}// End of class
