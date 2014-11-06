class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(redirect: "/home/index")
        "/index"(redirect: "/home/index")
        "500"(view: '/error')
    }
}
