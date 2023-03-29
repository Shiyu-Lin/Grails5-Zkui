package org.grails.plugins.zkui

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

import javax.servlet.FilterRegistration
import javax.servlet.ServletContext
import org.springframework.web.context.ServletContextAware
import org.zkoss.lang.Library
import org.zkoss.zk.ui.http.WebManager

import javax.servlet.ServletRegistration

class WebManagerInit implements ServletContextAware, ApplicationContextAware{
    void setServletContext(ServletContext servletContext) {
        final WebManager webman = WebManager.getWebManagerIfAny(servletContext)
        if (webman == null) {
            final String ATTR = "org.zkoss.zkplus.embed.updateURI"
            String updateURI = Library.getProperty(ATTR)
            if (updateURI == null)
                updateURI = "/zkau"
            else
                updateURI = org.zkoss.zk.ui.http.Utils.checkUpdateURI(updateURI, ATTR)
            WebManager webmanCreated = new WebManager(servletContext, updateURI)



            // Logging zk WebManager just to confirm it is indeed initialized
            println(webmanCreated)
            println(WebManager.getWebManagerIfAny(servletContext))

            // Logging all registered servlets
            println("Registered Servlets: ")
            for(Map.Entry<String, ServletRegistration> entry : servletContext.getServletRegistrations().entrySet()){
                println(entry.getKey() + "======" + entry.getValue())
            }
            println()

            // Logging all registered filters
            println("Registered Filters: ")
            for(Map.Entry<String, FilterRegistration> entry : servletContext.getFilterRegistrations().entrySet()){
                println(entry.getKey() + "======" + entry.getValue())
            }
            println()





        }
    }

    // Could also log all created beans that are managed by spring.
    // 1. make the class implements ApplicationContextAware
    // 2. in the implemented method, use applicationContext.getBeanDefinitionNames()
    // 3. there are way too many beans!!
    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        String[] beanNames = applicationContext.getBeanDefinitionNames()
        for(String s : beanNames){
            println(s)
        }

    }
}