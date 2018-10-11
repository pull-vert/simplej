/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.generator.java;

import org.simplej.generator.SimpleJGenerator;
import org.simplej.generator.SimpleJTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SimpleJToJavaGenerator implements SimpleJGenerator {

    private Logger LOGGER = LoggerFactory.getLogger(SimpleJToJavaGenerator.class);

    @Override
    public SimpleJTransformer getSimpleJTransformer() {
        return SimpleJToJavaTransformer.getInstance();
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
