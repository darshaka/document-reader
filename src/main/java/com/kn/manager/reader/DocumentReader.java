package com.kn.manager.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public abstract class DocumentReader {

    String filePath;

    public DocumentReader(String filePath) {
        this.filePath = filePath;
    }

    public boolean validateFilePath() {
        return Files.isRegularFile(Paths.get(filePath));
    }

    public abstract Stream<String> getAllLines() throws IOException;
}
