package com.kn.manager.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextFileReader extends DocumentReader {

    public TextFileReader(String filePath) {
        super(filePath);
    }

    @Override
    public Stream<String> getAllLines() throws IOException {
        return Files.lines(Paths.get(filePath));
    }
}
