package com.kn.service;

import com.kn.util.FileStatistics;

import java.io.IOException;

public interface DocumentService {
    FileStatistics getFileStatistics(String file) throws IOException;
}
