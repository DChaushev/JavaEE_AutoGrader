/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaeefmi.core.grader;

import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 *
 * @author Dimitar
 */
public class Unzipper {

    public static String unzipToFolder(String zip) {
        try {
            File tempDir = new File("temp");
            if (!tempDir.exists()) {
                tempDir.mkdir();
            }
            File folderName = new File(String.format("%s/%d%d", tempDir.getPath(), System.currentTimeMillis(), new Random().nextInt()));
            if (!folderName.exists()) {
                folderName.mkdir();
            }
            System.out.println(zip);
            ZipFile zipFile = new ZipFile(zip);
            zipFile.extractAll(folderName.getAbsolutePath());

            return folderName.getAbsolutePath();
        } catch (ZipException ex) {
            Logger.getLogger(Unzipper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
