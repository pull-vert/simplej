/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.generator;

import java.util.Iterator;
import java.util.List;

final class SimpleJToJavaTransformer {

    private SimpleJToJavaTransformer() {
        throw new AssertionError("No org.simplej.generator.SimpleJToJavaTransformer instances for you!");
    }

    /**
     * Transform lines from a SJ file to Java lines
     *
     * @param lines SJ lines
     */
    static List<String> transform(Iterator<String> lines) {
        // 1) clean lines (trim, remove comments, remove empty lines...)
        List<String> cleanedLines = LineUtils.cleanLines(lines);
        for (String line : cleanedLines) {
            // 1 imports
        }
        return null;
    }
}
