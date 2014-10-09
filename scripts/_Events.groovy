import org.apache.catalina.connector.Connector

eventConfigureTomcat ={tomcat ->
    def c = new Connector(protocol: "HTTP/1.1", port: 8080, URIEncoding: "utf-8", redirectPort: 8443)
    tomcat.service.addConnector(c)
}
