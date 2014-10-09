package org.grails.plugins.tomcat

import org.apache.catalina.connector.Connector
import org.apache.catalina.startup.Tomcat

/**
 * Created by k.kobayashi on 2014/10/09.
 */
class ForkedTomcatCustomizer{
    void customize(Tomcat tomcat) {
        def c = new Connector(protocol: "HTTP/1.1", port: 8080, URIEncoding: "utf-8", redirectPort: 8443)
        tomcat.service.findConnectors().each {println "${it.protocol} ${it.URIEncoding}"}
        tomcat.service.addConnector(c)
    }

}
