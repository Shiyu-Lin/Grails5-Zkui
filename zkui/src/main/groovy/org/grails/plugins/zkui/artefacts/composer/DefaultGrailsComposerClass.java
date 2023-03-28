package org.grails.plugins.zkui.artefacts.composer;

import org.grails.core.AbstractInjectableGrailsClass;

public class DefaultGrailsComposerClass extends AbstractInjectableGrailsClass
        implements GrailsComposerClass {

    public static final String COMPOSER = "Composer";

    @SuppressWarnings("unchecked")
    public DefaultGrailsComposerClass(Class clazz) {
        super(clazz, COMPOSER);
    }
}