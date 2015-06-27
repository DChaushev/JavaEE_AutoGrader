package com.javaeefmi.core.grader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dimitar
 */
public enum ErrorMessage {

    RuntimeError("RE"),
    CompilationError("CE"),
    No("NO"),
    Ok("OK");

    private final String error;

    private ErrorMessage(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }

}
