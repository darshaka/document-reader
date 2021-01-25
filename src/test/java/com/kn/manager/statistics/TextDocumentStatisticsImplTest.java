package com.kn.manager.statistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

class TextDocumentStatisticsImplTest {

    private String filePath = "src/test/resources/file2.txt";

    private DocumentStatistics textDocumentStatisticsImpl;
    private Stream<String> sampleLines;

    @BeforeEach
    void setUp() throws IOException {
        textDocumentStatisticsImpl = new TextDocumentStatisticsImpl();
        sampleLines = Files.lines(Paths.get(filePath));
        textDocumentStatisticsImpl.streamProcessor(sampleLines);
    }

    @Test
    void testGetMostFrequentWordMap() {
        LinkedHashMap<String, Integer> result = textDocumentStatisticsImpl.getMostFrequentWordMap(2);
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void testGetMostFrequentCharMap() {
        LinkedHashMap<String, Integer> result = textDocumentStatisticsImpl.getMostFrequentCharMap(2);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(9, result.get("i"));
        Assertions.assertEquals(6, result.get("s"));
        Assertions.assertEquals(6, result.get("e"));
    }
}