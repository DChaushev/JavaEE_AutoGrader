package com.fmi.javaee.autograder.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dimitar
 */
public class FilesRetriever {

    public List<String> getFiles(String folder) {
        List<String> results = new ArrayList<>();
        try {
            Files.walk(Paths.get(folder)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    results.add(filePath.toString());
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(FilesRetriever.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }

}
