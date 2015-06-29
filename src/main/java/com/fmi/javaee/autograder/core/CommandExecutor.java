package com.fmi.javaee.autograder.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
public class CommandExecutor {

    private Process process;
    private final List<String> commands;
    private final String inputParams;
    private ThreadedStreamHandler inputStreamHandler;
    private ThreadedStreamHandler errorStreamHandler;

    public CommandExecutor(List<String> commands, String inputParams) {
        this.commands = commands;
        this.inputParams = inputParams;
    }

    public void executeCommand() {

        try {
            ProcessBuilder pb = new ProcessBuilder(commands);
            process = pb.start();

            OutputStream stdOutput = process.getOutputStream();
            InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream();

            if (inputParams != null) {
                inputStreamHandler = new ThreadedStreamHandler(inputStream, stdOutput, inputParams);
            } else {
                inputStreamHandler = new ThreadedStreamHandler(inputStream);
            }
            errorStreamHandler = new ThreadedStreamHandler(errorStream);

            inputStreamHandler.start();
            errorStreamHandler.start();

            inputStreamHandler.join(2000);
            errorStreamHandler.join(2000);

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(CommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            process.destroy();
        }

    }

    public String getResult() {
        return inputStreamHandler.getOutput();
    }

    public String getError() {
        return errorStreamHandler.getOutput();
    }
}
