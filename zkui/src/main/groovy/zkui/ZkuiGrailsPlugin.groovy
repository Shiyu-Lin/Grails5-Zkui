package zkui

import grails.plugins.*
import org.grails.gsp.GroovyPage
import org.grails.plugins.zkui.ComposerHandler
import org.grails.plugins.zkui.WebManagerInit
import org.grails.plugins.zkui.ZkComponentBuilder
import org.grails.plugins.zkui.ZkuiGrailsOpenSessionInViewFilter
import org.grails.plugins.zkui.artefacts.composer.ComposerArtefactHandler
import org.grails.plugins.zkui.artefacts.vm.ViewModelArtefactHandler
import org.grails.plugins.zkui.composer.BindComposer
import org.grails.plugins.zkui.metaclass.RedirectDynamicMethod
import org.grails.plugins.zkui.util.ComponentErrorRendererUtil
import org.grails.plugins.zkui.util.UriUtil
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.Ordered
import org.springframework.web.context.request.RequestContextHolder as RCH
import org.zkoss.lang.Library
import org.zkoss.zk.au.http.DHtmlResourceServlet
import org.zkoss.zk.au.http.DHtmlUpdateServlet
import org.zkoss.zk.ui.Component
import org.zkoss.zk.ui.Execution
import org.zkoss.zk.ui.Executions
import org.zkoss.zk.ui.Page
import org.zkoss.zk.ui.Session
import org.zkoss.zk.ui.http.DHtmlLayoutServlet
import org.zkoss.zk.ui.http.HttpSessionListener
import org.zkoss.zk.ui.select.Selectors
import org.zkoss.zul.Checkbox
import org.zkoss.zul.Combobox
import org.zkoss.zul.Listbox
import org.zkoss.zul.Messagebox
import org.zkoss.zul.Radiogroup
import org.zkoss.zul.impl.InputElement
import org.grails.core.artefact.DomainClassArtefactHandler
import grails.util.GrailsClassUtils
//import org.codehaus.groovy.grails.web.metaclass.BindDynamicMethod
//import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.grails.taglib.TagLibraryLookup
import grails.util.TypeConvertingMap

class ZkuiGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "5.3.2 > *"

    def profiles = ['web']

    def loadAfter = ['core', 'hibernate', 'controllers']


    def watchedResources = [
            "file:./grails-app/composers/**/*Composer.groovy",
            "file:./plugins/*/grails-app/composers/**/*Composer.groovy",
            "file:./grails-app/vms/**/*VM.groovy",
            "file:./plugins/*/grails-app/vms/**/*VM.groovy"
    ]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Zkui" // Headline display name of the plugin
    def author = ""
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''


    // URL to the plugin's documentation
