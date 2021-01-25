package com.kn.manager.statistics;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class DocumentStatistics {
    Map<String, Integer> wordMap = new HashMap<>();
    Map<String, Integer> charMap = new HashMap<>();
    String wordSplitRegex;
    String supportedStringFormatRegex;

    public abstract void streamProcessor(Stream<String> lines);
    public abstract LinkedHashMap<String, Integer> getMostFrequentWordMap(int limit);
    public abstract LinkedHashMap<String, Integer> getMostFrequentCharMap(int limit);
}
