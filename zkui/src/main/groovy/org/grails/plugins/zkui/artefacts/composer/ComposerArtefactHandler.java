package org.grails.plugins.zkui.artefacts.composer;

import grails.core.ArtefactHandlerAdapter;
import org.grails.core.artefact.DomainClassArtefactHandler;


public class ComposerArtefactHandler extends ArtefactHandlerAdapter {

    public static final String TYPE = "Composer";

    public ComposerArtefactHandler() {
        super(TYPE, GrailsComposerClass.class,
                DefaultGrailsComposerClass.class,
                DefaultGrailsComposerClass.COMPOSER,
                false);
    }

    @SuppressWarnings("unchecked")
    public boolean isArtefactClass(Class clazz) {
        return super.isArtefactClass(clazz) &&
                !DomainClassArtefactHandler.isDomainClass(clazz);
    }
}
