package com.kn.manager.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

class TextFileReaderTest {
    TextFileReader textFileReader = new TextFileReader("src/test/resources/file1.txt");

    @Test
    void testGetAllLines() throws IOException {
        Stream<String> result = textFileReader.getAllLines();
        result.forEach(line -> {
            Assertions.assertEquals("test file one", line);
        });
    }

    @Test
    void testValidateFilePath() {
        boolean result = textFileReader.validateFilePath();
        Assertions.assertEquals(true, result);
    }

    @Test
    void testInValidateFilePath() {
        textFileReader = new TextFileReader("src/test/resources/notfound.txt");
        boolean result = textFileReader.validateFilePath();
        Assertions.assertEquals(false, result);
    }
}
