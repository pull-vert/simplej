/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.generator;

import java.io.EOFException;
import java.util.Iterator;
import java.util.List;

class LineUtils {

    // prevent instanciation
    private LineUtils() {
        throw new AssertionError("No LinesUtil instances for you!");
    }

    /**
     * Empty lines or commented lines are exactly the same in transformed line.
     * They do not need to be transformed
     * Returns first input line with SimpleJ code
     *
     * @param linesIterator input lines
     * @param transformedLines output lines
     * @return first input line with SimpleJ code
     */
    static String nextCodeLine(Iterator<String> linesIterator, List<String> transformedLines) throws EOFException {
        String line = nextLineOrEOF(linesIterator);
        String trimmedLine = line.trim();

        // handle ' // xxxx ' comment
        if (trimmedLine.isEmpty() || trimmedLine.startsWith("//")) {
            // add line as it is to transformedLines
            transformedLines.add(line);
            // recursive call
            return nextCodeLine(linesIterator, transformedLines);
        } else if (trimmedLine.startsWith("/*")) {
            // iterate until end of commented lines
            while (!trimmedLine.contains("*/")) {
                // add line as it is to transformedLines
                transformedLines.add(line);
                line = nextLineOrEOF(linesIterator);
                trimmedLine = line.trim();
            }
            // detect line like : ' xxx */     /* xxxxxx ' : must be handled as a full commented line
            String afterEndOfCommentTrimmed = trimmedLine.substring(trimmedLine.indexOf("*/") + 2).trim();
            if (afterEndOfCommentTrimmed.isEmpty() || afterEndOfCommentTrimmed.startsWith("/*")) {
                // add line as it is to transformedLines
                transformedLines.add(line);
                // recursive call
                return nextCodeLine(linesIterator, transformedLines);
            }
        }
        // line with code -> return
        return line;
    }

    private static String nextLineOrEOF(Iterator<String> linesIterator) throws EOFException {
        if (linesIterator.hasNext()) {
            return linesIterator.next();
        }
        // end of file : throw EOF
        throw new EOFException();
    }
}