//    def documentation = "http://grails.org/plugin/zkui"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]


    @Override
    Closure doWithSpring(){
        {->
            webManagerInit(WebManagerInit)
            auEngine(ServletRegistrationBean, new DHtmlUpdateServlet(), "/zkau/*")
//            zkLoader(ServletRegistrationBean, new DHtmlLayoutServlet(), "*.gsp"){
//                initParameters = [ "update-uri": "/zkau", "compress": "false" ]
//                loadOnStartup = 0
//            }
            if(manager?.hasGrailsPlugin("hibernate")){
                GOSIVFilter(FilterRegistrationBean){
                    filter = bean(ZkuiGrailsOpenSessionInViewFilter)
                    urlPatterns = ["/zkau"]
                }
            }
            composerHandler(ComposerHandler){ bean ->
                bean.scope = "prototype"
            }
            zkComponentBuilder(ZkComponentBuilder){ bean ->
                bean.scope = "prototype"
            }
            "org.zkoss.bind.BindComposer"(BindComposer){ bean ->
                bean.scope = "prototype"
            }

            // Listener
//            ZkSessionCleaner(ServletListenerRegistrationBean) {
//                listener = bean(HttpSessionListener)
//                order = Ordered.HIGHEST_PRECEDENCE
//            }

            // Registering Composer Beans
            grailsApplication.composerClasses.each { composerClass ->
                "${composerClass.clazz.name}"(composerClass.clazz) { bean ->
                    bean.scope = "prototype"
                    bean.autowire = "byName"
                }
            }
            // Registering ViewModel Beans for supporting MVVM
            grailsApplication.viewModelClasses.each { viewModelClass ->
                "${viewModelClass.clazz.name}"(viewModelClass.clazz) { bean ->
                    bean.scope = "prototype"
                    bean.autowire = "byName"
                }
            }

        }
    }

    @Override
    void doWithDynamicMethods(){

        ConfigurableApplicationContext ctx = getApplicationContext()

        //Inject taglib namespace to Composer
        TagLibraryLookup gspTagLibraryLookup = ctx.getBean("gspTagLibraryLookup")
        CharSequence.metaClass.fixToZkUri = { String contextPath ->
            return UriUtil.fixToZk(delegate?.toString(), contextPath)
        }

        Component.metaClass.appendChild = { Closure closure ->
            def builder = ctx.getBean('zkComponentBuilder')
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            builder.build(delegate, closure)
        }
        Component.metaClass.leftShift = { Object value ->
            delegate.appendChild(value)
        }
        Component.metaClass.select = { String query ->
            return Selectors.find((Component) delegate, query)
        }
        Page.metaClass.select = { String query ->
            return Selectors.find((Page) delegate, query)
        }
        Component.metaClass.addEventListener = { String eventName, Closure listenerClosure ->
            return delegate.addEventListener(eventName, listenerClosure as org.zkoss.zk.ui.event.EventListener)
        }
        Component.metaClass.getParams = {
            return delegate.select("*").inject([:]) { s, c ->
                if (!c.metaClass.respondsTo(c, 'getName')) return s
                if (c.name == null) return s
                def e = s.get(c.name)
                def value
                if (c instanceof Combobox) {
                    value = c.selectedItem?.value
                } else if (c instanceof Checkbox) {
                    value = c.value ?: c.isChecked()
                } else if (c instanceof Listbox) {
                    value = c.getSelectedItems()?.value
                } else if (c instanceof Radiogroup) {
                    return s
                } else if (c.metaClass.respondsTo(c, 'getValue')) {
                    value = c.value
                } else {
                    return s
                }
                if (value == null) {
                    value = ''
                }
                if (e == null) {
                    s.put(c.name, value)
                } else if (e instanceof Collection) {
                    e << value
                } else {
                    s.put(c.name, [s.remove(c.name), value])
                }
                return s
            }.inject(new TypeConvertingMap()) { s, e ->
                if (e.value instanceof Collection) {
                    s.put(e.key, e.value as String[])
                } else {
                    s.put(e.key, e.value)
                }
                return s
            }
        }
        def gDispatcher = gspTagLibraryLookup.lookupNamespaceDispatcher(GroovyPage.DEFAULT_NAMESPACE)
        Component.metaClass.renderErrors = { Map args ->
            if (!args.bean) {
                throw new IllegalArgumentException("[bean] attribute must be specified!")
            }
            if (!grailsApplication.isArtefactOfType(DomainClassArtefactHandler.TYPE, args.bean.class)) {
                throw new IllegalArgumentException("[bean] attribute must be Domain class!")
            }
            def domainClass = grailsApplication.getDomainClass(args.bean.class.name)
            args.bean.errors.fieldErrors.each {
                def p = domainClass.getPropertyByName(it.field)
                def name
                if (p.manyToOne || p.oneToOne) {
                    name = "${p.referencedDomainClass.propertyName}.id"
                } else {
                    name = it.field
                }
                def selectedComponentList = delegate.select("[name='${name}']")
                String errorMessage = gDispatcher.message(error: it)
                if (selectedComponentList.size() > 0 && selectedComponentList[0] instanceof InputElement) {
                    selectedComponentList[0].setErrorMessage(errorMessage)
                } else {
                    //todo the remaining errorMessage
                }
            }
        }

        ComponentErrorRendererUtil errorRendererUtil = new ComponentErrorRendererUtil()

        errorRendererUtil.addRenderMapAsErrors()

        Session.metaClass.getAt = { String name ->
            delegate.getAttribute(name)
        }

        Session.metaClass.putAt = { String name, value ->
            delegate.setAttribute(name, value)
        }

        Execution.metaClass.getAt = { String name ->
            delegate.getAttribute(name)
        }

        Execution.metaClass.putAt = { String name, value ->
            delegate.setAttribute(name, value)
        }

        Messagebox.metaClass.static.show = { int messageCode, int titleCode, int buttons, java.lang.String icon, int focus, Closure listener ->
            Messagebox.show(messageCode, titleCode, buttons, icon, focus, listener as org.zkoss.zk.ui.event.EventListener)
        }
        Messagebox.metaClass.static.show = { int messageCode, java.lang.Object[] args, int titleCode, int buttons, String icon, int focus, Closure listener ->
            Messagebox.show(messageCode, args, titleCode, buttons, icon, focus, listener as org.zkoss.zk.ui.event.EventListener)
        }
        Messagebox.metaClass.static.show = { int messageCode, java.lang.Object arg, int titleCode, int buttons, java.lang.String icon, int focus, Closure listener ->
            Messagebox.show(messageCode, arg, titleCode, buttons, icon, focus, listener as org.zkoss.zk.ui.event.EventListener)
        }
        Messagebox.metaClass.static.show = { String message, java.lang.String title, int buttons, String icon, Closure listener ->
            Messagebox.show(message, title, buttons, icon, listener as org.zkoss.zk.ui.event.EventListener)
        }
        Messagebox.metaClass.static.show = { String message, String title, int buttons, String icon, int focus, Closure listener ->
            Messagebox.show(message, title, buttons, icon, focus, listener as org.zkoss.zk.ui.event.EventListener)
        }

        def redirect = new RedirectDynamicMethod(ctx)
        def redirectObject = { Map args ->
            redirect.invoke(delegate, "redirect", args)
        }
//        def bind = new BindDynamicMethod()
        def paramsObject = {-> RCH.currentRequestAttributes().params }
        def flashObject = {-> RCH.currentRequestAttributes().flashScope }
        def executionObject = {-> Executions.current }
        def sessionObject = {-> Executions.current.session }
        if (manager?.hasGrailsPlugin("controllers")) {
            for (namespace in gspTagLibraryLookup.availableNamespaces) {
                def propName = GrailsClassUtils.getGetterName(namespace)
                def namespaceDispatcher = gspTagLibraryLookup.lookupNamespaceDispatcher(namespace)
                def composerClasses = grailsApplication.composerClasses*.clazz
                for (Class composerClass in (composerClasses as List<Class>)) {
                    MetaClass mc = composerClass.metaClass
                    if (!mc.getMetaProperty(namespace)) {
                        mc."$propName" = { namespaceDispatcher }
                    }
                }
            }
            def composerClasses = grailsApplication.composerClasses*.clazz
            for (Class composerClass in (composerClasses as List<Class>)) {
                MetaClass mc = composerClass.metaClass
                mc.redirect = redirectObject
                mc.getSession = sessionObject
                mc.getExecution = executionObject
                mc.getParams = paramsObject
                // the flash object
                mc.getFlash = flashObject
                // the bindData method
                mc.bindData = { Object target, Object args ->
                    bind.invoke(delegate, BindDynamicMethod.METHOD_SIGNATURE, [target, args] as Object[])
                }
                mc.bindData = { Object target, Object args, List disallowed ->
                    bind.invoke(delegate, BindDynamicMethod.METHOD_SIGNATURE, [target, args, [exclude: disallowed]] as Object[])
                }
                mc.bindData = { Object target, Object args, List disallowed, String filter ->
                    bind.invoke(delegate, BindDynamicMethod.METHOD_SIGNATURE, [target, args, [exclude: disallowed], filter] as Object[])
                }
                mc.bindData = { Object target, Object args, Map includeExclude ->
                    bind.invoke(delegate, BindDynamicMethod.METHOD_SIGNATURE, [target, args, includeExclude] as Object[])
                }
                mc.bindData = { Object target, Object args, Map includeExclude, String filter ->
                    bind.invoke(delegate, BindDynamicMethod.METHOD_SIGNATURE, [target, args, includeExclude, filter] as Object[])
                }
                mc.bindData = { Object target, Object args, String filter ->
                    bind.invoke(delegate, BindDynamicMethod.METHOD_SIGNATURE, [target, args, filter] as Object[])
                }
            }
        }
    }

    @Override
    void doWithApplicationContext(){
        Library.setProperty("org.zkoss.web.servlet.http.URLEncoder", "org.grails.plugins.zkui.encodes.URLEncoder")
    }



    @Override
    void onChange(Map<String, Object> event){
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
        def artefactType = null
        if (grailsApplication.isArtefactOfType(ComposerArtefactHandler.TYPE, event.source.metaClass.class)) {
            artefactType = ComposerArtefactHandler.TYPE
        } else if (grailsApplication.isArtefactOfType(ViewModelArtefactHandler.TYPE, event.source.metaClass.class)) {
            artefactType = ViewModelArtefactHandler.TYPE
        }
        if (artefactType) {
            def context = event.ctx
            if (!context) {
                println("Application context not found. Can't reload")
                return
            }
            def artefactClass = grailsApplication.addArtefact(artefactType, event.source.metaClass.class)
            def artefactBeanName = artefactClass.clazz.name

            beans {
                "${artefactBeanName}"(artefactClass.clazz) { bean ->
                    bean.scope = "prototype"
                    bean.autowire = "byName"
                }
            }
//            beans.registerBeans(event.ctx)
        }

//        event.manager?.getGrailsPlugin("zkui")?.doWithDynamicMethods(event.ctx)
    }

    @Override
    void onShutdown(Map<String, Object> event){
        // do nothing
    }
}
