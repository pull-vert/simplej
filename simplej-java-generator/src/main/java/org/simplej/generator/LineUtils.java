package org.simplej.generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class LineUtils {

    private LineUtils() {
        throw new AssertionError("No org.simplej.generator.SimpleJToJavaTransformer instances for you!");
    }

    static List<String> cleanLines(Iterator<String> lines) {
        List<String> cleanedLines = new ArrayList<>();
        while (lines.hasNext()) {
            String line = lines.next();

            // remove /* xxx */ comments
            while (line.contains("/*")) {
                if (line.contains("*/")) {
                    line = line.substring(0, line.indexOf("/*")) + line.substring(line.indexOf("*/") + 2);
                }
            }
            // remove initial and final whitespaces
            line = line.trim();
            // add line only if not empty
            if (!line.isEmpty()) {
                cleanedLines.add(line);
            }
        }
        return cleanedLines;
    }
}
