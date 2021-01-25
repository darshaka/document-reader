package com.kn;

import com.kn.service.DocumentService;
import com.kn.service.impl.DocumentServiceImpl;
import com.kn.util.FileStatistics;
import com.kn.util.Message;
import com.kn.util.MessageReader;
import org.apache.log4j.Logger;


import java.io.IOException;

public class ReaderApplication {

    final Logger logger = Logger.getLogger(ReaderApplication.class.getName());

    private DocumentService documentService = new DocumentServiceImpl();

    public static void main(String[] args) {
        if(args.length > 0) {
            new ReaderApplication(args[0]);
        } else {
            System.out.println(MessageReader.getInstance().getMessage(Message.ranwWithoutArguments.toString()));
        }
    }

    public ReaderApplication(String file) {
        try {
            FileStatistics fileStatistics = documentService.getFileStatistics(file);
            fileStatistics.print();
        } catch (IOException e) {
            System.out.println(MessageReader.getInstance().getMessage(Message.contactSystemAdmin.toString()));
            logger.debug(e.toString());
        }
    }

}
