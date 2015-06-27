package com.javaeefmi.core.grader;

import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dimitar
 */
public class Grader {

    private final String source;
    private final List<String> inputFiles;
    private final List<String> outputFiles;

    public Grader(String source, List inputFiles, List outputFiles) {
        this.source = source;
        this.inputFiles = inputFiles;
        this.outputFiles = outputFiles;
    }

    public String runTests() {

        String fileName = TemporaryFileManager.createFile(source);
        StringBuilder result = new StringBuilder();

        FileReader reader = new FileReader();

        String outcome = "";
        if ((outcome = compile(fileName)).equals(ErrorMessage.Ok.toString())) {
            for (int i = 0; i < inputFiles.size(); i++) {
                List<String> command = Arrays.asList(fileName + ".exe");
                ErrorMessage em;

                String input = reader.getContent(inputFiles.get(i));
                String output = reader.getContent(outputFiles.get(i));

                em = test(command, input, output);
                result.append(em);

                if (i != inputFiles.size() - 1) {
                    result.append(" ");
                }
            }
        } else {
            result.append(outcome);
        }

        TemporaryFileManager.deleteFile(fileName + ".cpp");
        TemporaryFileManager.deleteFile(fileName + ".exe");
        return result.toString();
    }

    private String compile(String fileName) {

        String[] compileCommand = new String[]{"g++", fileName + ".cpp", "-o", fileName, "-std=c++11"};
        ProcessBuilder pb = new ProcessBuilder(compileCommand);
        CommandExecutor executor = new CommandExecutor(Arrays.asList(compileCommand), null);
        executor.executeCommand();
        if (executor.getError().length() != 0) {
            return executor.getError();
        }
        return (executor.getError().length() == 0) ? ErrorMessage.Ok.toString() : executor.getError();

    }

    private ErrorMessage test(List<String> command, String input, String output) {
        CommandExecutor executor = new CommandExecutor(command, input);
        executor.executeCommand();
        String result = executor.getResult();

        if (result.equals("")) {
            return ErrorMessage.RuntimeError;
        } else {
            return (result.equals(output) ? ErrorMessage.Ok : ErrorMessage.No);
        }

    }

}
