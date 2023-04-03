# Grails5-Zkui

Code for the project is copied and adopted from: https://github.com/xiaochong/zkui/

## Introduction

ZK UI plugin,the same as the ZKGrails plugin, seamlessly integrates ZK with Grails' infrastructures. 

The difference is it uses the Grails' infrastructures more, such as gsp, controllers rather than zk's zul.

The goal of the project is to migrate the old zkui to newer Grails & ZK version (Grails 5.3.2 + ZK 9.6.3) since the repository on GitHub has not been touched in 10 years. Grails & ZK version used in the original
project is Grails 2.2.4 and ZK 6.5.4.

## Getting Started
### 1. Understanding the directory structure.
    The file structure for the project follows the Grails plugins and multi-project builds. It is as follows:
    PROJECT_DIR (Grails5-Zkui)
        - settings.gradle
        - myapp (grails5-app)
            - build.gradle
        - myplugin (zkui)
            - build.gradle
### 2. After cloning the repository
    If you want to use the provided Grails application template (grails5-app) as a starting point. 
    Change the repositories{...} field in build.gradle in grails5-app & zkui directory to point to public repositories to get required ZK packages.
    Currently they are pointing to McGill University's artifactory which is not publicly available. If everything is correctly setted up, you would
    see a page full of ZK components after you open up the page (localhost). Have fun!

    If you would like to create a new Grails application from scrach, copy "zkui" folder and put it into that project's directory. Then follow Step2&3 
    of the section "Plugins and Multi-Project Builds" of the first reference below. If everything is setted up correctly, you could run the Grails application
    and after you open up the page (localhost) under "Installed Plugins" there should be "zkui - 0.1". If you ran into issues, refer to trouble shooting or contact
    me for help! :)
### 3. After the application is running
    DOCTYPE
    The html pages generated by gsp must generate the doc type as follows:
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

    To start using ZK components in .gsp files, add the tag <z:resources/> to the head of the layout/main.gsp or any page that you want to use ZK UI Grails plug-ins.
    Then start throwing ZK components tags into the file, but they should begin with "<z:...".

### 4. Hello World example
    
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Hello World Demo</title>
        <z:resources/>
    </head>
    <body>
        <z:window title="My First ZK UI Application" border="normal">
            Hello World!
        </z:window>
    </body>
    </html>

## Plugin Configuration File 
    The core of the plugin is ZkuiGrailsPlugin.groovy file in zkui/src/main/groovy/zkui, it is quite hard to understand what exactly it is doing, but in "doWithSpring()"
    method you would see servlets that are required by ZK and some customized handlers are initialized.

## Trouble Shooting / Known Errors
    "Error initializing classpath: Project with path ':zkui' could not be found in root project 'myapp'". This error is caused by multiple settings.gradle files are present 
    in the project. To fix it, remove settings.gradle under "yourGrailsApplication". The project should only have one settings.gradle file (the one at root level, refer to "Understanding the directory structure"). 
    
    "Failed to invoke class org.zkoss.zkmax.init.WebAppInit" & "java.lang.IllegalStateException: Servlets cannot be added to context [] as the context has been initialised".
    The error is caused by "webManagerInit(WebManagerInit)" in ZkuiGrailsPlugin.groovy and is ultimately caused by "WebManagerInit.groovy" in zkui/src/main/groovy/org/grails/plugins/zkui 
    when it tries to initialize ZK's WebManager. The WebManager is successfully initialized, but it might invoked some other initialization process(my guess :)). However, this error seems 
    not to impact the functionality of the plugin and I could not get rid of it :( . If it ever causes the plugin to not work or you found a solution to resolve it, please let me know!
    
    "Execution failed for task ':zkui:jar'. > Unable to delete file '...\zkui\build\libs\zkui-0.1-plain.jar'". What I did was to remove "build" folder from zkui and rerun, this should resolve
    the issue.

    Something related to message code "....message code not found, etc.", seems to appear randomly, could not figure out why it happens yet.



## References
https://docs.grails.org/latest/guide/plugins.html