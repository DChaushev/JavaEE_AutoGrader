package com.javaeefmi.core.grader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
public class FileReader {

    public String getContent(String file) {

        StringBuilder result = new StringBuilder();

        try {
            Files.lines(Paths.get(file)).forEach(line -> {
                result.append(line + "\n");
            });
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result.toString();
    }

}
