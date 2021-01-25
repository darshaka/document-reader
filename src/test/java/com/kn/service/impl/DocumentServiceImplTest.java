package com.kn.service.impl;

import com.kn.manager.reader.DocumentReader;
import com.kn.manager.statistics.DocumentStatistics;
import com.kn.util.FileStatistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import static org.mockito.Mockito.*;

class DocumentServiceImplTest {

    private String filePath = "src/test/resources/file3.txt";

    @Mock
    DocumentReader documentReader;
    @Mock
    DocumentStatistics documentStatistics;
    @Mock
    FileStatistics expectedStatistics;
    @InjectMocks
    DocumentServiceImpl documentServiceImpl;

    LinkedHashMap<String, Integer> wordsMap = new LinkedHashMap<>();
    LinkedHashMap<String, Integer> charsMap = new LinkedHashMap<>();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        wordsMap.put("line", 3);
        wordsMap.put("this", 3);
        wordsMap.put("is", 3);
        wordsMap.put("one", 1);
        wordsMap.put("two", 1);
        wordsMap.put("three", 1);
        charsMap.put("i", 9);
        charsMap.put("s", 6);
        charsMap.put("e", 6);
        charsMap.put("t", 5);
        charsMap.put("h", 4);
        charsMap.put("n", 4);
        expectedStatistics = new FileStatistics(filePath);
        expectedStatistics.setStatus(true);
        expectedStatistics.setMostFrequentCharacters(charsMap);
        expectedStatistics.setMostFrequentWords(wordsMap);
    }

    @Test
    void testGetFileStatistics() throws IOException {
        when(documentReader.validateFilePath()).thenReturn(true);
        when(documentReader.getAllLines()).thenReturn(Files.lines(Paths.get(filePath)));
        when(documentStatistics.getMostFrequentWordMap(anyInt())).thenReturn(wordsMap);
        when(documentStatistics.getMostFrequentCharMap(anyInt())).thenReturn(charsMap);

        FileStatistics result = documentServiceImpl.getFileStatistics(filePath);
        Assertions.assertEquals(expectedStatistics.getFileName(), result.getFileName());
        Assertions.assertEquals(expectedStatistics.getMostFrequentWords(), result.getMostFrequentWords());
        Assertions.assertEquals(expectedStatistics.getMostFrequentCharacters(), result.getMostFrequentCharacters());
        Assertions.assertEquals(expectedStatistics.getMostFrequentCharacters(), result.getMostFrequentCharacters());
        Assertions.assertEquals(expectedStatistics.isStatus(), result.isStatus());
        Assertions.assertEquals(expectedStatistics.getMessage(), result.getMessage());
    }
}