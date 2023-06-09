package grails5.app

class UrlMappings {

    static excludes = ["/images/*", "/css/*"]
    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/accessibility"(view:"/accessibilityFeatures")
        "/viewModel"(view:"/zkMVVM")
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
