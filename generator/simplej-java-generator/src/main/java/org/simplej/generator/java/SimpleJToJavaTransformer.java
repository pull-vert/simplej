/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.generator.java;

import org.simplej.generator.SimpleJTransformer;

/**
 * Singleton class that transform a SimpleJ file to Java file
 */
final class SimpleJToJavaTransformer implements SimpleJTransformer {

    // private constructor
    private SimpleJToJavaTransformer() {
    }

    /** Holder */
    private static class SimpleJToJavaTransformerHolder {
        /** Non preloaded instance */
        private static final SimpleJToJavaTransformer INSTANCE = new SimpleJToJavaTransformer();
    }

    /** Singleton unique access method */
    static SimpleJToJavaTransformer getInstance() {
        return SimpleJToJavaTransformerHolder.INSTANCE;
    }
}
