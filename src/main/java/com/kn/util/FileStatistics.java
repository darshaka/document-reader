package com.kn.util;

import com.kn.ReaderApplication;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;

public class FileStatistics {

    final Logger logger = Logger.getLogger(ReaderApplication.class.getName());

    private String fileName;
    private LinkedHashMap<String, Integer> mostFrequentWords;
    private LinkedHashMap<String, Integer> mostFrequentCharacters;
    private boolean status = false;
    private String message;
    private StringBuffer summary;

    public FileStatistics(String fileName) {
        this.fileName = fileName;
    }

    public void setMostFrequentWords(LinkedHashMap<String, Integer> mostFrequentWords) {
        this.mostFrequentWords = mostFrequentWords;
    }

    public void setMostFrequentCharacters(LinkedHashMap<String, Integer> mostFrequentCharacters) {
        this.mostFrequentCharacters = mostFrequentCharacters;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public LinkedHashMap<String, Integer> getMostFrequentWords() {
        return mostFrequentWords;
    }

    public LinkedHashMap<String, Integer> getMostFrequentCharacters() {
        return mostFrequentCharacters;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void print() {
        summary = new StringBuffer();
        summary = summary.append("File Name: ").append(this.fileName).append("\n\n");
        if(this.status) {
            summary = summary.append("Most frequent words\n");
            this.mostFrequentWords.forEach((key, value) -> {
                summary = summary.append(key).append(" : ").append(value).append("\n");
            });
            summary = summary.append("\nMost frequent Character\n");
            this.mostFrequentCharacters.forEach((key, value) -> {
                summary = summary.append(key).append(" : ").append(value).append("\n");
            });
        } else {
            summary = summary.append(this.message);
        }
        logger.info(summary.toString());
        System.out.println(summary);
    }
}
