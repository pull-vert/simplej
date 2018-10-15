package org.simplej.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

class LineUtilsTest {

    @Test
    @DisplayName("nextCodeLine method : Simple code line 'interface it { }' is code")
    void nextCodeLineSingleLine() throws EOFException {
        // Given
        final String validLine = "interface it { }";
        Iterator<String> lines = Collections.singletonList(validLine).iterator();

        // when
        String firstCodeLine = LineUtils.nextCodeLine(lines, new ArrayList<>());

        // Then
        assertThat(firstCodeLine)
                .isEqualTo(validLine);
    }
}
