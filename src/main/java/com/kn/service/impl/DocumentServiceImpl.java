package com.kn.service.impl;

import com.kn.manager.reader.DocumentReader;
import com.kn.manager.reader.TextFileReader;
import com.kn.manager.statistics.DocumentStatistics;
import com.kn.manager.statistics.TextDocumentStatisticsImpl;
import com.kn.service.DocumentService;
import com.kn.util.FileStatistics;
import com.kn.util.FileType;
import com.kn.util.Message;
import com.kn.util.MessageReader;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

public class DocumentServiceImpl implements DocumentService {

    private DocumentReader documentReader;
    private DocumentStatistics documentStatistics;
    private final int limit;
    private FileStatistics statistics;

    public DocumentServiceImpl() {
        limit = Integer.parseInt(MessageReader.getInstance().getMessage(Message.recordLimit.toString()));
    }

    @Override
    public FileStatistics getFileStatistics(String file) throws IOException {
        statistics = new FileStatistics(file);
        Optional<String> fileExtension = getExtensionByStringHandling(file);
        if (fileExtension.isPresent()) {
            boolean isSupportedFileFormat = false;
            switch (fileExtension.get().toUpperCase()) {
                case FileType.TXT:
                case FileType.PROPERTIES: {
                    documentReader = new TextFileReader(file);
                    documentStatistics = new TextDocumentStatisticsImpl();
                    isSupportedFileFormat = true;
                    break;
                }
            }
            if(isSupportedFileFormat) {
                if(documentReader.validateFilePath()) {
                    Stream<String> fileByLines = documentReader.getAllLines();
                    getStreamStatistics(fileByLines);
                } else {
                    statistics.setMessage(MessageReader.getInstance().getMessage(Message.fileNotFound.toString()));
                }
            } else {
                statistics.setMessage(MessageReader.getInstance().getMessage(Message.fileFormatNotSupported.toString()));
            }
        } else {
            statistics.setMessage(MessageReader.getInstance().getMessage(Message.cannotFindExtension.toString()));
        }

        return statistics;
    }

    private void getStreamStatistics(Stream<String> fileByLines) {
        documentStatistics.streamProcessor(fileByLines);
        statistics.setMostFrequentWords(documentStatistics.getMostFrequentWordMap(limit));
        statistics.setMostFrequentCharacters(documentStatistics.getMostFrequentCharMap(limit));
        statistics.setStatus(true);
    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
