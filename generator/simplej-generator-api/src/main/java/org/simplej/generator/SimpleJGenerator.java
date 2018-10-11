/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.generator;

import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public interface SimpleJGenerator {

    default void generate(List<String> classpath, File inputDir, File outputDir) throws IOException {
        // get all files from inputDir
        try (Stream<Path> paths = Files.walk(inputDir.toPath())) {
            paths
                    .filter(Files::isRegularFile) // filter only files not directories
                    .filter(path -> path.endsWith(".sj")) // filter only *.sj files
                    .map(path -> {
                        try {
                            return Files.readAllLines(path); // Path -> List<String>
                        } catch (IOException e) {
                            getLogger().error("Error parsing file {}", path);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull) // filter unparseable or empty files
                    .map(getSimpleJTransformer()) // transform SJ file to target file
                    .forEach(System.out::println); // todo save file
        }
    }

    SimpleJTransformer getSimpleJTransformer();

    Logger getLogger();
}
