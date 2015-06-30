/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.javaee.autograder.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author vankata
 */
public class SaveTasks {

    public static void writeToFile(InputStream stream, String fileName, Integer id) {
        String fileLocation = System.getProperty("user.dir") + "Problem Set" + id +fileName;
        System.out.println("The location is " + fileLocation);
        try (OutputStream out = new FileOutputStream(new File(fileLocation))) {
            int read = 0;
            byte bytes[] = new byte[1024];
            while ((read = stream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException e) {

        }
    }
}
