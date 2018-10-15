/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.generator;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.simplej.generator.LineUtils.*;

/**
 * Transform a SimpleJ file to target file, whatever language it is
 */
public interface SimpleJTransformer extends Function<List<String>, List<String>> {

    @Override
    default List<String> apply(List<String> lines) {
        final List<String> transformedLines = new ArrayList<>();
        final Iterator<String> linesIterator = lines.iterator();
        try {
            // 1 : imports
            nextCodeLine(linesIterator, transformedLines);

            // x : End of file
        } catch (EOFException e) {
            e.printStackTrace();
        }

        // 1 imports
        return transformedLines;
}
}
