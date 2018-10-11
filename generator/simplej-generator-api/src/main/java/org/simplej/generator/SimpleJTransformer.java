/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.generator;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public interface SimpleJTransformer extends Function<List<String>, List<String>> {

    @Override
    default List<String> apply(List<String> lines) {
        final List<String> transformedLines = new ArrayList<>();
        final Iterator<String> linesIterator = lines.iterator();
        try {
            String line = handleEmptyLines(linesIterator, transformedLines);
        } catch (EOFException e) {
            e.printStackTrace();
        }

        // 1 imports
        return transformedLines;
}

    /**
     * Empty lines or commented lines are exactly the same in transformed line.
     * They do not need to be transformed
     *
     * @param linesIterator input lines
     * @param transformedLines output lines
     * @return first input line with code
     */
    default String handleEmptyLines(Iterator<String> linesIterator, List<String> transformedLines) throws EOFException {
        String line;
        String trimmedLine;
        while (linesIterator.hasNext()) {
            line = linesIterator.next();
            trimmedLine = line.trim();

            if (trimmedLine.isEmpty() || trimmedLine.startsWith("//")) {
                // add line as it is to transformedLines
                transformedLines.add(line);
                // recursive call
                return handleEmptyLines(linesIterator, transformedLines);
            } else if (trimmedLine.startsWith("/*")) {
                // iterate until end of commented lines
                while (!trimmedLine.contains("*/")) {

                }
                // add line as it is to transformedLines
                transformedLines.add(line);
            }
        }
        // if end of file : throw EOF
        throw new EOFException();
    }
}
