/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaeefmi.core.grader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimitar
 */
public class TemporaryFileManager {

    public static String createFile(String source) {

        String fileName = String.format("%d%d", System.currentTimeMillis(), new Random().nextInt());

        try (PrintWriter writer = new PrintWriter(fileName + ".cpp", "UTF-8")) {

            writer.println(source);

        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(TemporaryFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fileName;
    }

    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

}
