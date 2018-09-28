/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public final class SimpleJGenerator {

    private Logger LOGGER = LoggerFactory.getLogger(SimpleJGenerator.class);

    public SimpleJGenerator(List<String> classpath, File inputDir, File outputDir) throws IOException {
        // get all files from inputDir
        try (Stream<Path> paths = Files.walk(inputDir.toPath())) {
            paths
                    .filter(Files::isRegularFile) // filter only files not directories
                    .filter(path -> path.endsWith(".sj")) // filter only *.sj files
                    .map(path -> {
                        try {
                            return Files.readAllLines(path); // Path -> List<String>
                        } catch (IOException e) {
                            LOGGER.error("Error parsing file {}", path.toString());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull) // filter unparseable files
                    .map(SimpleJToJavaTransformer::transform) // transform SJ file to Java file
                    .forEach(java -> java.toString()); // todo save Java file
        }
    }
}
