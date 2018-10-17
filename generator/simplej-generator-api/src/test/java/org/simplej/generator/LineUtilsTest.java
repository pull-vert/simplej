package org.simplej.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.EOFException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class LineUtilsTest {

    @Test
    @DisplayName("nextCodeLine method : Single code line 'interface it { }'")
    void nextCodeLine_SingleCodeLine() throws EOFException {
        // Given
        final String codeLine = "interface it { }";
        final Iterator<String> inputLines = Collections.singletonList(codeLine).iterator();
        final List<String> outputLines = new ArrayList<>();

        // when
        String firstCodeLine = LineUtils.nextCodeLine(inputLines, outputLines);

        // Then
        assertThat(firstCodeLine)
                .isEqualTo(codeLine);
        assertThat(outputLines)
                .isEmpty();
    }

    @Test
    @DisplayName("nextCodeLine method : One commented line '// comment' then code line 'interface it { }'")
    void nextCodeLine_DoubleSlashCommentThenCodeLine() throws EOFException {
        // Given
        final String commentedLine = "// comment";
        final String codeLine = "interface it { }";
        final Iterator<String> inputLines = Arrays.asList(commentedLine, codeLine).iterator();
        final List<String> outputLines = new ArrayList<>();

        // when
        String firstCodeLine = LineUtils.nextCodeLine(inputLines, outputLines);

        // Then
        assertThat(firstCodeLine)
                .isEqualTo(codeLine);
        assertThat(outputLines)
                .hasSize(1)
                .containsOnly(commentedLine);
    }

    @Test
    @DisplayName("nextCodeLine method : One commented line '/* comment */' then code line 'interface it { }'")
    void nextCodeLine_SlashStarCommentThenCodeLine() throws EOFException {
        // Given
        final String commentedLine = "/* comment */";
        final String codeLine = "interface it { }";
        final Iterator<String> inputLines = Arrays.asList(commentedLine, codeLine).iterator();
        final List<String> outputLines = new ArrayList<>();

        // when
        String firstCodeLine = LineUtils.nextCodeLine(inputLines, outputLines);

        // Then
        assertThat(firstCodeLine)
                .isEqualTo(codeLine);
        assertThat(outputLines)
                .hasSize(1)
                .containsOnly(commentedLine);
    }

    @Test
    @DisplayName("nextCodeLine method : One comment and code in same line '/* comment */ interface it { }'")
    void nextCodeLine_SlashStarCommentAndCodeLine() throws EOFException {
        // Given
        final String codeLine = "/* comment */ interface it { }";
        final Iterator<String> inputLines = Collections.singletonList(codeLine).iterator();
        final List<String> outputLines = new ArrayList<>();

        // when
        String firstCodeLine = LineUtils.nextCodeLine(inputLines, outputLines);

        // Then
        assertThat(firstCodeLine)
                .isEqualTo(codeLine);
        assertThat(outputLines)
                .isEmpty();
    }

    @Test
    @DisplayName("nextCodeLine method : One comment and code in same line 'interface it { } // comment'")
    void nextCodeLine_CodeLineAndDoubleSlashComment() throws EOFException {
        // Given
        final String codeLine = "interface it { } // comment";
        final Iterator<String> inputLines = Collections.singletonList(codeLine).iterator();
        final List<String> outputLines = new ArrayList<>();

        // when
        String firstCodeLine = LineUtils.nextCodeLine(inputLines, outputLines);

        // Then
        assertThat(firstCodeLine)
                .isEqualTo(codeLine);
        assertThat(outputLines)
                .isEmpty();
    }

    @Test
    @DisplayName("nextCodeLine method : Three commented line '// comment' then code line 'interface it { }'")
    void nextCodeLine_MultipleDoubleSlashCommentThenCodeLine() throws EOFException {
        // Given
        final String commentedLine = "// comment";
        final String codeLine = "interface it { }";
        final Iterator<String> inputLines = Arrays.asList(commentedLine, commentedLine, commentedLine, codeLine).iterator();
        final List<String> outputLines = new ArrayList<>();

        // when
        String firstCodeLine = LineUtils.nextCodeLine(inputLines, outputLines);

        // Then
        assertThat(firstCodeLine)
                .isEqualTo(codeLine);
        assertThat(outputLines)
                .hasSize(3)
                .containsOnly(commentedLine);
    }

    @Test
    @DisplayName("nextCodeLine method : 3 commented lines '/* comment ' 'comment' 'comment */' then code line 'interface it { }'")
    void nextCodeLine_MultilineSlashStarCommentThenCodeLine() throws EOFException {
        // Given
        final String commentedLineStart = "/* comment ";
        final String commentedLineMiddle = " comment ";
        final String commentedLineEnd = " comment */";
        final String codeLine = "interface it { }";
        final Iterator<String> inputLines = Arrays.asList(commentedLineStart, commentedLineMiddle, commentedLineEnd, codeLine).iterator();
        final List<String> outputLines = new ArrayList<>();

        // when
        String firstCodeLine = LineUtils.nextCodeLine(inputLines, outputLines);

        // Then
        assertThat(firstCodeLine)
                .isEqualTo(codeLine);
        assertThat(outputLines)
                .hasSize(3)
                .containsExactly(commentedLineStart, commentedLineMiddle, commentedLineEnd);
    }

    @Test
    @DisplayName("nextCodeLine method : 3 documentation lines '\\u0009/** documentation ' '\\u0009* documentation' '\\u0009*/' then code line 'interface it { }'")
    void nextCodeLine_MultilineDocumentationCommentThenCodeLine() throws EOFException {
        // Given
        final String documentationLineStart = "\u0009/* documentation ";
        final String documentationLineMiddle = "\u0009* documentation ";
        final String documentationLineEnd = "\u0009*/";
        final String codeLine = "interface it { }";
        final Iterator<String> inputLines = Arrays.asList(documentationLineStart, documentationLineMiddle, documentationLineEnd, codeLine).iterator();
        final List<String> outputLines = new ArrayList<>();

        // when
        String firstCodeLine = LineUtils.nextCodeLine(inputLines, outputLines);

        // Then
        assertThat(firstCodeLine)
                .isEqualTo(codeLine);
        assertThat(outputLines)
                .hasSize(3)
                .containsExactly(documentationLineStart, documentationLineMiddle, documentationLineEnd);
    }
}
