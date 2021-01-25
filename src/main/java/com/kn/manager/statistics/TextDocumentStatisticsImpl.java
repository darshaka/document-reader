package com.kn.manager.statistics;

import com.kn.util.Message;
import com.kn.util.MessageReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class TextDocumentStatisticsImpl extends DocumentStatistics {

    public TextDocumentStatisticsImpl() {
        wordSplitRegex = MessageReader.getInstance().getMessage(Message.wordSplitRegex.toString());
        supportedStringFormatRegex = MessageReader.getInstance().getMessage(Message.supportedStringFormatRegex.toString());
    }

    @Override
    public void streamProcessor(Stream<String> lines) {
        lines.forEach(x ->
            calculateMatrix(
                    Arrays.stream(x.split(wordSplitRegex))
                            .filter(word -> !word.trim().equals(""))
                            .filter(word -> word.trim().matches(supportedStringFormatRegex))
                            .collect(Collectors.toList())
            )
        );
    }

    @Override
    public LinkedHashMap<String, Integer> getMostFrequentWordMap(int limit) {
        return getMostFrequentStrings(wordMap, limit);
    }

    @Override
    public LinkedHashMap<String, Integer> getMostFrequentCharMap(int limit) {
        return getMostFrequentStrings(charMap, limit);
    }

    private LinkedHashMap<String, Integer> getMostFrequentStrings(Map<String, Integer> infoCounts, int count) {
        LinkedHashMap<String, Integer> collect = infoCounts
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        Set<String> keySet = collect.keySet();
        Long previousValue = 0L;
        for (String key : keySet) {
            count--;
            if(count == 0) {
                previousValue = new Long(collect.get(key));
            } else if(count < 0) {
                if(previousValue > new Long(collect.get(key))) {
                    break;
                }
            }
            result.put(key, collect.get(key));
        }
        return result;
    }

    private void calculateMatrix(List<String> words) {
        for (String word : words) {
            int count = 1;
            if(wordMap.containsKey(word)) {
                count = wordMap.get(word) + 1;
            }
            wordMap.put(word, count);
            charCalculateMatrix(
                    word.chars().mapToObj(e -> Character.toString((char) e)).collect(Collectors.toList()));
        }
    }

    private void charCalculateMatrix(List<String> words) {
        for (String word : words) {
            int count = 1;
            if(charMap.containsKey(word)) {
                count = charMap.get(word) + 1;
            }
            charMap.put(word, count);

        }
    }
}
