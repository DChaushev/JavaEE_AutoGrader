package com.fmi.javaee.autograder.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dimitar
 */
public class ThreadedStreamHandler extends Thread {

    private InputStream inputStream;
    private String inputParams;
    private OutputStream outputStream;
    private PrintWriter printWriter;
    private StringBuilder outputBuilder = new StringBuilder();
    private boolean writeRequest = false;

    ThreadedStreamHandler(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ThreadedStreamHandler(InputStream inputStream, OutputStream outputStream, String inputParams) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.printWriter = new PrintWriter(outputStream);
        this.inputParams = inputParams;
        this.writeRequest = true;
    }

    @Override
    public void run() {

        if (writeRequest) {
            printWriter.println(inputParams);
            printWriter.flush();
        }

        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = buffReader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String getOutput() {
        return outputBuilder.toString();
    }
}
